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
	  public String SendMail(String to, String subject, String Msgbody) {
	        String result = null;
	        // Sender's email ID needs to be mentioned
	        String from = "radnsr@yahoo.com";
	        String pass = "0716338903";
	    // Recipient's email ID needs to be mentioned.

	        String host = "smtp.mail.yahoo.com";

	        // Get system properties
	        Properties properties = System.getProperties();
	        // Setup mail server
	        properties.put("mail.smtp.starttls.enable", "false");
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.user", from);
	        properties.put("mail.smtp.password", pass);
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "false");

	        // Get the default Session object.
	        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO,
	                    new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject(subject);

	            // Now set the actual message
	            message.setText(Msgbody);

	            // Send message
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	            System.out.println("Sent message successfully....");
	            result = "success";
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	        return result;
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
	        System.out.println("CODE: " + str);
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