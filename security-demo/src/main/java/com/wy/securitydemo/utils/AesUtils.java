package com.wy.securitydemo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * 对称加密解密AES算法
 *
 * @author wangye
 */
public class AesUtils {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    /**
     * 初始化向量IV 长度为16/32位
     */
    private static final String IV = "A-16-Byte-String";


    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        //默认128，获得无政策权限后可为192或256
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 加密返回的数据转换成 String 类型
     *
     * @param content 明文
     * @param key     秘钥
     * @return 密文
     */
    public static String encrypt(String content, String key) {
        // 将返回的加密过的 byte[] 转换成Base64编码字符串  ！！！！很关键
        return base64ToString(encrypt(content.getBytes(), key.getBytes()));
    }

    /**
     * 将解密返回的数据转换成 String 类型
     *
     * @param content Base64编码的密文
     * @param key     秘钥
     * @return 明文
     */
    public static String decrypt(String content, String key) {
        // stringToBase64() 将 Base64编码的字符串转换成 byte[] 与base64ToString()配套使用
        byte[] bytes = decrypt(stringToBase64(content), key.getBytes());
        if (bytes == null) {
            return null;
        }
        return new String(bytes);
    }

    private static byte[] encrypt(byte[] content, byte[] keyBytes) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            return cipher.doFinal(content);
        } catch (Exception e) {
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

    private static byte[] decrypt(byte[] content, byte[] keyBytes) {
        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            return cipher.doFinal(content);
        } catch (Exception e) {
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

    /**
     * 字符串装换成 Base64
     */

    private static byte[] stringToBase64(String key) {
        return Base64.decodeBase64(key.getBytes());
    }

    /**
     * Base64装换成字符串
     */
    private static String base64ToString(byte[] key) {
        return new Base64().encodeToString(key);
    }

}