CREATE TABLE `categories` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(100) NOT NULL,
  `cat_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `products` (
  `prod_id` int(11) NOT NULL,
  `prod_name` varchar(60) NOT NULL,
  `prod_description` varchar(200) DEFAULT NULL,
  `prod_price` double(5,2) DEFAULT NULL,
  `prod_unit` varchar(50) DEFAULT NULL,
  `prod_category` int(11) DEFAULT NULL,
  `prod_color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `prod_cat_fk` (`prod_category`),
  CONSTRAINT `prod_cat_fk` FOREIGN KEY (`prod_category`) REFERENCES `categories` (`cat_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `product_images` (
  `image_id` int(11) NOT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `prod_uri` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `img_prod_fk_idx` (`prod_id`),
  CONSTRAINT `img_prod_fk` FOREIGN KEY (`prod_id`) REFERENCES `products` (`prod_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `salutation` varchar(45) DEFAULT NULL,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_total` double DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_cust_fk_idx` (`customer_id`),
  CONSTRAINT `order_cust_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `order_details` (
  `order_detail_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_color` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `prod_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `orderdetail_order_fk_idx` (`order_id`),
  KEY `orderdetail_prod_fk_idx` (`product_id`),
  KEY `ord_prod_fk` (`prod_id`),
  CONSTRAINT `ord_prod_fk` FOREIGN KEY (`prod_id`) REFERENCES `products` (`prod_id`),
  CONSTRAINT `detail_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `detail_prod_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`prod_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `indox_inventory` (
  `inventory_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `inventory_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`inventory_id`),
  KEY `inv_prod_fk_idx` (`product_id`),
  CONSTRAINT `inv_prod_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`prod_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
