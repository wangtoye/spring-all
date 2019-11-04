package com.wy.securitydemo.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author : wangye
 * @date : 2019-11-04
 * Description: MD5加密工具类
 */
public class Smd5Utils {
    private final static String SMD5 = "{SMD5}";
    private final static int SHACODE_LENGTH = 8;

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        //String smd5 = getSMD5("123456");
//       // System.out.println(smd5);
//
//        boolean b = verifySHA("{SMD5}rHRUQXdCussUsbkNpHwfsqPdDJB5ur4q", "123456");
//        System.out.println(b);
//    }

    /**
     * 加密
     *
     * @param sec 明文
     * @return 密文
     */
    public static String getPwd(String sec) {
        try {
            byte[] b = new byte[8];
            Random random = new Random();
            random.nextBytes(b);

            String salt = bytesToHexString(b);
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(sec.getBytes());
            digest.update(b);
            byte[] bs = digest.digest();
            byte[] bsnew = new byte[bs.length + 8];
            System.arraycopy(b, 0, bsnew, bs.length, 8);
            System.arraycopy(bs, 0, bsnew, 0, bs.length);
            String base64 = Base64.encode(bsnew);
            return SMD5 + base64;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 密码校验
     *
     * @param realPwd  已存的密码
     * @param inputPwd 输入的密码
     * @return true false
     * @throws NoSuchAlgorithmException 算法不支持异常
     */
    public static boolean verifyPwd(String realPwd, String inputPwd) throws NoSuchAlgorithmException {
        // MessageDigest 提供了消息摘要算法，如 MD5 或 SHA，的功能，这里使用的是MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 解码BASE64
        byte[] realPwdByte = Base64.decode(realPwd.substring(SMD5.length()));
        byte[] shacode;
        byte[] salt;
        // 前20位是SHA-1加密段，20位后是最初加密时的随机明文
        if (realPwdByte.length <= SHACODE_LENGTH) {
            shacode = realPwdByte;
            salt = new byte[0];
        } else {
            shacode = new byte[realPwdByte.length - 8];
            salt = new byte[8];
            System.arraycopy(realPwdByte, 0, shacode, 0, realPwdByte.length - 8);
            System.arraycopy(realPwdByte, realPwdByte.length - 8, salt, 0, salt.length);
        }

        // 把用户输入的密码添加到摘要计算信息
        md.update(inputPwd.getBytes());
        // 把随机明文添加到摘要计算信息
        md.update(salt);

        // 按SSHA把当前用户密码进行计算
        byte[] inputPwdByte = md.digest();
        // 返回校验结果
        return MessageDigest.isEqual(shacode, inputPwdByte);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isBlank(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}