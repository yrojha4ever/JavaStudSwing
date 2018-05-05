package javastud.extend.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Utils {

	// https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
	public static String getMd5DigestValue(String plainText) {
		try {

			byte[] bytesOfMessage = plainText.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			return DatatypeConverter.printHexBinary(md.digest(bytesOfMessage));

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static void main(String[] args) {
		String result = Utils.getMd5DigestValue("java123");
		System.out.println(result);
	}
}
