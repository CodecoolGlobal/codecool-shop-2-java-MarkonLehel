package com.codecool.shop.config;

import com.codecool.shop.controller.DetailedController;
import com.codecool.shop.controller.Util;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ActiveDataSourceService;
import org.slf4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ActiveDataSourceService activeDataSourceService = ActiveDataSourceService.getInstance();
        try {
            activeDataSourceService.getConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (activeDataSourceService.getUseMemDao()) {
            initializeMemDao();
        }
        try {
            activeDataSourceService.init();
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
    }

    private void initializeMemDao() {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier starlight = new Supplier("Starlight Industries", "Expedition spaceships");
        Supplier goauld = new Supplier("Goa'uld", "Battleship and Jaffa manufacturer");
        Supplier CEC = new Supplier("Corellian Engineering Corporation", "Long time manufacturer of reliable cargo vessels");
        Supplier qimgIndustries = new Supplier("Qimg Industries", "Specialists of modern spaceship design and the latest technologies");

        supplierDataStore.add(starlight);
        supplierDataStore.add(goauld);
        supplierDataStore.add(CEC);
        supplierDataStore.add(qimgIndustries);


        //setting up a new product category
        ProductCategory freighter = new ProductCategory("Freighter", "Spaceship", "Ships used for transoprtation of cargo");
        ProductCategory fighter = new ProductCategory("Fighter", "Spaceship", "Small spacecraft mainly designed for combat");
        ProductCategory frigate = new ProductCategory("Frigate", "Spaceship", "Massive spceships designed with serious weaponry");
        ProductCategory mothership = new ProductCategory("Mothership", "Spaceship", "Big ships that are mainly used for housing smaller craft");
        ProductCategory battleship = new ProductCategory("Battleship", "Spaceship",
                "Massive spceships created as a main damage dealer of a fleet, carrying immerse weaponry");
        ProductCategory scout = new ProductCategory("Scout", "Spaceship", "Smaller ships created specifically to do reconnaissance and target acquisition");
        productCategoryDataStore.add(freighter);
        productCategoryDataStore.add(fighter);
        productCategoryDataStore.add(frigate);
        productCategoryDataStore.add(mothership);
        productCategoryDataStore.add(battleship);
        productCategoryDataStore.add(scout);


        //setting up products and printing it
        productDataStore.add(new Product("Millennium Falcon", 5000, "USD", "The Millennium Falcon, originally designated YT 492727ZED and formerly known as the Stellar Envoy, was a Corellian YT-1300f light freighter most famously used by the smugglers Han Solo and Chewbacca, during and following the Galactic Civil War. It is the fastest ship in the Galaxy and a best ride, if you are in smuggling.", freighter, CEC));
        productDataStore.add(new Product("Normandy class frigate", 18200, "USD", "The Normandy is equipped with a standard system of kinetic barrier shielding. Kinetic barriers are specialized mass effect fields that halt incoming projectiles. ... By rotationally firing their mass effect field projectors, the ship creates rapidly oscillating kinetic barriers instead of static ones.", frigate, starlight));
        productDataStore.add(new Product("Prometheus Expedition Carrier", 47535, "USD", "A Goa'uld mothership, tetrahedral in shape. Ha'taks facilitate the transport of Goa'uld and their Jaffa armies. They are significant forces in attacking worlds from space, capable of atmospheric flight and landing on a planet's surface.", mothership, starlight));
        productDataStore.add(new Product("Ha'tak Mothership", 39500, "USD", "U.S.C.S.S. Prometheus Ship was a pioneering starship built to travel into deep space, tasked with the mission to discover the truth of mankind???s true origin.", mothership, goauld));
        productDataStore.add(new Product("Stinger Starfigther Gen I", 3500, "USD", "A small and nimble fighter, main armaments consist of 2 laser cannons.", fighter, CEC));

        productDataStore.add(new Product("Galactica", 85000, "USD",
                "A bulky battleship, not the most recent design but it will serve its purpose.", battleship, CEC));
        productDataStore.add(new Product("Ares 14KIM", 43500, "USD",
                "This frigate was built with the most recent technologies, has advanced shields and a new class of hyperdrive",
                frigate, qimgIndustries));
        productDataStore.add(new Product("Tel'tak", 12000, "USD",
                "This ship is mainly used by the Goa'uld for transportation and landing troops.", freighter, goauld));
        productDataStore.add(new Product("Spectre AREC", 42080, "USD",
                "A highly advanced recon craft equipped with several arrays of sensors, it can pinpoint the energy discharge of a weapon from low orbit .",
                scout, qimgIndustries));
        productDataStore.add(new Product("Coelier B505", 42080, "USD",
                "Technically not a spaceship, but an advanced hovercraft capable of transporting large quantities of cargo in any environment.",
                freighter, qimgIndustries));


    }
}
