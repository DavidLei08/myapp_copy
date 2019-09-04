package jp.co.nttdata.myapp.controller.user;

import jp.co.nttdata.myapp.authorization.annotation.TokenAuthorization;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.common.Constants;
import jp.co.nttdata.myapp.model.user.UpdatePasswordInfo;
import jp.co.nttdata.myapp.model.user.UpdateUserInfo;
import jp.co.nttdata.myapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户个人信息操作用controller
 */
@CrossOrigin
@RestController
public class UserController {

    /**
     * 用户个人信息service
     */
    @Autowired
    private UserService service;

    /**
     *获取修改前用户相关信息
     * @param request 请求体
     * @return 用户相关信息
     */
    @TokenAuthorization
    @RequestMapping(value="/updateuser",method = RequestMethod.GET)
    public UpdateUserInfo updateUser( HttpServletRequest request){
        //获取header中的 用户名
        String  username=request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY);
        //返回修改前的用户信息
        return  service.getUpdateUserInfo(username);
    }

    /**
     * 执行用户相关信息修改
     * @param userInfo 提交的用户信息
     * @param result 校验结果
     * @param request 请求体
     * @return 修改结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public String  doUpdateUserInfo (@RequestBody @Valid UpdateUserInfo userInfo,
                                     BindingResult result,HttpServletRequest request){
        //判断校验是否正确
        if((!result.hasErrors())&&userInfo.getUsername().
                equals(request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY))){
            //执行用户信息更改
            service.updateUserInfo(userInfo);
        }else{
            //抛出异常-请求参数有误
            throw new APIException(HttpServletResponse.SC_BAD_REQUEST,"request parameter is wrong");
        }
        return "ok";
    }

    /**
     * 修改密码
     * @param updatePasswordInfo 修改密码提交的信息
     * @param result 校验结果
     * @param request 请求体
     * @return 修改结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public String updatePassword(@RequestBody @Valid UpdatePasswordInfo updatePasswordInfo,
                                 BindingResult result,HttpServletRequest request){
        //判断校验是否正确
        if((!result.hasErrors())&&updatePasswordInfo.getUsername().
                equals(request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY))){
            //执行密码修改
            service.updatePassword(updatePasswordInfo);
        }else {
            //抛出异常-请求参数格式不正确
            throw new APIException(HttpServletResponse.SC_BAD_REQUEST,"request parameter is wrong");
        }
        return  "ok";
    }
}
