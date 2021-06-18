ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE,
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.product_category
    DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;


DROP TABLE IF EXISTS public.product;
CREATE TABLE public.product
(

    id               serial NOT NULL PRIMARY KEY,
    name             text,
    description      text,
    price            float,
    currency         text,
    product_category int,
    supplier         int
);


DROP TABLE IF EXISTS public.product_category;
CREATE TABLE public.product_category
(
    id          serial NOT NULL PRIMARY KEY,
    name        text,
    department  text,
    description text
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier
(
    id          serial NOT NULL PRIMARY KEY,
    name        text,
    description text
);

INSERT INTO supplier (name, description)
VALUES ('Starlight Industries', 'Expedition spaceships'),
       ('Goa''uld', 'Expedition spaceships'),
       ('Corellian Engineering Corporation', 'Long time manufacturer of reliable vessels'),
       ('Qimg Industries', 'Specialists of modern spaceship design and the latest technologies');

INSERT INTO product_category (name, department, description)
VALUES ('Freighter', 'Spaceship', 'Ships used for transoprtation of cargo'),
       ('Fighter', 'Spaceship', 'Small spacecraft mainly designed for combat'),
       ('Frigate', 'Spaceship', 'Larger spceships designed to protect other ships of the fleet'),
       ('Mothership', 'Spaceship', 'Big ships that are mainly used for housing smaller craft'),
       ('Battleship', 'Spaceship',
        'Massive spceships created as a main damage dealer of a fleet, carrying immerse weaponry'),
       ('Scout', 'Spaceship', 'Smaller ships created specifically to do reconnaissance and target acquisition');

INSERT INTO product (name, price, currency, description, product_category, supplier)
VALUES ('Millennium Falcon', 5000, 'USD',
        'The Millennium Falcon, originally designated YT 492727ZED and formerly known as the Stellar Envoy, was a Corellian YT-1300f light freighter most famously used by the smugglers Han Solo and Chewbacca, during and following the Galactic Civil War. It is the fastest ship in the Galaxy and a best ride, if you are in smuggling.0',
        1, 3),
       ('Normandy class frigate', 18200, 'USD',
        'The Normandy is equipped with a standard system of kinetic barrier shielding. Kinetic barriers are specialized mass effect fields that halt incoming projectiles. ... By rotationally firing their mass effect field projectors, the ship creates rapidly oscillating kinetic barriers instead of static ones."',
        3, 1),
       ('Prometheus Expedition Carrier', 47535, 'USD',
        'U.S.C.S.S. Prometheus Ship was a pioneering starship built to travel into deep space, tasked with the mission to discover the truth of mankind’s true origin.',
        4, 1),
       ('Ha''tak Mothership', 39500, 'USD',
        'A Goa''uld mothership, tetrahedral in shape. Ha''taks facilitate the transport of Goa''uld and their Jaffa armies. They are significant forces in attacking worlds from space, capable of atmospheric flight and landing on a planet''s surface.',
        4, 2),
       ('Stinger Starfigther Gen I', 3500, 'USD',
        'A small and nimble fighter, main armaments consist of 2 laser cannons.', 2, 3),
       ('Galactica', 85000, 'USD',
        'A bulky battleship, not the most recent design but it will serve its purpose.', 5, 3),
       ('Ares 14KIM', 43500, 'USD',
        'This frigate was built with the most recent technologies, has advanced shields and a new class of hyperdrive.',
        3, 4),
       ('Tel''tak', 12000, 'USD',
        'This ship is mainly used by the Goa''uld for transportation and landing troops.', 1, 2),
       ('Spectre AREC', 42080, 'USD',
        'A highly advanced recon craft equipped with several arrays of sensors, it can pinpoint the energy discharge of a weapon from low orbit.',
        6, 4),
       ('Coelier B505', 42080, 'USD',
        'Technically not a spaceship, but an advanced hovercraft capable of transporting large quantities of cargo in any environment.',
        1, 4);



ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category) REFERENCES product_category (id),
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier) REFERENCES supplier (id);

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (id) REFERENCES product_category (id);

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (id) REFERENCES supplier (id);