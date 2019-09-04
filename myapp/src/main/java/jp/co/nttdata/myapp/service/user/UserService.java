package jp.co.nttdata.myapp.service.user;

        import jp.co.nttdata.myapp.model.user.UpdatePasswordInfo;
        import jp.co.nttdata.myapp.model.user.UpdateUserInfo;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户信息service
 */
public interface UserService {
    /**
     * 获取修改前的用户信息
     * @param username
     * @return
     */
    public UpdateUserInfo getUpdateUserInfo(String username);

    /**
     * 执行用户信息的修改
     * @param updateUserInfo
     */
    public void updateUserInfo(UpdateUserInfo updateUserInfo);

    /**
     * 修改密码
     * @param passwordInfo
     */
    public void updatePassword(UpdatePasswordInfo passwordInfo);
}
