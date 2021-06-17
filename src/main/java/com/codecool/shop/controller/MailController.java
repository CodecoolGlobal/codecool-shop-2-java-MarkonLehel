package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import org.slf4j.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailController {
    private static final Logger logger = Util.createLogger(PaymentController.class);
    static Cart shoppingCart = Cart.getInstance();

    private static MailController instance;


    public static MailController getInstance() {
        if (instance == null)
            instance = new MailController();
        return instance;
    }

    private MailController() {
    }


    public static void sendMail(String address) throws MessagingException {

        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "spaceshipshop01@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("spaceshipshop01@gmail.com", ".spaceShipShop01.");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(false);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));

            // Set Subject: header field
            message.setSubject("Space ShipShop order confirmation");

            // Now set the actual message
            message.setText("Dear customer, \n Your order has been processed and will ship in the next 3 business days.\n" +
                    "Your order ID is " + shoppingCart.getOrderID());
            // Send message
            Transport.send(message);
            logger.info("Email has been sent successfully to " + address);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
