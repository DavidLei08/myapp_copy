package jp.co.nttdata.myapp.common;

/**
 * @author chao11.lei
 * @version 1.0
 * api自定义异常
 */
public class APIException extends RuntimeException {


    private static final long serialVersionUID = -3253216958845154328L;
    private int status;
    private String errMsg;

    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public APIException(int status, String msg) {
        this.status = status;
        this.errMsg = msg;
    }
}