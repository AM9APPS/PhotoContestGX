package com.gxzzb.gxphotocontest.support.bins;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SupportMD5 {

	String result16;
	String result32;

	public SupportMD5(String string) {
		MD5(string);
	}

	private void MD5(String string) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(string.getBytes());
			byte b[] = messageDigest.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}

			result16 = buf.toString();
			System.out.println("result: " + buf.toString());// 32位的加密
			result32 = buf.toString().substring(8, 24);
			System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String get16MD5() {
		return result16;
	}

	public String get32MD5() {
		return result32;

	}

}
