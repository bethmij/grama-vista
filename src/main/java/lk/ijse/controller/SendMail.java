package lk.ijse.controller;


import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
    public static void main(String[] args) {

        System.out.println("preparing to send message ...");
        String message = "Civil ID  - C00";
        String subject = "Grama Vista : civil data renewal";
        String to = "bjayamila@gmail.com";
        String from = "gramavista@gmail.com";

		sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,from);
    }

    //this is responsible to send the message with attachment
    private static void sendAttach(String message, String subject, String to, String from) {

        //Variable for gmail
        String host="smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object..
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gramavista@gmail.com", "tcnclclmcnpcxpvj");
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            //attachement..

            //file path
            String path="C:\\Users\\bethm\\Downloads\\REQUEST LETTER .pdf";


            MimeMultipart mimeMultipart = new MimeMultipart();
            //text
            //file

            MimeBodyPart textMime = new MimeBodyPart();

            MimeBodyPart fileMime = new MimeBodyPart();

            try {

                textMime.setText(message);

                File file=new File(path);
                fileMime.attachFile(file);


                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);


            } catch (Exception e) {

                e.printStackTrace();
            }


            m.setContent(mimeMultipart);


            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);



        }catch (Exception e) {
            e.printStackTrace();
        }








        System.out.println("Sent success...................");


    }

    //this is responsible to send email..
    private static void sendEmail(String message, String subject, String to, String from) {

        //Variable for gmail
        String host="smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object..
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gramavista@gmail.com", "tcnclclmcnpcxpvj");
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            //adding text to message
            m.setText(message);

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

            System.out.println("Sent success...................");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }







        //this is responsible to send email..
       /* private static void sendEmail(String message, String subject, String to, String from) {

            //Variable for gmail
            String host="smtp.gmail.com";

            //get the system properties
            Properties properties = System.getProperties();
            System.out.println("PROPERTIES "+properties);

            //setting important information to properties object

            //host set
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            //Step 1: to get the session object..
            Session session=Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("techsoftindia2018@gmail.com", "Durgesh@123");
                }



            });

            session.setDebug(true);

            //Step 2 : compose the message [text,multi media]
            MimeMessage m = new MimeMessage(session);

            try {

                //from email
                m.setFrom(from);

                //adding recipient to message
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                //adding subject to message
                m.setSubject(subject);


                //adding text to message
                m.setText(message);

                //send

                //Step 3 : send the message using Transport class
                Transport.send(m);

                System.out.println("Sent success...................");


            }catch (Exception e) {
                e.printStackTrace();
            }

        }*/
    }




