package jp.co.nttdata.myapp.authorization.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jp.co.nttdata.myapp.data.TokenData;
import jp.co.nttdata.myapp.repository.AuthTokenRepository;
import com.fasterxml.uuid.Generators;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * @author chao11.lei
 * @version 1.0
 * 认证token管理
 */
@Component
public class AuthTokenManager {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenManager.class);

    public static final long ACCESS_TOKEN_INVALID = -1L;
    public static final long ACCESS_TOKEN_EXPIRED = -2L;

    /**
     * token用repository
     */
    @Autowired
    private AuthTokenRepository authTokenRepository;

    /**
     * token有效时间
     */
    @Value("${jp.co.nttdata.myapp.accessToken.expirationInterval}")
    private long accessExpirationInterval;

    /**
     * token校验
     * @param token token
     * @param username 用户名
     * @return 校验结果
     */
    public String  validateAccessToken(String token,String username) {
        //token判空
        if (StringUtils.isEmpty(token)) {
            logger.error("Access token is empty!");
            return "empty";
        }
        logger.debug("checkAuthToken ： access_token({})", token);
        //按条件查询查询token信息
        TokenData tokenData = authTokenRepository.selectTokenInfoByAccessToekn(token,username);
        //判断token信息是否取得
        if (tokenData == null) {
            logger.error("Access token not exist![" + token + "]");


            return "not exist";
        }
        //判断时间是否在有效期内
        if (tokenData.getAccessTokenExpirationTime().
                toInstant(ZoneOffset.ofHours(8)).toEpochMilli() < System.currentTimeMillis()) {
            logger.error("Access token was expired![" + token + "]");
            return "expired";
        }
        return "acessToken is ok";
    }

    /**
     * 生成token
     * @param username 用户名
     * @return token信息
     */
    public TokenData createAccessToken(String  username) {
        //生成随机token
        String accessToken = Generators.timeBasedGenerator().generate().toString();
        //token信息初始化
        TokenData tokenData = new TokenData();
        //设定用户名
        tokenData.setUsername(username);
        //设定token
        tokenData.setAccessToken(accessToken);
        //设定有效时间
        tokenData.setAccessTokenExpirationTime(
                LocalDateTime.ofEpochSecond(((accessExpirationInterval +
                        System.currentTimeMillis())/1000),
                        0,ZoneOffset.ofHours(8)));
        //判断当前用户名是否有token
        if(authTokenRepository.selectTokenInfoByUsername(username)!=null){
            //删除旧token
            authTokenRepository.deleteTokenByUsername(username);
        }
        //创建token，向数据库中插入token数据
        int insertResult = authTokenRepository.insertAuthToken(tokenData);

        if (insertResult > 0) {
            return tokenData;
        } else {
            logger.error("Insert token data into table token failed!");
            return null;
        }
    }

    /**
     * 根据用户名删除token信息
     * @param username 用户名
     */
    public void deleteTokenByUser(String username) {
        authTokenRepository.deleteTokenByUsername(username);
    }
}
