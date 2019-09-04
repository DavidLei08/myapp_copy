package jp.co.nttdata.myapp.controller.login;


import jp.co.nttdata.myapp.authorization.annotation.TokenAuthorization;
import jp.co.nttdata.myapp.common.APIException;
import jp.co.nttdata.myapp.common.Constants;
import jp.co.nttdata.myapp.model.login.LoginDetail;
import jp.co.nttdata.myapp.model.login.LoginUserInfo;
import jp.co.nttdata.myapp.model.login.RegisterUserInfo;
import jp.co.nttdata.myapp.service.login.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author chao11.lei
 * @version 1.0
 * 用户登录登出注册用controller
 */
@CrossOrigin
@RestController
public class LoginController {


    /**
     * 用户登录相关service
     */
    @Autowired
    private UserLoginService service;

    /**
     * 用户登录
     * @param loginDetail 用户登录提交信息
     * @param result 校验结果
     * @return 用户登录结果信息
     */
    @RequestMapping(value="/login",method =RequestMethod.POST)
    public LoginUserInfo userLogin(@RequestBody @Valid LoginDetail loginDetail, BindingResult result){
        //判断输入信息校验是否有错
        if(!result.hasErrors()){
            //执行登录，接受登录结果
            LoginUserInfo userInfo=service.login(loginDetail);
            return  userInfo;
        }else{
            //抛出异常-请求参数格式不对
            throw  new APIException(HttpServletResponse.SC_BAD_REQUEST,
                    "request parameters formatter is wrong ");
        }
    }

    /**
     * 用户注册
     * @param userInfo 注册提交的用户信息
     * @param result 信息校验结果
     * @return 注册结果
     */
    @RequestMapping(value = "/logon",method =RequestMethod.POST)
    public String userRegister(@RequestBody  @Valid RegisterUserInfo userInfo,BindingResult result){
        //判断校验是否有误
        if(!result.hasErrors()){
            //执行注册
            service.register(userInfo);
        }else{
            //抛出异常-提交参数格式不对
            throw  new APIException(HttpServletResponse.SC_BAD_REQUEST,
                    "request parameters formatter is wrong ");
        }
        return  "ok";
    }

    /**
     * 用户登出
     * @param request 请求体
     * @return 登出结果
     */
    @TokenAuthorization
    @RequestMapping(value = "/logout",method =RequestMethod.GET)
    public String userLogout( HttpServletRequest request){
        //从header中取得用户名
        String   username=request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY);
        //执行登出
        service.logout(username);
        return  "ok";
    }
}
