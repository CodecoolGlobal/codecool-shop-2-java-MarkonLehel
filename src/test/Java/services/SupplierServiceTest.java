package services;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SupplierServiceTest {
    SupplierDao mockSupplierDao;
    Supplier mockSupplier;
    SupplierService supplierService;

    @BeforeEach
    void beforeEach() {
        mockSupplierDao = mock(SupplierDao.class);
        mockSupplier = mock(Supplier.class);
        supplierService = new SupplierService(mockSupplierDao);
    }

    @Test
    void getSupplier_findExistingSupplier_returnTrue() {
        when(mockSupplierDao.find(1)).thenReturn(mockSupplier);
        assertEquals(mockSupplier, supplierService.getSupplier(1));
    }

    @Test
    void getAllSuppliers() {
    }
}