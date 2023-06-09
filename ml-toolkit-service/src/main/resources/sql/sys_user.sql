CREATE TABLE `sys_user`
(
    `id`          decimal(20, 0) NOT NULL,
    `name`        varchar(100) DEFAULT NULL COMMENT '名称',
    `account`     varchar(50)  DEFAULT NULL COMMENT '账号',
    `password`    varchar(50)  DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `status`      char(1)      DEFAULT NULL COMMENT '类型 0正常， 1 禁用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8