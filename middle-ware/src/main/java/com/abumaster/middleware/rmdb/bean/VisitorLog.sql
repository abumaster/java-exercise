-- auto Generated on 2020-10-20
-- DROP TABLE IF EXISTS visitor_log;
CREATE TABLE visitor_log(
	id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
	msg VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'msg',
	update_time BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'updateTime',
	insert_date DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'insertDate',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'visitor_log';
