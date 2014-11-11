package org.warrior.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA256 {
	/**
	 *  使用SHA256 进行加密，直接将加密的内容存入数据库，防止用户篡改
	 * @param pwd
	 * @return
	 */
	public static String encode(String pwd) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest=md.digest(pwd.getBytes());
		return new String(digest);
	}

	public static void main(String args[]) throws NoSuchAlgorithmException{ 
		String password="123456";
		String digest1=SHA256.encode(password);
		System.out.println(digest1);
		String digest2=SHA256.encode("123456");
		System.out.println(digest2);
		System.out.println("digest1 equals digest2 : "+digest1.equals(digest2));
	}
}