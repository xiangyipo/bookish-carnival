CREATE TABLE sys_user(
   `id` VARCHAR(50) PRIMARY KEY ,
   `user_name` VARCHAR(20),
   `user_sex` VARCHAR(2),
   `user_age` INT,
	 `user_number` VARCHAR(50),
	 `user_password` VARCHAR(50),
	 `user_mailbox` VARCHAR(200)
)


insert into sys_user VALUES('admin','admin','男',26,'admin',MD5('admin'),'9444@qq.com')

CREATE TABLE sys_slideshow(
   `id` VARCHAR(50) PRIMARY KEY ,
   `url` VARCHAR(200),
   `state` int,
   `create_time` datetime,
   `create_user_id` VARCHAR(50),
	 `update_time` datetime,
   `update_user_id` VARCHAR(50),
   `rank` INT
)