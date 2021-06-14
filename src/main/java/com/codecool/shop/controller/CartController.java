package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    private final ProductDaoMem pdm = ProductDaoMem.getInstance();
    private final ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();

    private final CartService cartService = new CartService(Cart.getInstance());
    private final ProductService productService = new ProductService(pdm, pcd);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonObject returnJson = new JsonObject();
        JsonArray cartItemsArray = new JsonArray();

        for (CartItem item : cartService.getAllCartItems()) {
            JsonObject cartItem = new JsonObject();
            cartItem.addProperty("id", item.getProduct().getId());
            cartItem.addProperty("name", item.getProduct().getName());
            cartItem.addProperty("quantity", item.getQuantity());
            cartItem.addProperty("price", item.getProduct().getDefaultPrice());
            cartItem.addProperty("subtotal", item.getSubTotalPrice());
            cartItem.addProperty("currency", item.getProduct().getDefaultCurrency().toString());
            cartItemsArray.add(cartItem);
        }
        returnJson.add("items", cartItemsArray);
        returnJson.addProperty("totalPrice", cartService.getTotalCartPrice());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(returnJson.toString());
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonCartItem = Util.getRequestData(req);
        int pID = jsonCartItem.get("productID").getAsInt();

        cartService.addCartItem(productService.getProduct(pID));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonObject jsonCartItem = Util.getRequestData(req);
        int pID = jsonCartItem.get("productID").getAsInt();

        cartService.removeCartItem(productService.getProduct(pID));
    }
}
