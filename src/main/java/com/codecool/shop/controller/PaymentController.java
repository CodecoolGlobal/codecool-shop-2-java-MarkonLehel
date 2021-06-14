package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.service.CartService;
import com.google.gson.JsonObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    private final CartService cartService = new CartService(Cart.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Receiving request: " + req.getPathInfo());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        context.setVariable("cart", cartService.getShoppingCart());

        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonObject paymentData = Util.getRequestData(req);
        Files.write(Paths.get(cartService.getCartOrderID() + "pson" + ".json"), paymentData.toString().getBytes());

        System.out.println("loading success page");
        cartService.clearCart();
        resp.sendRedirect("/success");
//Mail
//        try {
//            MailController.getInstance().sendMail("lehel.markon@gmail.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }



}
