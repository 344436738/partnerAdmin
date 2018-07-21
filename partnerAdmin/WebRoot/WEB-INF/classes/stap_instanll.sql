/*
SQLyog Ultimate v8.32 
MySQL - 5.5.3-m3-log : Database - stap
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stap` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `stap`;

/*Table structure for table `site_main` */

DROP TABLE IF EXISTS `site_main`;

CREATE TABLE `site_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '网站简称',
  `domain` varchar(50) DEFAULT NULL COMMENT '域名 不带http://',
  `link` varchar(200) DEFAULT NULL COMMENT '网址连接 带http://',
  `state` int(1) DEFAULT NULL COMMENT '状态 0禁用 1可用 2审核中',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `pic` varchar(200) DEFAULT NULL COMMENT '图片URL',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除 1=删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `site_main` */

insert  into `site_main`(`id`,`name`,`domain`,`link`,`state`,`createTime`,`updateTime`,`rank`,`pic`,`deleted`) values (1,'华生论坛','www.mn606.com','http://www.mn606.com',0,'2012-12-23 20:13:24','2013-01-09 22:37:32',0,'',0),(2,'美女之家','www.mn606.com','http://www.mn606.com',0,'2012-12-23 20:16:53','2013-01-11 22:14:09',0,'',0),(3,'532525','532532','http://532532.com',0,'2012-12-23 20:32:15','2012-12-23 20:32:15',0,'',1),(4,'百度','www.mn606.com','http://www.mn606.com',0,'2013-01-08 16:29:14','2013-01-08 16:29:14',0,'',1),(5,'11111','1111','http://www.mn606.com',0,'2013-01-09 16:55:23','2013-01-09 16:55:23',0,'',1),(6,'1112','222','http://www.sina.com',0,'2013-01-09 16:55:47','2013-01-09 16:55:47',0,'',1);

/*Table structure for table `site_type` */

DROP TABLE IF EXISTS `site_type`;

CREATE TABLE `site_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `code` varchar(10) DEFAULT NULL COMMENT 'code 英文和数字',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `descr` varchar(250) DEFAULT NULL COMMENT '描述',
  `state` int(1) DEFAULT NULL COMMENT '状态 0=可用,1=禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `site_type` */

insert  into `site_type`(`id`,`name`,`code`,`rank`,`descr`,`state`,`createTime`,`updateTime`) values (1,'美女论坛','mnlt',0,'美女论坛',0,'2012-12-23 20:06:45','2013-01-15 17:45:55'),(2,'美女网站','mnwz',0,'11',0,'2012-12-23 20:30:39','2013-01-15 17:45:53');

/*Table structure for table `site_type_rel` */

DROP TABLE IF EXISTS `site_type_rel`;

CREATE TABLE `site_type_rel` (
  `siteId` int(11) NOT NULL COMMENT '站点id 关联：site_main.id',
  `siteTypeId` int(11) NOT NULL COMMENT '站点分类id 关联：site_type.id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `site_type_rel` */

insert  into `site_type_rel`(`siteId`,`siteTypeId`) values (1,2),(1,1),(2,1),(2,2);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '系统url',
  `parentId` int(10) DEFAULT NULL COMMENT ' 父id 关联sys_menu.id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0=未删除，1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `actions` varchar(500) DEFAULT '0' COMMENT '注册Action 按钮|分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parentId`,`deleted`,`createTime`,`updateTime`,`rank`,`actions`) values (1,'系统管理','',NULL,0,'2012-12-23 17:21:58','2013-01-10 22:30:50',1,''),(2,'菜单管理','/sysMenu/menu.shtml',1,0,'2012-12-23 18:18:32','2013-01-13 02:29:33',0,'dataList.do'),(3,'站点管理','',NULL,0,'2012-12-23 20:26:35','2012-12-23 21:16:51',1,''),(4,'站点信息管理','/siteMain/list.shtml',3,0,'2012-12-23 20:26:53','2013-01-13 01:24:47',0,'dataList.do|/siteType/typeListJson.do'),(5,'站点类型','/siteType/list.shtml',3,0,'2012-12-23 20:28:23','2013-01-13 01:20:46',0,'dataList.do'),(6,'操作员管理','/sysUser/list.shtml',1,0,'2012-12-23 22:15:33','2013-01-13 00:57:51',0,'dataList.do'),(7,'角色管理','/sysRole/role.shtml',1,0,'2012-12-24 22:17:51','2013-01-13 01:15:00',0,'dataList.do|/sysMenu/getMenuTree.do'),(8,'操作员授权','/sysUser/userRole.shtml',1,0,'2013-01-06 11:42:26','2013-01-14 11:35:04',0,'userList.do|/sysRole/loadRoleList.do'),(9,'测试11','111',NULL,1,'2013-01-15 16:40:48','2013-01-15 17:22:11',1,''),(10,'测试2','',NULL,1,'2013-01-15 16:42:59','2013-01-15 17:23:23',1,''),(11,'页面管理','/webPage/list.shtml',3,0,'2013-01-16 14:01:45','2013-01-16 15:21:15',0,'dataList.do'),(12,'111','1111',NULL,0,'2013-01-16 14:34:50',NULL,0,'111'),(13,'ttt','tttt',NULL,0,'2013-01-16 14:50:42',NULL,0,'tttt'),(14,'tt1','t1',13,0,'2013-01-16 14:50:54',NULL,0,''),(15,'11','11',NULL,0,'2013-01-16 14:52:46',NULL,0,''),(16,'aaa','',NULL,0,'2013-01-16 15:00:56',NULL,0,''),(17,'test','/test.do',NULL,0,'2013-01-16 15:25:35',NULL,0,'test.do'),(18,'test','ts',NULL,0,'2013-01-16 16:20:40',NULL,3,'');

/*Table structure for table `sys_menu_btn` */

DROP TABLE IF EXISTS `sys_menu_btn`;

CREATE TABLE `sys_menu_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` int(11) NOT NULL COMMENT ' 菜单id关联 sys_menu.id',
  `btnName` varchar(30) DEFAULT NULL COMMENT '按钮名称',
  `btnType` varchar(30) DEFAULT NULL COMMENT '按钮类型，用于列表页显示的按钮',
  `actionUrls` varchar(250) DEFAULT NULL COMMENT 'url注册，用"," 分隔 。用于权限控制UR',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu_btn` */

insert  into `sys_menu_btn`(`id`,`menuid`,`btnName`,`btnType`,`actionUrls`) values (5,2,'添加','add','save.do'),(6,2,'修改','edit','getId.do|save.do'),(7,2,'删除','remove','delete.do'),(8,6,'添加','add','save.do'),(9,6,'修改','edit','getId.do|save.do'),(10,6,'修改密码','editPwd','updatePwd.do'),(11,6,'删除','remove','delete.do'),(12,7,'添加','add','save.do'),(13,7,'修改','edit','getId.do|save.do'),(14,7,'删除','remove','delete.do'),(15,8,'授权','authRole','/sysUser/getUser.do|/sysUser/addUserRole.do'),(16,5,'添加','add','save.do'),(17,5,'修改','edit','getId.do|save.do'),(18,5,'删除','remove','delete.do'),(19,4,'添加','add','save.do'),(20,4,'修改','edit','getId.do|save.do'),(21,4,'删除','remove','delete.do'),(22,11,'添加','add','save.do'),(23,11,'修改','edit','getId.do|save.do'),(24,11,'删除','remove','delete.do'),(25,12,'11','111','111'),(26,15,'添加','add','save.do'),(27,15,'修改','edit','getId.do|save.do'),(28,15,'删除','remove','delete.do'),(29,17,'add','add',''),(30,18,'添加','add','save.do'),(31,18,'修改','edit','getId.do|save.do'),(32,18,'删除','remove','delete.do');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `roleName` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `state` int(1) DEFAULT NULL COMMENT '状态0=可用 1=禁用',
  `descr` varchar(200) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`roleName`,`createTime`,`createBy`,`updateTime`,`updateBy`,`state`,`descr`) values (1,'系统管理员','2013-01-05 16:07:00',NULL,'2013-01-14 11:28:29',NULL,0,NULL),(3,'管理员','2013-01-06 10:45:06',NULL,'2013-01-14 11:22:38',NULL,0,NULL),(18,'站点管理','2013-01-13 01:21:46',NULL,'2013-01-16 14:05:39',NULL,0,NULL),(19,'测试管理员','2013-01-13 17:10:21',NULL,'2013-01-16 14:06:08',NULL,1,NULL),(20,'23423','2013-01-16 15:56:47',NULL,'2013-01-16 15:56:47',NULL,0,NULL);

/*Table structure for table `sys_role_rel` */

DROP TABLE IF EXISTS `sys_role_rel`;

CREATE TABLE `sys_role_rel` (
  `roleId` int(11) NOT NULL COMMENT '角色主键 sys_role.id',
  `objId` int(11) NOT NULL COMMENT '关联主键 type=0管理sys_menu.id, type=1关联sys_user.id',
  `relType` int(1) DEFAULT NULL COMMENT '关联类型 0=菜单,1=用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_rel` */

insert  into `sys_role_rel`(`roleId`,`objId`,`relType`) values (3,8,0),(3,1,0),(3,2,0),(3,6,0),(3,7,0),(3,5,2),(3,8,2),(3,9,2),(3,12,2),(3,13,2),(3,15,2),(1,3,0),(1,4,0),(1,5,0),(1,19,2),(1,20,2),(1,21,2),(1,16,2),(1,17,2),(1,18,2),(3,6,1),(1,6,1),(3,5,1),(18,5,1),(18,11,0),(18,3,0),(18,4,0),(18,5,0),(18,19,2),(18,20,2),(18,16,2),(18,17,2),(18,22,2),(18,23,2),(18,24,2),(19,8,0),(19,1,0),(19,2,0),(19,6,0),(19,7,0),(19,3,0),(19,4,0),(19,5,0),(19,11,0),(19,7,2),(19,8,2),(19,10,2),(19,13,2),(19,15,2),(19,19,2),(19,20,2),(19,16,2),(19,17,2),(19,22,2),(19,23,2),(1,3,1),(3,3,1),(18,3,1),(1,1,1),(3,1,1),(18,1,1),(20,2,0),(20,1,0),(20,6,0),(20,5,2),(20,6,2),(20,7,2),(20,8,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `email` varchar(50) NOT NULL COMMENT '邮箱也是登录帐号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0=可用,1=禁用',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录总次数',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除,1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `superAdmin` int(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员 0= 不是，1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`email`,`pwd`,`nickName`,`state`,`loginCount`,`loginTime`,`deleted`,`createTime`,`updateTime`,`createBy`,`updateBy`,`superAdmin`) values (1,'admin@qq.com','C33367701511B4F6020EC61DED352059','超级大Boss',0,125,'2013-01-16 15:20:37',0,'2012-12-23 23:01:15','2013-01-16 15:20:37',NULL,NULL,1),(3,'362217990@qq.com','E10ADC3949BA59ABBE56E057F20F883E','vowo',0,1,'2013-01-07 12:53:29',0,'2012-12-23 23:17:39','2013-01-13 03:33:41',NULL,NULL,0),(5,'wolf@qq.com','E10ADC3949BA59ABBE56E057F20F883E','大灰狼',0,130,'2013-01-16 16:22:12',0,'2013-01-07 12:30:10','2013-01-16 16:22:12',NULL,NULL,0),(6,'youke@qq.com',NULL,' 游客',0,NULL,NULL,0,'2013-01-13 03:41:32','2013-01-13 03:41:32',NULL,NULL,0);

/*Table structure for table `web_page` */

DROP TABLE IF EXISTS `web_page`;

CREATE TABLE `web_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '页面名称',
  `typeid` varchar(11) DEFAULT NULL COMMENT '页面类型id web_type.id',
  `localPath` varchar(250) DEFAULT NULL COMMENT '页面路径',
  `title` varchar(250) DEFAULT NULL COMMENT '页面标题',
  `keyword` varchar(250) DEFAULT NULL COMMENT '关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '页面描述',
  `deleted` int(1) DEFAULT NULL COMMENT '删除状态 0=未删除，1=已删除',
  `templateContent` text COMMENT '页面模板内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间按',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `web_page` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
