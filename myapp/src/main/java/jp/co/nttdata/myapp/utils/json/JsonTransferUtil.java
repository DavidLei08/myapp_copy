package jp.co.nttdata.myapp.utils.json;

import net.sf.json.JSONObject;


/**
 * JSON对象转换工具类.
 */
public class JsonTransferUtil {
    /**
     * java对象转json对象.
     * @param o
     * @return
     */
    public static JSONObject getJSONObject(Object o){
        JSONObject jsonObject = JSONObject.fromObject(o);;
        return jsonObject;
    }

    /**
     * java对象转json字符串.
     * @param o
     * @return
     */
    public static String getJSONStr(Object o){
        JSONObject jsonObject = JSONObject.fromObject(o);;
        return jsonObject.toString();
    }

    /**
     * json字符串转java对象.
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getJSONStrToObject(String jsonStr,Class<T> t)  {
        JSONObject jsonobject = JSONObject.fromObject(jsonStr);

        T object = (T)JSONObject.toBean(jsonobject,t);

        return  object;
    }

    /**
     * 字符串转json对象.
     * @param jsonStr
     * @return
     */
    public static JSONObject getStrToJSONObject(String jsonStr){
        JSONObject jsonobject = JSONObject.fromObject(jsonStr);
        return jsonobject;
    }

}
