package jp.co.nttdata.myapp.authorization.intercepetor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jp.co.nttdata.myapp.authorization.annotation.TokenAuthorization;
import jp.co.nttdata.myapp.authorization.manager.AuthTokenManager;
import jp.co.nttdata.myapp.common.Constants;

/**
 * @author chao11.lei
 * @version  1.0
 * aceessToken验证拦截器
 */
@Configuration
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    /**
     * token管理
     */
    @Autowired
    private AuthTokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //判断是否是方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //判断方法是否被标识
            if (method.getAnnotation(TokenAuthorization.class) != null) {
                //取得请求头中的accessToken
                String authToken = request.getHeader(Constants.REQ_HEADER_AUTH_TOKEN_KEY);
                //取得请求头中的用户名
                String username=request.getHeader(Constants.REQ_HEADER_USER_NAME_KEY);
                //校验accessToken是否有效
                String  validResult= tokenManager.validateAccessToken(authToken,username);
                //判断校验校验结果是那种类型
                if (validResult .equals("empty")) {
                    response.addHeader("Access-Control-Allow-Origin", "*"); // TODO delete
                    // return 401
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                } else if (validResult.equals("not exist")||validResult.equals("expired")) {
                    response.addHeader("Access-Control-Allow-Origin", "*"); // TODO delete
                    // return 403
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }else {
                    //request.setAttribute(Constants.REQ_SESSION_USER_ID_KEY, validResult);
                    return true;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

}
