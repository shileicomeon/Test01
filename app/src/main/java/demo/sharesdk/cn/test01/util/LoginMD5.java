package demo.sharesdk.cn.test01.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class LoginMD5 {
    /**
     * 对外提供getMD5(String)方法
     * @author randyjia
     *
     */
        public static String getMD5(String val){
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return getString(m);
        }
        private static String getString(byte[] b){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < b.length; i ++){
                sb.append(b[i]);
            }
            return sb.toString();
        }
}
