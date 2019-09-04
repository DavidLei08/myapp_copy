package jp.co.nttdata.myapp.common;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chao11.lei
 * @version 1.0
 * 全局异常捕获
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    protected static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * MethodArgumentNotValidException异常捕获
     * @param ex 异常
     * @return 消息
     */
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String exceptionHandler(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        return ex.getMessage();
    }

    /**
     * APIException异常捕获
     * @param ex 异常
     * @param response 响应体
     * @return 消息
     */
    @ResponseBody
    @ExceptionHandler(value = APIException.class)
    public String apiExceptionHandler(APIException ex, HttpServletResponse response) {
        response.setStatus(ex.getStatus());
        logger.error(ex.getErrMsg());
        return ex.getErrMsg();
    }
}
