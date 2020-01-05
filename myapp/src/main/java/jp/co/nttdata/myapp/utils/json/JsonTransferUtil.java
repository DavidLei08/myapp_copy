package jp.co.nttdata.myapp.utils.json;


import com.alibaba.fastjson.JSONObject;

/**
 * JSON对象转换工具类.
 */
public class JsonTransferUtil {

    /**
     * java对象转json字符串.
     * @param o
     * @return
     */
    public static String getJSONStr(Object o){

        return JSONObject.toJSONString(o);
    }

    /**
     * json字符串转java对象.
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getJSONStrToObject(String jsonStr,Class<T> t)  {
        JSONObject jsonobject = JSONObject.parseObject(jsonStr);

        T object = (T)JSONObject.toJavaObject(jsonobject,t);
        return  object;
    }

    /**
     * 字符串转json对象.
     * @param jsonStr
     * @return
     */
    public static JSONObject getStrToJSONObject(String jsonStr){
        JSONObject jsonobject = JSONObject.parseObject(jsonStr);
        return jsonobject;
    }

    /**
     * json对象转字符串.
     * @param object
     * @return
     */
    public static String getJsonObjectToStr(JSONObject object){
        return object.toJSONString();
    }
}
