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
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE INDEX user_company_id_index ON user(company_id);

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
  `employees_id` bigint(20) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE UNIQUE INDEX company_employees_company_id_uniq_index on company_employees(company_id);
