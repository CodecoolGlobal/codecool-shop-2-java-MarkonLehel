ALTER TABLE IF EXISTS ONLY product
    DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE,
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;

ALTER TABLE IF EXISTS ONLY product_category
    DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;

ALTER TABLE IF EXISTS ONLY supplier
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;


DROP TABLE IF EXISTS product;
CREATE TABLE product
(

    id               serial NOT NULL PRIMARY KEY,
    name             text,
    description      text,
    price            float,
    currency         text,
    product_category int,
    supplier         int
);


DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id          serial NOT NULL PRIMARY KEY,
    name        text,
    department  text,
    description text
);

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier
(
    id          serial NOT NULL PRIMARY KEY,
    name        text,
    description text
);

INSERT INTO supplier (name, description)
VALUES ('Starlight Industries', 'Expedition spaceships'),
       ('Goa''uld', 'Expedition spaceships');

INSERT INTO product_category (name, department, description)
VALUES ('Freighter','Spaceship', 'Ships used for transoprtation of cargo'),
       ('Fighter','Spaceship', 'Small spacecraft mainly designed for combat');

INSERT INTO product (name, price, currency, description, product_category, supplier)
VALUES ('Millennium Falcon', 5000, 'USD', 'The Millennium Falcon, originally designated YT 492727ZED and formerly known as the Stellar Envoy, was a Corellian YT-1300f light freighter most famously used by the smugglers Han Solo and Chewbacca, during and following the Galactic Civil War. It is the fastest ship in the Galaxy and a best ride, if you are in smuggling.0',
        1, 1);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (id) REFERENCES product_category (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (id) REFERENCES supplier (id);

ALTER TABLE ONLY product_category
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (id) REFERENCES product_category (id);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (id) REFERENCES supplier (id);