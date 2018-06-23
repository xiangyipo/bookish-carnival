drop TABLE sys_user
CREATE TABLE sys_user(
   `id` VARCHAR(50) PRIMARY KEY ,
   `user_name` VARCHAR(20),
   `user_sex` VARCHAR(2),
   `user_age` INT,
	 `user_number` VARCHAR(50),
	 `user_password` VARCHAR(50), 	
	 `user_mailbox` VARCHAR(200)
)
select * from sys_user

insert into sys_user VALUES('111','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('1','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('2','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('3','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('4','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('5','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('6','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('7','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('8','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('9','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('10','admin','男',26,'admin',MD5('admin'),'9444@qq.com');
insert into sys_user VALUES('11','admin','男',26,'admin',MD5('admin'),'9444@qq.com');


drop TABLE sys_slideshow
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
)
insert into sys_slideshow VALUES('13','8.jpg',1,NOW(),'admin',NOW(),'admin',1);

select MAX(rank) from sys_slideshow

SELECT * from sys_slideshow