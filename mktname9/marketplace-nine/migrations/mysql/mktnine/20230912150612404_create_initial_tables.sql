CREATE TABLE `products`
(
    `id` smallint NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `price` decimal(10, 2) NOT NULL,
    `description` varchar(255) NOT NULL,
    `image` varchar(255) NOT NULL,
    `category` smallint NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `category_ibfk_1` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `categories`
(
    `id` smallint NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;