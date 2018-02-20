package com.xiteng.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class CookieEncryptTool {
	public static String encodeBase64(String cleartext) {
		try {
			cleartext=new String(Base64.encode(cleartext.getBytes("utf-8")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cleartext;
	}
	public static String decodeBase64(String ciphertext) {
		try {
			ciphertext=new String(Base64.decode(ciphertext.getBytes()),"utf-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ciphertext;
		
	}

}
