package com.rigor.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import org.bouncycastle.util.encoders.Hex;

public class Methods {

	public String generateID(Session session,String prefix, String column, Class t) {
		String generateId = "";
	
		Criteria criteria = session.createCriteria(t).setProjection(Projections.max(column));
		String maxId = (String) criteria.uniqueResult();		
		int b = 0;
		if (maxId != null) {
			String a = maxId.substring(1);
			b = Integer.parseInt(a);
		}
		if (b <= 8) {
			b += 1;
			generateId += prefix + "000" + b;
			return generateId;
		} else {
			b += 1;
			generateId += prefix + "00" + b;
			return generateId;
		}
	}
	  
		//generate random password for new user
	    public String RandomCode() {
	        java.util.Random r = new java.util.Random();
	        int i = 1, n = 0;
	        char c;
	        String str = "";
	        for (int t = 0; t < 3; t++) {
	            while (true) {
	                i = r.nextInt(10);
	                if (i > 5 && i < 10) {

	                    if (i == 9) {
	                        i = 90;
	                        n = 90;
	                        break;
	                    }
	                    if (i != 90) {
	                        n = i * 10 + r.nextInt(10);
	                        while (n < 65) {
	                            n = i * 10 + r.nextInt(10);
	                        }
	                    }

	                    break;
	                }
	            }
	            c = (char) n;

	            str = String.valueOf(c) + str;
	        }
	        while (true) {
	            i = r.nextInt(10000000);
	            if (i > 999999) {
	                break;
	            }
	        }
	        str = str + i;
	        System.out.println("GENERATED CODE: " + str);
	        return str;
	    }

public String passwordHash(String password){
	 MessageDigest md;
	 String hash_value=null;
	try {
		md = MessageDigest.getInstance("SHA-256");

     byte[] temp = password.getBytes(); // convert password to byte 
     md.update(temp);
     byte[] svPass = md.digest();
      hash_value = new String(Hex.encode(svPass));// digest the password
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return hash_value;
}
}