package com.target.javamailTest;

public class SendMail {

    public static void main(String[] args) {
          EmailServices eSvc = new EmailServices();
          String googleUsername = args[0];
          String googlePassword = args[1];

          System.out.println("Connecting to Google with " + googleUsername + "...");

          eSvc.sendEmail("stephaniengufor@gmail.com", "celestineamandou@yahoo.com",
                "It finally works?",
                "Hey team, we are doing an awesome job so far...",
                googleUsername, googlePassword);
      }
}
