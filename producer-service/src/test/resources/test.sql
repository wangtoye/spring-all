CREATE TABLE `user` (
  `id` BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR (30) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` INT (11) NOT NULL DEFAULT 0 COMMENT '年龄',
  `email` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '删除标记位 0有效 1删除',
  PRIMARY KEY (id)
);

INSERT INTO `user` (`name`, `age`, `email`) VALUES
('Jone', 18, 'test1@baomidou.com'),
('Jack', 20, 'test2@baomidou.com'),
('Tom', 28, 'test3@baomidou.com'),
('Sandy', 21, 'test4@baomidou.com'),
('Billie', 24, 'test5@baomidou.com');
