DROP TABLE IF EXISTS product;

CREATE TABLE product (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 name VARCHAR(250) NOT NULL,
 stock INTEGER(11) NOT NULL,
 price FLOAT(10) NOT NULL,
 image VARCHAR(250)
);

/* Do not modify this 3 products because are used for tests */
INSERT INTO product (name,stock,price,image) VALUES ('Vaso de Superman', 10 , 50, '/assets/img/vaso-pinta-superman-detalle.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('Llavero de Spiderman', 10 , 5, '/assets/img/llavero-spiderman.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('Gorra Hulk', 10 , 27, '/assets/img/gorra-hulk.jpg');
/* -- */

INSERT INTO product (name,stock,price,image) VALUES ('Remera La Mole', 10 , 45, '/assets/img/remera-mole.webp');
INSERT INTO product (name,stock,price,image) VALUES ('Mochila Superman', 10 , 99, '/assets/img/mochila-superman.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('Globo Iroman', 10 , 6, '/assets/img/globo-ironman.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('Buzo Batman', 0 , 125, '/assets/img/buzo-batman.jpeg');
INSERT INTO product (name,stock,price,image) VALUES ('Zapatilla Aquaman', 10 , 57, '/assets/img/zapa-aquaman.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('Taza Marvel', 10 , 17, '/assets/img/taza-marvel.webp');
INSERT INTO product (name,stock,price,image) VALUES ('Mascara Batman', 10 , 12, '/assets/img/batman-mascara.jpg');
INSERT INTO product (name,stock,price,image) VALUES ('PenDrive Ironman', 10 , 18, '/assets/img/pen-ironman.jpg');


