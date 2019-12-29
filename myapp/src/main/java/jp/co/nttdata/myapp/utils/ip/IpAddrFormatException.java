package jp.co.nttdata.myapp.utils.ip;

public class IpAddrFormatException extends Throwable {

    static final long serialVersionUID = -3387516993124229958L;


    public IpAddrFormatException() {
        super();
    }


    public IpAddrFormatException(String message) {
        super(message);
    }


    public IpAddrFormatException(String message, Throwable cause) {
        super(message, cause);
    }


    public IpAddrFormatException(Throwable cause) {
        super(cause);
    }


    protected IpAddrFormatException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
