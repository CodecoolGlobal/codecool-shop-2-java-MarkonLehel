package services;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductCategoryTest {

    ProductCategoryDaoMem mockCategoryDao;
    ProductCategoryService productCategoryService;
    List<ProductCategory> mockCategories;

    @BeforeEach
    void beforeEach() {
        mockCategoryDao = mock(ProductCategoryDaoMem.class);
        productCategoryService = new ProductCategoryService(mockCategoryDao);
        mockCategories = Arrays.asList(
                mock(ProductCategory.class),
                mock(ProductCategory.class),
                mock(ProductCategory.class));
    }

    @Test
    void getAllProductCategories_ReturnsAllCategories() {
        when(mockCategoryDao.getAll()).thenReturn(mockCategories);

        assertEquals(mockCategories, productCategoryService.getAllProductCategories());
    }
}
