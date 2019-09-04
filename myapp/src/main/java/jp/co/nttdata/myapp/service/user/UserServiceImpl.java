package jp.co.nttdata.myapp.service.user;


import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.model.user.UpdatePasswordInfo;
import jp.co.nttdata.myapp.model.user.UpdateUserInfo;
import jp.co.nttdata.myapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository repository;
    @Override
    public UpdateUserInfo getUpdateUserInfo(String username) {
        UpdateUserInfo updateUserInfo=repository.selUserInfoByUsername(username);
        if(updateUserInfo==null){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "user update information failed to get");
        }
        return updateUserInfo;
    }

    @Override
    public void updateUserInfo(UpdateUserInfo updateUserInfo) {
        try {
         int total=   repository.updateUserInfo(updateUserInfo);
         if(total==0){
             throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "user information is disabled");
         }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "user information is wrong ,failed to  update");
        }
    }

    @Override
    public void updatePassword(UpdatePasswordInfo passwordInfo) {
        try {
         int total=   repository.updatePassword(passwordInfo);
         if(total==0){
             throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "password information is disabled");
         }
        }catch (Exception e){
            throw new APIException(HttpServletResponse.SC_UNAUTHORIZED, "password info is wrong ,failed to  update");
        }

    }
}
