--user

id
username
password
enabled

org_id (不要部门，直接商户分组，等同于部门)

gender(感性词|性别)
email
telephone
avatar
description 描述信息

last_login_time
last_password_reset_time

create_time
update_time
create_by
update_by
remark
is_deleted


--role
id
code
name
description

data_scope(数据范围，全部，本级，自定义)
role_level 角色级别

create_time
update_time



--user_role
user_id
role_id

--menu

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `cache` bit(1) DEFAULT b'0',
  `hidden` bit(1) DEFAULT b'0',
  `component_name` varchar(20) DEFAULT '-',
  `permission` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  
  create_time
update_time

  
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKqcf9gem97gqa5qjm4d3elcqt5` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


--role_menu
 `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',


--organization 组织（商户|类似部门）

CREATE TABLE `t_merchant_organization` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_name` varchar(255) DEFAULT NULL COMMENT '运营商名称',
  `enabled` bit(1) DEFAULT b'1' COMMENT '是否启用',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  description  运营商描述信息
   
  `contact_person` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(255) DEFAULT NULL COMMENT '联系人邮箱',
  `identity_number` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  
  `province` varchar(255) DEFAULT NULL COMMENT '省（独立行政区）',
  `city` varchar(255) DEFAULT NULL COMMENT '市（县）',
  `area` varchar(255) DEFAULT NULL COMMENT '区域（村）',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  
  `enterprise_name` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `unified_social_credit_code` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `business_license_photo` varchar(255) DEFAULT NULL COMMENT '营业执照照片（分辨率不得低于1000*1000）',
  `promote_code` varchar(255) DEFAULT NULL COMMENT '推广二维码',

  
  
  create_time
update_time
create_by
update_by
remark
is_deleted
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COMMENT='运营商组织信息管理表';


--job 商户职位
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `sort` bigint(20) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
    
  create_time
update_time
create_by
update_by
remark
is_deleted




--role_org 

`role_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,

--log
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `log_type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `request_ip` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `browser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17040 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



--dict
CREATE TABLE `dict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(100) NOT NULL COMMENT '字典编码',
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `sort` int(100) DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--dict_data
CREATE TABLE `dict_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(100) NOT NULL COMMENT '字典编码',
  `code` varchar(100) NOT NULL COMMENT '字典数据编码',
   `name` varchar(100) NOT NULL COMMENT '字典数据名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;








  