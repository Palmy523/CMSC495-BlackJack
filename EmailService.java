package com.target.javamailTest;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailServices {
    Properties properties;

  public EmailServices() {
            // Get system properties
            properties = System.getProperties();

            // Setup mail server
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
      }

    public boolean sendEmail(String email, String from, String subject, String messageText, final String googleUsername, final String googlePassword) {
      boolean success = false;
        // Get the default Session object.
        Session session = Session.getInstance(properties,
            new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(googleUsername, googlePassword);
          }
          });

      try {
        // Create a default MimeMessage object.
          MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject(subject);

           // Now set the actual message
          message.setText(messageText);

          // Send message
          Transport.send(message, googleUsername, googlePassword);
      } catch(Exception ex) {
        System.out.println(ex.toString());
        success = false;
      }
        return success;
    }
  }
