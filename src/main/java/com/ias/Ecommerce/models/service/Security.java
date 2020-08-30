package com.ias.Ecommerce.models.service;

import java.security.MessageDigest;

public class Security {
	
	public static String sha256(String base,String trash) {
		String password=base+trash;
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(password.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	public static String generateTempPassword() {
		String tmp = "";
		for (int i=0; i<5; i++){
			int codigoAscii = (int)Math.floor(Math.random()*(122 - 97)+97); 
		    tmp = tmp + (char)codigoAscii; 
		} 
		return tmp;  
	}

}
