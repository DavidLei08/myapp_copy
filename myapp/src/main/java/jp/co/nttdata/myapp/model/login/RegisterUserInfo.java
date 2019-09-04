package jp.co.nttdata.myapp.model.login;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户注册提交的信息
 */
public class RegisterUserInfo implements Serializable {
    private static final long serialVersionUID = -3253216958845154328L;
    @NotEmpty
    @Size(max = 32,min = 1)
    private String username;

    @NotEmpty
    @Size(max = 32,min = 1)
    private String password;

    @Pattern(regexp = "^\\d{11}$")
    @Size(max = 11,min = 11)
    private String telephoneNum;

    @NotNull
    @Size(max = 32,min =1)
    private String dearName;

    @NotNull
    @Pattern(regexp = "^([A-Za-z0-9_\\-+.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})$")
    @Size(max = 32)
    private String email;

    private LocalDateTime createTime;

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public String getDearName() {
        return dearName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public void setDearName(String dearName) {
        this.dearName = dearName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
