DROP SCHEMA if EXISTS order_management;
CREATE SCHEMA order_management;
USE order_management;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'avion2015';

CREATE TABLE order_management.client (
  id INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(45) NULL,
  name VARCHAR(45) NULL,
  address VARCHAR(45) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

CREATE TABLE order_management.product (
  id INT NOT NULL AUTO_INCREMENT,
  category VARCHAR(45) NULL,
  productName VARCHAR(45) NULL,
  stock INT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

CREATE TABLE order_management.order (
  id INT NOT NULL AUTO_INCREMENT,
  productId INT NULL,
  amount INT NULL,
  clientId INT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

INSERT INTO order_management.client (firstName, name, address) VALUES ("Steven", "Foster", "3996  Grant View Drive");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Miranda", "Morris", "2185  Ferguson Street");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Wilson", "Brown", "4736  Davis Place");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Ryan", "Baker", "3922  Java Lane");
INSERT INTO order_management.client (firstName, name, address) VALUES ("April", "Elliott", "3805  Bombardier Way");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Jacob", "Douglas", "3590  Steve Hunt Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Samantha", "Douglas", "3590  Steve Hunt Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Garry", "Myers", "4003  Prospect Valley Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Jenna", "Miller", "3874  Sarah Drive");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Rafael", "Thompson", "2672  Hide A Way Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Amanda", "Carroll", "179  Nickel Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Patrick", "Alexander", "1185  Tori Lane");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Cherry", "Wells", "845  Mayo Street");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Ted", "Perry", "1996  Alpha Avenue");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Alina", "Clark", "3514  Waldeck Street");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Deanna", "Baker", "4301  Lost Creek Road");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Lyndon", "Kelley", "4494  Simpson Street");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Vincent", "Kelley", "4494  Simpson Street");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Chloe", "Barnes", "1349  Snyder Avenue");
INSERT INTO order_management.client (firstName, name, address) VALUES ("Maddie", "Barnes", "1349  Snyder Avenue");

INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Vitelotte", 500);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Yukon Gold", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Bintje", 1500);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Kennebec", 2000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Russet Burbank", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Laura", 500);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Potato", "Kerr's Pink", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Cherry", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "San Marzano", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Kumato", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Better Boy", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Cherokke Prurple", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Early Girl", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Roma", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Tigerella", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Tomato", "Green Zebra", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "Armenian", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "Diva", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "English Telegraph", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "Lemon", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "Persian", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Cucumber", "Suyo Long", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pepper", "Sweet California Wonder", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pepper", "Cabernet Bell", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pepper", "Islander Bell", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pepper", "Golden Bell", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pepper", "Sweet Chocolate", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Red Delicious", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Gala", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Fuji", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Granny Smith", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Honeycrisp", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Cripps Pink", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Golden Delicious", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Ambrosia ", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Autumn Glory ", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Braeburn", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Apple", "Breeze", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Red Anjou", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Asian", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Williams", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Bosc", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Comice", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Concorde", 1000);
INSERT INTO order_management.product (category, productName, stock) VALUES ("Pear", "Green Anjou", 1000);

SELECT * FROM order_management.client;
SELECT * FROM order_management.product;

SELECT order_management.order.id, order_management.client.firstName, order_management.client.name, order_management.product.category, order_management.product.productName, order_management.order.amount
FROM order_management.product, order_management.client, order_management.order
WHERE order_management.order.productId = order_management.product.id AND order_management.order.clientId = order_management.client.id;