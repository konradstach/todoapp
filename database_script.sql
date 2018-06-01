CREATE  TABLE user (
user_id INT(11) auto_increment,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(256) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `date` date NOT NULL,
  `completed` boolean DEFAULT FALSE,
  `user_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),
  CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)

) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

INSERT INTO user(user_id,username,password, enabled)
VALUES (11,'anna', '$2a$04$hP86iuIw3UHjWEzI7dLb.OPU56.yYxXLJZXu6K9bB81FKW.Mh4WiK', 1);
INSERT INTO user(user_id,username,password, enabled)
VALUES (12,'robert', '$2a$04$hP86iuIw3UHjWEzI7dLb.OPU56.yYxXLJZXu6K9bB81FKW.Mh4WiK', 1);

INSERT INTO role(role_id,role)
VALUES (1,'ROLE_ADMIN');
INSERT INTO role(role_id,role)
VALUES (2,'ROLE_USER');

INSERT INTO user_role(user_id, role_id)
VALUES (11,2);
INSERT INTO user_role(user_id, role_id)
VALUES (12,2);

INSERT INTO task(id, title, description, date, completed, user_id)
VALUES (31,'task title 1', 'description 1', '2018-05-11', b'1', 11);
INSERT INTO task(id, title, description, date, completed, user_id)
VALUES (32,'task title 2', null, '2018-05-01', b'0', 11);
INSERT INTO task(id, title, description, date, completed, user_id)
VALUES (33,'task title 1', null, '2018-04-11', b'1', 12);
INSERT INTO task(id, title, description, date, completed, user_id)
VALUES (34,'task title 2', 'description 2', '2018-03-11', b'0', 12);



