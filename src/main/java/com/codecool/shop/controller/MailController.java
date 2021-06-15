package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import org.slf4j.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailController {
    private final Logger logger = Util.createLogger(PaymentController.class);
    static Cart shoppingCart = Cart.getInstance();

    private static Properties prop = new Properties();
    private static MailController instance;



    public static MailController getInstance(){
        if (instance == null)
            instance = new MailController();
        return instance;
    }

    private MailController(){

    }

    private void init() {
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
    }

    static Session session = Session.getInstance(prop, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("Space ShipShop", ".spaceShipShop01.");
        }
    });

    public static void sendMail(String address) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("spaceshipshop@freemail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(address));
        message.setSubject("Space ShipShop order confirmation");

        String msg = "Thank you for purchasing from Space ShipShop!" +
                "Your order number is " + shoppingCart.getOrderID();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
