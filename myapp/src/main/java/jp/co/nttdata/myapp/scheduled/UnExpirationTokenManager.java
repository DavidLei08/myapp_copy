package jp.co.nttdata.myapp.scheduled;

import com.sun.javafx.util.Logging;
import jp.co.nttdata.myapp.authorization.manager.AuthTokenManager;
import jp.co.nttdata.myapp.repository.AuthTokenRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author chao11.lei
 * @version  1.0
 * 失效token的定时管理
 */
@Component
public class UnExpirationTokenManager {

    private  static final Logger logger=LoggerFactory.getLogger(UnExpirationTokenManager.class);
    /**
     * token用repository
     */
    @Autowired
    private AuthTokenRepository authTokenRepository;

    /**
     * 每天清除失效token一次
     */
    //@Scheduled(cron = "0 0/2 * * * ? ")
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void delUnExpirationToken(){
        logger.info("start delUnExpirationToke scheduled ");
        try{
            int total= authTokenRepository.deletTokenByExpirationTime();
            if(total==0){
                logger.info("no token was deleted");
            }else{
                logger.info(total+" total token was deleted");
            }
        }catch (Exception e){
            logger.error("scheduled task haven error:"+e.getCause());
        }

    }

}
