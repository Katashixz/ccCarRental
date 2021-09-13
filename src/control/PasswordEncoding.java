package control;

import com.mysql.jdbc.log.LogFactory;
import sun.rmi.runtime.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//√‹¬Îº”√‹
public class PasswordEncoding {
    public  MessageDigest digest = null;
    public  String encode(String data) {
        if (digest == null)
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {

                nsae.printStackTrace();
            }
        digest.update(data.getBytes());
        return encodeHex(digest.digest());
    }

    private  String encodeHex(byte bytes[]) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 16)
                buf.append("0");
            buf.append(Long.toString(bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

}
