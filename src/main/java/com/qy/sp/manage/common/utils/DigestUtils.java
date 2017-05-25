package com.qy.sp.manage.common.utils;

import java.security.MessageDigest;

public class DigestUtils {

	public final static String MD5 = "MD5";
	public final static String MD2 = "MD2";
	public final static String SHA_1 = "SHA-1";
	public final static String SHA_256 = "SHA-256";
	public final static String SHA_384 = "SHA-384";
	public final static String SHA_512 = "SHA-512";

	public static String encrypt(String algorithm, String openPassword) {
		if (openPassword == null)
			openPassword = "";
		try {
			String s = digest(algorithm, openPassword.getBytes("UTF-8"));
			return s;
		} catch (Exception exc) {
			String s1 = null;
			return s1;
		}
	}

	private static String digest(String algorithm, byte input[]) throws Exception {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte ret[] = md.digest(input);
		return new String(Base64.encode(ret));
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.encrypt(DigestUtils.SHA_1, "123456"));
	}
}
