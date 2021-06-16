package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Cart;
import com.codecool.shop.service.CartService;
import org.slf4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/success"})
public class SuccessController extends HttpServlet {
    private final Logger logger = Util.createLogger(SuccessController.class);
    private final CartService cartService = new CartService(Cart.getInstance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orderId", cartService.getCartOrderID());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        logger.info("Success page\n Order id: {}", cartService.getCartOrderID());
        engine.process("product/success.html", context, resp.getWriter());

    }
}

