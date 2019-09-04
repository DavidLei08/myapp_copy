package jp.co.nttdata.myapp.service.login;

import jp.co.nttdata.myapp.model.login.RegisterUserInfo;
import jp.co.nttdata.myapp.model.login.LoginDetail;
import jp.co.nttdata.myapp.model.login.LoginUserInfo;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户信息操作用service
 */
public interface UserLoginService {

    /**
     *用户登录
     * @param loginDetail
     * @return
     */
    public LoginUserInfo login(LoginDetail loginDetail);

    /**
     * 用户注册
     * @param user
     */
    public  void  register(RegisterUserInfo user);

    /**
     * 用户登出
     * @param username
     */
    public void logout(String username);

}
