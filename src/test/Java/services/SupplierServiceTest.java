package services;

import com.codecool.shop.dao.SupplierDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SupplierServiceTest {
    SupplierDao mockSupplierDao;

    @BeforeEach
    void beforeEach() {
        mockSupplierDao = mock(SupplierDao.class);
    }

    @Test
    void getSupplier() {
    }

    @Test
    void getAllSuppliers() {
    }
}