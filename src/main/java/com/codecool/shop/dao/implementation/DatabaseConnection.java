package com.codecool.shop.dao.implementation;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseConnection {
    private ProductDaoDB productDaoDB;
    private SupplierDaoDB supplierDaoDB;
    private ProductCategoryDaoDB productCategoryDaoDB;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        productDaoDB = new ProductDaoDB();
        supplierDaoDB = new SupplierDaoDB();
        productCategoryDaoDB = new ProductCategoryDaoDB();

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "codecoolshop";
        String user = "agocsvince";
        String password = "vinuvinu";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
