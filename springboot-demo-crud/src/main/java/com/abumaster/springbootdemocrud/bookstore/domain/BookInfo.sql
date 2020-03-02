-- auto Generated on 2020-02-21
-- DROP TABLE IF EXISTS book_info;
CREATE TABLE book_info(
	bid VARCHAR (50) NOT NULL COMMENT 'bid',
	book_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'bookName',
	book_desc VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'bookDesc',
	author VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'author',
	update_time VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'updateTime',
	details VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'details',
	PRIMARY KEY (bid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'book_info';
