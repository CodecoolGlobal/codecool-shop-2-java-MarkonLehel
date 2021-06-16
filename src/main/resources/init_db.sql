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
    price            float,
    currency         text,
    product_category int,
    supplier         int
);


DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id          int NOT NULL PRIMARY KEY,
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



ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (id) REFERENCES product_category (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (id) REFERENCES supplier (id);

ALTER TABLE ONLY product_category
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (id) REFERENCES product_category (id);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (id) REFERENCES supplier (id);