package com.MySQL.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5SİFRE {
	
	public static String md5(String icerik) {
		try {
			MessageDigest MD=MessageDigest.getInstance("MD5");
			byte[] sifrelenmis= MD.digest(icerik.getBytes());
			BigInteger no=new BigInteger(1,sifrelenmis);
			String hashicerik=no.toString(16);
			while (hashicerik.length()<32) {
				hashicerik="0"+hashicerik;
				
				
			}
			return hashicerik;
		} 
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}
