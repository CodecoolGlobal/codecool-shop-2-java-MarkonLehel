package com.codecool.shop.service;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.UUID;

public class CartService {
    private Cart shoppingCart;

    public CartService(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<CartItem> getAllCartItems() {
        return shoppingCart.getCartItems();
    }

    public float getTotalCartPrice() {
        return shoppingCart.getTotalPrice();
    }

    public void addCartItem(Product product) {
        shoppingCart.addItem(product);
    }

    public void removeCartItem(Product product) {
        shoppingCart.removeItem(product);
    }

    public Cart getShoppingCart(){
        return shoppingCart;
    }

    public UUID getCartOrderID() {
        return shoppingCart.getOrderID();
    }

    public void clearCart() {
        shoppingCart.clear();
    }


}
