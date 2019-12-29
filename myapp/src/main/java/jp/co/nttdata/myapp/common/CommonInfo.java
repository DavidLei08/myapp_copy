package jp.co.nttdata.myapp.common;

public class CommonInfo {

    /**
     * 返回状态值.
     */
    private String status = "200";
    /**
     * 返回信息.
     */
    private String msg = "OK";

    public CommonInfo() {
    }

    public CommonInfo(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
