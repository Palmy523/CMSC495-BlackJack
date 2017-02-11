package com.blackjack.server.service;

import java.security.*;
import java.math.*;

public class MD5EncryptionService {

	
	private static final String salt = ":alj382na";
	
	/**
	 * Performs MD5 encryption of strings
	 * @param value the string value to encrypt
	 * @return an MD5 encrypted string of the value
	 */
	public static String encrypt(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");

			byte[] fingerprint = md.digest(value.getBytes());
			BigInteger digestNum = new BigInteger(1, fingerprint);
			String md5hash = digestNum.toString(16);
			return md5hash.concat(salt);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
