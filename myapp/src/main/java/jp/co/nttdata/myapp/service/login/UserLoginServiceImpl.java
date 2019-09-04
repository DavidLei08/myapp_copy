package jp.co.nttdata.myapp.service.login;

import jp.co.nttdata.myapp.authorization.manager.AuthTokenManager;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.model.login.RegisterUserInfo;
import jp.co.nttdata.myapp.model.login.LoginDetail;
import jp.co.nttdata.myapp.model.login.LoginUserInfo;
import jp.co.nttdata.myapp.repository.AppUserRepository;
import jp.co.nttdata.myapp.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Service
public class UserLoginServiceImpl implements  UserLoginService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AuthTokenManager authTokenManager;

    @Transactional
    @Override
    public LoginUserInfo login(LoginDetail loginDetail) {
        LoginUserInfo userInfo= appUserRepository.login(loginDetail);
        if(userInfo==null){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "Wrong username or password!");
        }
        appUserRepository.updateLoginTime(loginDetail.getUsername(),LocalDateTime.now());
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
