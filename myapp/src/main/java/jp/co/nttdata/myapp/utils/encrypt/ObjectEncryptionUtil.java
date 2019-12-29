package jp.co.nttdata.myapp.utils.encrypt;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectEncryptionUtil {

    public ObjectEncryptionUtil(List<String> encryptFieldNames) {
        this.encryptFieldNames = encryptFieldNames;
    }

    private  List<String> encryptFieldNames;

    public <T>T encryptObject(T o){
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field:fields){
            if(isFieldName(field.getName())){
                field.setAccessible(true);
                try {
                   String str= field.get(o).toString();
                   field.set(o, AESUtil.encrypt(str));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    public <T>T encryptObjectByToken(T o,String token){
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field:fields){
            if(isFieldName(field.getName())){
                field.setAccessible(true);
                try {
                    String str= field.get(o).toString();
                    field.set(o,AESUtil.encryptByToken(str,token));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }
    private  boolean isFieldName(String fieldName){

        for(String name:encryptFieldNames){
            if(fieldName.equals(name)){
                return  true;
            }
        }
        return false;
    }

    public <T>T decryptObject(T o){
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field:fields){
            if(isFieldName(field.getName())){
                field.setAccessible(true);
                try {
                    String str= field.get(o).toString();
                    field.set(o,AESUtil.decrypt(str));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }
    public <T>T decryptObjectByToken(T o,String token){
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field:fields){
            if(isFieldName(field.getName())){
                field.setAccessible(true);
                try {
                    String str= field.get(o).toString();
                    field.set(o,AESUtil.decryptByToken(str,token));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }
}
