package com.platform.soft.common.utils.encryptUtils;


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * 3DES加密工具类
 */
public class DesUtils {
	// 密钥  jujiaruanjianshunlu
	private final static String secretKey = "xazhidaxinxisoft666666123456";
	// 向量
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";
	
	
	public static void main(String[] args) throws Exception {
//		String logpath ="pI7+y+qrG29KuH8kon69frHkF0bvOftDnPbbWmyfKp9InGYWwdre9bsBET+a xaFi1IYk9tFSY1/5S7iI9wzLdE/h2GRYONkd1Obr1yPDkYUC0zw9aFPEr7Ea A0ULW9l8+Zeke2wjLlM=";
//		System.out.println(DesUtils.decrypt("mrBLv5u5/O0GTshjFNULTg=="));
		System.out.println(DesUtils.encrypt("18329908633"));
	}

	/**
	 * 3DES加密
	 * @param plainText 普通文本
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String plainText) {
		String encrptStr=null;
		if(plainText==null||plainText.equals("")||plainText.equals("null")){
			return null;
		}
		try {
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey= keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			encrptStr= Base64.encode(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encrptStr;
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptText){
		String decryptStr=null;
		if(encryptText==null||encryptText.trim().equals("")||encryptText.equals("null")){
			return null;
		}
		try {
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			Key deskey  = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
			decryptStr=new String(decryptData, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptStr;
	}
}