package services;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceTest {
    SupplierDao mockSupplierDao;
    Supplier mockSupplier;
    SupplierService supplierService;
    List<Supplier> mockSuppliers;

    @BeforeEach
    void beforeEach() {
        mockSupplierDao = mock(SupplierDao.class);
        mockSupplier = mock(Supplier.class);
        supplierService = new SupplierService(mockSupplierDao);
        mockSuppliers = Arrays.asList(
                mock(Supplier.class),
                mock(Supplier.class),
                mock(Supplier.class));
    }

    @Test
    void getSupplier_findExistingSupplier_returnTrue() {
        when(mockSupplierDao.find(1)).thenReturn(mockSupplier);

        assertEquals(mockSupplier, supplierService.getSupplier(1));
    }

    @Test
    void getAllSuppliers_findAllExistingSuppliers_returnListOfSuppliers() {
        when(mockSupplierDao.getAll()).thenReturn(mockSuppliers);

        assertEquals(mockSuppliers, supplierService.getAllSuppliers());
    }
}