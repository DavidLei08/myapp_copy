package jp.co.nttdata.myapp.config;

import jp.co.nttdata.myapp.authorization.intercepetor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author chao11.lei
 * @version  1.0
 * web配置类
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // add authorization interceptor
        registry.addInterceptor(authInterceptor);
        // super.addInterceptors(registry);
    }

    @Profile("dev")
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
        // super.addCorsMappings(registry);
    }
}
