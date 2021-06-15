package services;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    Product mockProduct;
    ProductDao mockProductDao;
    ProductCategory mockProductCategory;
    ProductCategoryDao mockProductCategoryDao;

    List<Product> mockProducts;

    ProductService productService;

    @BeforeEach
    void setUp() {
        mockProduct = mock(Product.class);
        mockProductCategory = mock(ProductCategory.class);

        mockProductDao = mock(ProductDao.class);
        mockProductCategoryDao = mock(ProductCategoryDao.class);

        mockProducts = new LinkedList<>(
                Arrays.asList(
                        mock(Product.class),
                        mock(Product.class),
                        mock(Product.class)));

        productService = new ProductService(mockProductDao, mockProductCategoryDao);
    }

    @Test
    public void getProductName_ID1_ReturnsTrue() {
        when(mockProductDao.find(1)).thenReturn(mockProduct);
        assertEquals(mockProduct, productService.getProduct(1));
    }

    @Test
    public void getProductCategory_ID1_ReturnsTrue() {
        when(mockProductCategoryDao.find(1)).thenReturn(mockProductCategory);
        assertEquals(mockProductCategory, productService.getProductCategory(1));
    }

    @Test
    public void getProductsForCategory_ID1_ReturnsTrue() {
        when(mockProductCategoryDao.find(1)).thenReturn(mockProductCategory);
        when(mockProductDao.getBy(mockProductCategoryDao.find(1))).thenReturn(mockProducts);

        assertEquals(mockProducts, productService.getProductsForCategory(1));
    }
}