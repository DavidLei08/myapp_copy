package jp.co.nttdata.myapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chao11.lei
 * @version 1.0
 * app用户
 */
public class AppUser implements Serializable {
    private static final long serialVersionUID = -3253216958845154328L;
    private String username;

    private String password;

    private String telephoneNum;

    private String dearName;

    private String email;

    private LocalDateTime createTime;

    private LocalDateTime lastLoginTime;

    private LocalDateTime lastLogoutTime;

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

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getDearName() {
        return dearName;
    }

    public void setDearName(String dearName) {
        this.dearName = dearName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(LocalDateTime lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }


}
