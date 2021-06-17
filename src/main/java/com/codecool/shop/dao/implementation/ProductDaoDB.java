package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB extends DatabaseConnection implements ProductDao {
    private static ProductDaoDB instance;
    private ProductCategoryDaoDB productCategoryDaoDB = ProductCategoryDaoDB.getInstance();
    private SupplierDaoDB supplierDaoDB = SupplierDaoDB.getInstance();

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                        SELECT name, price, currency, product_category, supplier
                         FROM product WHERE id = ?""";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString(1);
            String description = rs.getString(2);
            float price = rs.getFloat(3);
            String currency = rs.getString(4);
            ProductCategory productCategory = productCategoryDaoDB.find(rs.getInt(5));
            Supplier supplier = supplierDaoDB.find(rs.getInt(6));
            Product product = new Product(name, price, currency,description, productCategory, supplier);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading product with id: " + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, price, currency, description, product_category, supplier FROM product ORDER BY id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(rs.getString(1),
                        rs.getFloat(2),
                        rs.getString(3),
                        rs.getString(4),
                        productCategoryDaoDB.find(rs.getInt(5)),
                        supplierDaoDB.find(rs.getInt(6)));
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products: " + e);
        }
        return result;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
