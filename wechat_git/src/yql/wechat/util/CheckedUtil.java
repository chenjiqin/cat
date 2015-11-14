package yql.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckedUtil {

	private static final String token = "yql";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[]{token, timestamp, nonce};
		//ÅÅÐò
		Arrays.sort(arr);
		//Éú´æ×Ö·û´®
		StringBuffer sb = new StringBuffer();
		for(String str : arr) {
			sb.append(str);
		}
		//sha1¼ÓÃÜ
		String temp = getFormattedText(sb.toString());
		return temp.equals(signature);
	}
	
	private static String getFormattedText(String str){  
		if(str == null || "".equals(str)) {
			return "";
		}
		
		char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());  
	        byte[] md = messageDigest.digest();
			int len = md.length;  
			char buf[] = new char[len * 2];
			int k = 0;
			for(int i = 0; i < len; i++) {
				byte byte0 = md[i];
				buf[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
				buf[k++] = HEX_DIGITS[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
        return "";
    } 
}
