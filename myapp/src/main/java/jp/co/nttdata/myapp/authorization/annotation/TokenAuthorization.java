package jp.co.nttdata.myapp.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;

/**
 * @author chao11.lei
 * @version 1.0
 * token认证标识注解
 */
@Configuration
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenAuthorization {
}
