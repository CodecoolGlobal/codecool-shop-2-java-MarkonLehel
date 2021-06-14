package services;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.service.CartService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CartServiceTest {

    Cart mockCart = mock(Cart.class);
    CartService cartService = new CartService(mockCart);
    CartItem mockItem1 = mock(CartItem.class);
    CartItem mockItem2 = mock(CartItem.class);
    CartItem mockItem3 = mock(CartItem.class);


    List<CartItem> mockCartItems = Arrays.asList(mockItem1,mockItem2,mockItem3);



    @Test
    void getAllCartItems_CorrectReturn_ReturnsTrue() {
    when(mockCart.getCartItems()).thenReturn(mockCartItems);
    assertEquals(mockCartItems,cartService.getAllCartItems());

    }

    @Test
    void getTotalCartPrice_CorrectPrice_ReturnsTrue() {
        when(mockCart.getTotalPrice()).thenReturn(500F);
        assertEquals(500F, cartService.getTotalCartPrice());
    }

    @Test
    void addCartItem() {
    }

    @Test
    void removeCartItem() {
    }

    @Test
    void getShoppingCart() {
    }

    @Test
    void getCartOrderID_ValidUUID_ReturnTrue() {
        when(mockCart.getOrderID()).thenReturn(UUID.fromString("c600d545-9dc3-4805-a584-7b15bde20671"));
        assertEquals(UUID.fromString("c600d545-9dc3-4805-a584-7b15bde20671"), cartService.getCartOrderID());
    }

    @Test
    void clearCart() {
    }
}