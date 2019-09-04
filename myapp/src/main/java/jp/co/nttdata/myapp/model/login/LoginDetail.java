package jp.co.nttdata.myapp.model.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户登录输入信息
 */
public class LoginDetail implements Serializable {
    private static final long serialVersionUID = -3253216958845154328L;
    @NotNull
    @Size(max = 32,min = 1)
    private String username;
    @NotNull
    @Size(max = 32,min = 1)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
