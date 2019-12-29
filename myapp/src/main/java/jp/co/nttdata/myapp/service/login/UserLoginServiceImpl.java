package jp.co.nttdata.myapp.service.login;

import com.alibaba.fastjson.JSONObject;
import jp.co.nttdata.myapp.authorization.manager.AuthTokenManager;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.model.login.RegisterUserInfo;
import jp.co.nttdata.myapp.model.login.LoginDetail;
import jp.co.nttdata.myapp.model.login.LoginUserInfo;
import jp.co.nttdata.myapp.repository.AppUserRepository;
import jp.co.nttdata.myapp.repository.AuthTokenRepository;
import jp.co.nttdata.myapp.utils.json.JsonTransferUtil;
import jp.co.nttdata.myapp.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class UserLoginServiceImpl implements  UserLoginService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AuthTokenManager authTokenManager;

    private Jedis jedis = RedisUtil.getJedis();

    @Transactional
    @Override
    public LoginUserInfo login(LoginDetail loginDetail) {
        //初始化登陆返回信息
        LoginUserInfo userInfo= null;
        //从redis中先行查询
        String userInfoStr = jedis.get(JSONObject.toJSONString(loginDetail));
        //处理信息，转换成java对象
        userInfo = JSONObject.toJavaObject(JSONObject.parseObject(userInfoStr),LoginUserInfo.class);
        //判断从redis中是否获取成功
        if(userInfo == null) {
            //若不成功。则查询db
            userInfo = appUserRepository.login(loginDetail);
            //若db查询记录没有
            if(userInfo==null){
                //抛出自定义异常，返回异常数据
                throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "Wrong username or password!");
            } else {
                //若db中存在这条数据，则把这条数据存入redis
                jedis.set(JSONObject.toJSONString(loginDetail),JSONObject.toJSONString(userInfo));
            }
        }
        //更新用户的登陆时间
        appUserRepository.updateLoginTime(loginDetail.getUsername(),LocalDateTime.now());
        //设置用户的token
        userInfo.setAccessToken(authTokenManager.createAccessToken(userInfo.getUsername()).getAccessToken());

        return userInfo;
    }

    @Override
    public void register(RegisterUserInfo user) {
            user.setCreateTime(LocalDateTime.now());
        try {
           int total= appUserRepository.register(user);
           if(total==0){
               throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "user information is disabled ,failed to  register");
           }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "user information is wrong ,failed to  register");
        }
    }

    @Transactional
    @Override
    public void logout(String username) {
        try {
            authTokenManager.deleteTokenByUser(username);
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "unkown reason ,try logout again");
        }
        appUserRepository.updateLogoutTime(username,LocalDateTime.now());
    }
}
