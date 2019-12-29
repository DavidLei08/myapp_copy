package jp.co.nttdata.myapp.utils.encrypt;



import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chao11.lei
 * @version 1.0
 * 安全加密用工具类
 */
public class SecurityUtil {

    public static String EnCodeMD5(String str)
    {
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte[] digest=md.digest();
        String myHash=DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }

}
