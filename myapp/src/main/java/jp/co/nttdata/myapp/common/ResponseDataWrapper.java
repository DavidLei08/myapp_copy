package jp.co.nttdata.myapp.common;

import java.io.Serializable;

public class ResponseDataWrapper<T> extends BaseResponse  implements  Serializable{

    private static final long serialVersionUID = -3253216958845154378L;

    private T data;


    public ResponseDataWrapper(T data) {
        this.data = data;
    }

    public ResponseDataWrapper(CommonInfo common, T data) {
        super(common);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
