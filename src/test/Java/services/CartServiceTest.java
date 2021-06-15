package services;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CartServiceTest {

    Cart mockCart;
    CartService cartService;
    List<CartItem> mockCartItems;

    @BeforeEach
    void beforeEach() {
        mockCart = mock(Cart.class);
        cartService = new CartService(mockCart);
        mockCartItems = new LinkedList<>(Arrays.asList(
                mock(CartItem.class),
                mock(CartItem.class),
                mock(CartItem.class)));
    }

    @Test
    void getAllCartItems_CorrectReturn_ReturnsTrue() {
        when(mockCart.getCartItems()).thenReturn(mockCartItems);
        assertEquals(mockCartItems, cartService.getAllCartItems());

    }

    @Test
    void getTotalCartPrice_CorrectPrice_ReturnsTrue() {
        when(mockCart.getTotalPrice()).thenReturn(500F);
        assertEquals(500F, cartService.getTotalCartPrice());
    }

    @Test
    void addCartItem_SuccessfulAddItem_ReturnsTrue() {
        Product mockProduct = mock(Product.class);
        doAnswer(invocationOnMock -> {
            Product mockProd = invocationOnMock.getArgument(0);
            CartItem item = new CartItem(mockProd, 1);
            mockCartItems.add(item);
            return null;
        }).when(mockCart).addItem(any(Product.class));

        cartService.addCartItem(mockProduct);

        assertEquals(4, mockCartItems.size());
        assertEquals(mockProduct, mockCartItems.get(3).getProduct());

    }

    @Test
    void removeCartItem_CartItemRemoval_ReturnsTrue() {
        doAnswer(invocationOnMock -> {
            mockCartItems.remove(mockCartItems.get(0));
            return null;
        }).when(mockCart).removeItem(any(Product.class));
        cartService.removeCartItem(mock(Product.class));
        assertEquals(2, mockCartItems.size());
    }

    @Test
    void getShoppingCart_ActualContent_ReturnsTrue() {
        when(cartService.getAllCartItems()).thenReturn(mockCartItems);
        assertEquals(mockCartItems, cartService.getAllCartItems());
    }

    @Test
    void getCartOrderID_ValidUUID_ReturnTrue() {
        when(mockCart.getOrderID()).thenReturn(UUID.fromString("c600d545-9dc3-4805-a584-7b15bde20671"));
        assertEquals(UUID.fromString("c600d545-9dc3-4805-a584-7b15bde20671"), cartService.getCartOrderID());
    }

    @Test
    void clearCart_EmptyCart_ReturnsTrue() {
        doAnswer(invocationOnMock -> {
            mockCartItems.clear();
            return null;
        }).when(mockCart).clear();
        cartService.clearCart();
        assertEquals(0, mockCartItems.size());
    }
}