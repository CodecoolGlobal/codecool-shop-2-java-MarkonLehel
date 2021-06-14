package services;

import com.codecool.shop.model.Cart;
import com.codecool.shop.service.CartService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CartServiceTest {

    Cart mockCart = mock(Cart.class);
    CartService cartService = new CartService(mockCart);



    @Test
    void getAllCartItems() {


    }

    @Test
    void getTotalCartPrice() {
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