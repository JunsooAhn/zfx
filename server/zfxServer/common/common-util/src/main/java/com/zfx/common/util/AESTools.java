package com.zfx.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.alibaba.fastjson.JSON;

public class AESTools {

	private final static String secretKey = "";

	private final static byte[] ivBytes = "".getBytes();

	private final static String ENCODING = "utf-8";

	private static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
		return new String(decryptData, ENCODING);
	}

	private static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
		return Base64.encode(encryptData);
	}

	public static <T> T decrypt(String jsonParam, Class<T> resultType) throws Exception {
		jsonParam = URLDecoder.decode(jsonParam, ENCODING);
		jsonParam = decode(jsonParam);
		return JSON.parseObject(jsonParam, resultType);
	}

	public static String encrypt(Object obj) throws Exception {
		String jsonStr = JSON.toJSONString(obj);
		jsonStr = encode(jsonStr);
		return URLEncoder.encode(jsonStr, ENCODING);
	}
}
