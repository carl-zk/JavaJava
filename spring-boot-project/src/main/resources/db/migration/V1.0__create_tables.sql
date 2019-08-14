drop table if exists user;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NOW(),
  `deleted` bit(1) DEFAULT FALSE,
  `updated_at` datetime DEFAULT NOW(),
  `version` int(11) NOT NULL,
  `country_code` varchar(10) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `uuid` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  last_modified_by varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE INDEX user_company_id_index ON user(company_id);

drop table if exists company;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NOW(),
  `deleted` bit(1) DEFAULT FALSE,
  `updated_at` datetime DEFAULT NOW(),
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
CREATE INDEX company_employees_cid ON company_employees(company_id);
CREATE INDEX company_employees_eid ON company_employees(employees_id);

drop table if exists revinfo;
create table revinfo (
  rev      integer not null auto_increment,
  revtstmp bigint(20),
  primary key (rev)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

drop table if exists user_aud;
create table user_aud (
  id           bigint(20) not null auto_increment,
  rev          integer not null,
  revtype      tinyint,
  country_code varchar(10),
  number       varchar(50),
  name         varchar(100),
  uuid         varchar(100),
  last_modified_by varchar(255),
  primary key (id, rev)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;
ALTER TABLE user_aud ADD CONSTRAINT user_aud_revinfo_rev_fk FOREIGN KEY (rev) REFERENCES revinfo (rev);