drop TABLE sys_user;
CREATE TABLE sys_user(
   `id` VARCHAR(50) PRIMARY KEY ,
   `user_name` VARCHAR(20),
   `user_sex` VARCHAR(2),
   `user_age` INT,
	 `user_number` INT DEFAULT 123456,
	 `user_password` VARCHAR(50),
	 `user_mailbox` VARCHAR(200),
	 `mailbox_index` TINYINT DEFAULT 0
);
select * from sys_user;

insert into sys_user VALUES('1','admin','男',26,123456,MD5('123456'),'9444@qq.com',1);
insert into sys_user VALUES('15','admin','男',26,123457,MD5('123456'),'9444@qq.com',0);

drop TABLE sys_slideshow;
CREATE TABLE sys_slideshow(
   `id` VARCHAR(50) PRIMARY KEY ,
   `url` VARCHAR(200),
   `state` int,
   `create_time` datetime,
   `create_user_id` VARCHAR(50),
	 `update_time` datetime,
   `update_user_id` VARCHAR(50),
   `rank` INT,
	 `remark` VARCHAR(500)
);
select * from sys_slideshow;
