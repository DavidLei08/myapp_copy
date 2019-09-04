package jp.co.nttdata.myapp.common;

/**
 * @author chao11.lei
 * @version 1.0
 * 定量配置
 */
public class Constants {

//    public static final String REQ_HEADER_AUTH_CODE_KEY = "X-Wallet-Auth-Code";
    public static final String REQ_HEADER_AUTH_TOKEN_KEY = "X-WALLET-ACCESS-TOKEN";
    public static final String REQ_HEADER_USER_NAME_KEY = "X-WALLET-USER-NAME";
    public static final String REQ_HEADER_REFRESH_TOKEN_KEY = "X-WALLET-REFRESH-TOKEN";
    
    
    
//    public static final String REQ_HEADER_AUTH_ID_KEY = "X-Client-Auth-ID";
//    public static final String RES_HEADER_AUTH_TOKEN_KEY = "X-Server-Auth-Token";
//    public static final String RES_HEADER_AUTH_EXPIRATION_TIME_KEY = "X-Client-Auth-Expiration_Time";
    public static final String REQ_SESSION_USER_ID_KEY = "X-User-ID";
    public static final String REQ_SESSION_USER_NAME_KEY = "X-User-Name";
    
    public static final int LOGIN_TYPE_NORMAL = 0;
    
    
    
    /* request header for common authorization check */
    public static final String HEADER_AUTH_TOKEN_NAME = "X-mobile-Auth-Token";
    public static final String HEADER_AUTH_TOKEN_VALUE = "";

    /* request header for dashboard authorization check */
    public static final String HEADER_AUTH_DASHBOARD_TOKEN = "X-Dashboard-Auth-Token";
    public static final String HEADER_AUTH_DASHBOARD_ID = "X-Dashboard-Auth-ID";

    /* request key */
    public static final String REQ_KEY_USER = "user";
    public static final String REQ_KEY_PRODUCTS = "products";
    public static final String REQ_KEY_ORDER = "order";
    public static final String REQ_KEY_USER_USERNO = "user_no";
    public static final String REQ_KEY_ORDER_TOTAL = "total";
    public static final String REQ_KEY_ORDER_DETAILS = "details";

}
