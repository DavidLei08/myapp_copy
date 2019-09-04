package jp.co.nttdata.myapp.model.login;

import java.io.Serializable;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户登陆后的返回信息
 */
public class LoginUserInfo implements Serializable {
    private static final long serialVersionUID = -3253216958845154328L;

    private String username;

    private String dearName;
    private String accessToken;

    public String getUsername() {
        return username;
    }

    public String getDearName() {

        return dearName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDearName(String dearName) {
        this.dearName = dearName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
