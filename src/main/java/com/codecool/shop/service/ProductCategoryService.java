package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryService {
    private ProductCategoryDao productCategoryDao;

    public ProductCategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryDao.getAll();
    }
}
