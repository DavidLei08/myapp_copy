package jp.co.nttdata.myapp.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdatePasswordInfo {
    @NotEmpty
    @Size(min = 1,max = 32)
    private String username;

    @NotEmpty
    @Size(min = 1,max = 32)
    private String oldPassword;

    @NotEmpty
    @Size(min = 1,max = 32)
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
