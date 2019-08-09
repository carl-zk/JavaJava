drop table if exists user;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `country_code` varchar(10) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `uuid` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2yuxsfrkkrnkn5emoobcnnc3r` (`company_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

drop table if exists company;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `building` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

drop table if exists company_employees;
CREATE TABLE `company_employees` (
  `company_id` bigint(20) NOT NULL,
  `employees_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_lg2r1rg13q18sa62l1y7un4or` (`employees_id`),
  KEY `FKd66w6jx84tydyd8sf9mpqh5je` (`company_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

