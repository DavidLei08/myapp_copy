package jp.co.nttdata.myapp.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UpdateUserInfo implements Serializable {
    private static final long serialVersionUID = -3253216958845154328L;
    @NotEmpty
    @Size(min = 1,max = 32)
    private String username;

    @Size(min = 11,max = 11)
    @Pattern(regexp = "^\\d*$")
    private String telephoneNum;

    @NotEmpty
    @Size(min = 1,max = 32)
    private String dearName;

    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^([A-Za-z0-9_\\-+.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})$")
    private String email;

    public String getUsername() {

        return username;
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

    public void setUsername(String username) {
        this.username = username;
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
}
