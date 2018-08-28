package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Arrays;

public class AddSignT {
//    1: 过滤掉数组里面的空值，并过滤[sign,key,token]键值；
//    2: 增加timestamp字段（注1）并加入请求参数参与签名；
//    3: 按字典序排序参数 ksort($params_array) 如：array(bb=>2,cc=>3,aa=>1,’dd’=>,’token’=>’12345678’) ksort得到
//      array(aa=>1,bb=>2,cc=>3)；
//    4： 将3中得到的参数数组拼凑成字符串 得到： aa=1&bb=2&cc=3[注 ：需要进行空值过滤]；
//    5: 在4的后面拼接固定字符串key 得到: aa=1&bb=2&cc=3&key=frapkkxookfjisksiakd26oo
//    6: 将5中得到的字符串进行md5
//    7: 将5中得到的字符串全部转为大写
    public static final String APP_KEY = "frapkkxookfjisksiakd26oo";
    public static final String KEY_MD5 = "MD5";

    /**
     * MD5加密
     *
     * @throws NoSuchAlgorithmException
     */
    private static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(KEY_MD5);
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    public static void sign(HashMap map){
        long now = System.currentTimeMillis() / 1000;
        map.put("timestamp", String.valueOf(now));

        String new_str = "";
        Object[] key_arr = map.keySet().toArray();
        Arrays.sort(key_arr);
        for  (Object key : key_arr) {
            new_str += key + "=" + map.get(key) + "&";
        }
        new_str = new_str + "key=" + APP_KEY;
        String sign = getMD5Str(new_str).toUpperCase();
        map.put("sign",sign);
        System.out.println(map);
    }

    public static String getSing(HashMap map){
        String new_str = "";
        Object[] key_arr = map.keySet().toArray();
        Arrays.sort(key_arr);
        for  (Object key : key_arr) {
            new_str += key + "=" + map.get(key) + "&";
        }
        new_str = new_str + "key=" + APP_KEY;
        String sign = getMD5Str(new_str).toUpperCase();

        return sign;
    }

    public static void main(String []args){
        HashMap map = new HashMap();
        map.put("brushed","1");
        map.put("deviceModel","oppor7sm");
        map.put("deviceName","OPPO");
        map.put("deviceToken","86787602332210513572489850");
        map.put("memorySize","32");
        map.put("openudid","867876023322105");
        map.put("password","qwe123");
        map.put("phone","13572489850");
        map.put("systemVersion","5.1.1");
        sign(map);
        System.out.println(map);
    }
}
