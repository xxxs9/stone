/*
Navicat MySQL Data Transfer

Source Server         : mysql2333
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : stone

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-04 22:46:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_apply_order
-- ----------------------------
DROP TABLE IF EXISTS `a_apply_order`;
CREATE TABLE `a_apply_order` (
  `ID` varchar(32) NOT NULL,
  `APPLY_ID` varchar(32) NOT NULL,
  `APPLY_TYPE` int(2) NOT NULL,
  `APPLY_USER` varchar(10) NOT NULL,
  `APPLY_TIME` datetime NOT NULL,
  `APPLY_MONEY` varchar(10) NOT NULL,
  `APPLY_STATE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_apply_order
-- ----------------------------
INSERT INTO `a_apply_order` VALUES ('014cf08d753b49899ff26ac9ca644887', '518f568addca46a5b857ca1c8d8e4c0c', '1', 'gameloft9', '2019-04-04 12:31:21', '144.00', '1');
INSERT INTO `a_apply_order` VALUES ('2bcac859b6ef449f92738b2d1aff8d63', '8c38ce38e8514b8580e0ac3b489e95e9', '4', 'admin', '2019-04-04 13:07:46', '12', '1');
INSERT INTO `a_apply_order` VALUES ('937fb2412bf940d6a4953c1a026947ab', 'da4fcf023ec34bb29ce478e4a882d739', '2', 'admin', '2019-04-04 12:53:04', '15.00', '1');
INSERT INTO `a_apply_order` VALUES ('b49a565719654cca8176150f0bfc6c4c', 'dc6116bf5afa482c82ab3ec0c6fad4aa', '2', 'admin', '2019-04-04 13:40:01', '75.00', '1');
INSERT INTO `a_apply_order` VALUES ('b4baaff312714ad7b2187084b45f997c', '013867c4169b477996d9214841dc1108', '1', 'admin', '2019-04-04 13:11:37', '144.00', '3');
INSERT INTO `a_apply_order` VALUES ('e7b7b09df90f444b954c1db86393d5e7', '8556a0f670444e23850ce017ca80653a', '4', 'admin', '2019-04-04 13:01:10', '12', '1');

-- ----------------------------
-- Table structure for a_bill
-- ----------------------------
DROP TABLE IF EXISTS `a_bill`;
CREATE TABLE `a_bill` (
  `ID` varchar(32) NOT NULL,
  `DEPARTMENT` varchar(32) NOT NULL,
  `BALANCE` int(20) NOT NULL,
  `BILL_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_bill
-- ----------------------------
INSERT INTO `a_bill` VALUES ('17', '采购部', '1000', '2019-05-03 18:35:33');
INSERT INTO `a_bill` VALUES ('4469f0c7d4fd404fb1bea36928e2cccc', '采购部', '-4200', '2019-04-04 13:12:20');

-- ----------------------------
-- Table structure for a_charge_off
-- ----------------------------
DROP TABLE IF EXISTS `a_charge_off`;
CREATE TABLE `a_charge_off` (
  `ID` varchar(32) NOT NULL,
  `PAY_ID` varchar(32) NOT NULL,
  `ACCOUNT_PAYABLE` varchar(10) NOT NULL,
  `ACTUAL_PAYMENT` varchar(10) DEFAULT NULL,
  `ARREARS` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_charge_off
-- ----------------------------
INSERT INTO `a_charge_off` VALUES ('1', '111', '111', '1111', '111');
INSERT INTO `a_charge_off` VALUES ('2', '33', '333', '333', '333');

-- ----------------------------
-- Table structure for a_enter_an_item_in_an_account
-- ----------------------------
DROP TABLE IF EXISTS `a_enter_an_item_in_an_account`;
CREATE TABLE `a_enter_an_item_in_an_account` (
  `ID` varchar(32) NOT NULL,
  `RECEIVE_ID` varchar(32) NOT NULL,
  `PUBLIC_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_enter_an_item_in_an_account
-- ----------------------------

-- ----------------------------
-- Table structure for a_finance_public_purchase_sale
-- ----------------------------
DROP TABLE IF EXISTS `a_finance_public_purchase_sale`;
CREATE TABLE `a_finance_public_purchase_sale` (
  `PID` varchar(32) NOT NULL,
  `AUDIT_TYPE` int(1) NOT NULL,
  `UNIT_PRICE` varchar(20) NOT NULL,
  `TOTAL_PRICE` varchar(20) NOT NULL,
  `ACTUAL_BALANCE` varchar(20) DEFAULT NULL,
  `DOCUMENT_MAKER` varchar(10) DEFAULT NULL,
  `DOCUMENT_MAKE_TIME` date DEFAULT NULL,
  `AUDIT_USER` varchar(10) DEFAULT NULL,
  `AUDIT_TIME` date DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `OBLIGATE1` varchar(255) DEFAULT NULL,
  `OBLIGATE2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_finance_public_purchase_sale
-- ----------------------------
INSERT INTO `a_finance_public_purchase_sale` VALUES ('1', '1', '3', '4', '4', '5', '2019-03-20', '1', '2019-03-20', null, null, null);
INSERT INTO `a_finance_public_purchase_sale` VALUES ('2', '2', '22', '22', '22', '22', '2019-03-02', '22', '2019-03-07', null, null, null);
INSERT INTO `a_finance_public_purchase_sale` VALUES ('33', '1', '33', '33', '33', '33', '2019-03-03', '33', '2019-03-03', null, null, null);
INSERT INTO `a_finance_public_purchase_sale` VALUES ('44', '2', '44', '44', '44', '44', '2019-03-04', '44', '2019-03-04', null, null, null);

-- ----------------------------
-- Table structure for a_gathering
-- ----------------------------
DROP TABLE IF EXISTS `a_gathering`;
CREATE TABLE `a_gathering` (
  `ID` varchar(32) NOT NULL,
  `RECEIVE_ID` varchar(32) NOT NULL,
  `RECEIVE_TYPE` int(2) NOT NULL,
  `BALANCE` varchar(10) NOT NULL,
  `DOCUMENT_MAKER` varchar(10) NOT NULL,
  `DOCUMENT_MAKE_TIME` date NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_gathering
-- ----------------------------

-- ----------------------------
-- Table structure for a_payment
-- ----------------------------
DROP TABLE IF EXISTS `a_payment`;
CREATE TABLE `a_payment` (
  `ID` varchar(32) NOT NULL,
  `PAY_ID` varchar(32) NOT NULL,
  `PAY_TYPE` int(2) NOT NULL,
  `BALANCE` varchar(10) NOT NULL,
  `DOCUMENT_MAKER` varchar(10) NOT NULL,
  `DOCUMENT_MAKE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_payment
-- ----------------------------
INSERT INTO `a_payment` VALUES ('c1e25bd28cd64822992159db2e1364e2', '2c85baa93b8c4ab7888c852dae58ecf0', '1', '4200', 'admin', '2019-04-04 13:12:20');

-- ----------------------------
-- Table structure for a_purchase_bills_payable
-- ----------------------------
DROP TABLE IF EXISTS `a_purchase_bills_payable`;
CREATE TABLE `a_purchase_bills_payable` (
  `ID` varchar(32) NOT NULL,
  `AUDIT_TYPE` int(1) NOT NULL,
  `UNIT_PRICE` varchar(20) NOT NULL,
  `GOODS_NUMBER` varchar(10) NOT NULL,
  `TOTAL_PRICE` varchar(20) NOT NULL,
  `ACTUAL_BALANCE` varchar(20) DEFAULT NULL,
  `DOCUMENT_MAKER` varchar(10) DEFAULT NULL,
  `DOCUMENT_MAKE_TIME` datetime DEFAULT NULL,
  `AUDIT_USER` varchar(10) DEFAULT NULL,
  `AUDIT_TIME` datetime DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `PURCHASE_ORDER_ID` varchar(32) NOT NULL,
  `AUDIT_STATE` int(2) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_purchase_bills_payable
-- ----------------------------
INSERT INTO `a_purchase_bills_payable` VALUES ('2c85baa93b8c4ab7888c852dae58ecf0', '1', '350', '12', '4200', '4200', 'admin', '2019-04-04 13:12:07', 'admin', '2019-04-04 13:12:20', '12', '013867c4169b477996d9214841dc1108', '3');

-- ----------------------------
-- Table structure for a_purchase_receivable
-- ----------------------------
DROP TABLE IF EXISTS `a_purchase_receivable`;
CREATE TABLE `a_purchase_receivable` (
  `ID` varchar(32) NOT NULL,
  `AUDIT_TYPE` int(1) NOT NULL,
  `UNIT_PRICE` varchar(20) NOT NULL,
  `TOTAL_PRICE` varchar(20) NOT NULL,
  `ACTUAL_BALANCE` varchar(20) DEFAULT NULL,
  `DOCUMENT_MAKER` varchar(10) DEFAULT NULL,
  `DOCUMENT_MAKE_TIME` datetime DEFAULT NULL,
  `AUDIT_USER` varchar(10) DEFAULT NULL,
  `AUDIT_TIME` datetime DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `REJECTED_NUMBER` varchar(10) NOT NULL,
  `PURCHASE_ORDER_REJECTED_ID` varchar(32) NOT NULL,
  `AUDIT_STATE` int(2) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_purchase_receivable
-- ----------------------------

-- ----------------------------
-- Table structure for a_recepit
-- ----------------------------
DROP TABLE IF EXISTS `a_recepit`;
CREATE TABLE `a_recepit` (
  `ID` varchar(32) NOT NULL,
  `RECEIVE_ID` varchar(32) NOT NULL,
  `RECEIVE_TYPE` int(2) NOT NULL,
  `BALANCE` varchar(10) NOT NULL,
  `DOCUMENT_MAKER` varchar(10) NOT NULL,
  `DOCUMENT_MAKE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_recepit
-- ----------------------------

-- ----------------------------
-- Table structure for a_sale_bills_payable
-- ----------------------------
DROP TABLE IF EXISTS `a_sale_bills_payable`;
CREATE TABLE `a_sale_bills_payable` (
  `ID` varchar(32) NOT NULL,
  `AUDIT_TYPE` int(1) NOT NULL,
  `UNIT_PRICE` varchar(20) NOT NULL,
  `TOTAL_PRICE` varchar(20) NOT NULL,
  `ACTUAL_BALANCE` varchar(20) DEFAULT NULL,
  `DOCUMENT_MAKER` varchar(10) DEFAULT NULL,
  `DOCUMENT_MAKE_TIME` datetime DEFAULT NULL,
  `AUDIT_USER` varchar(10) DEFAULT NULL,
  `AUDIT_TIME` datetime DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `REJECTED_NUMBER` varchar(10) NOT NULL,
  `SALE_REJECTED_ID` varchar(32) NOT NULL,
  `AUDIT_STATE` int(2) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_sale_bills_payable
-- ----------------------------

-- ----------------------------
-- Table structure for a_sale_receivable
-- ----------------------------
DROP TABLE IF EXISTS `a_sale_receivable`;
CREATE TABLE `a_sale_receivable` (
  `ID` varchar(32) NOT NULL,
  `AUDIT_TYPE` int(1) NOT NULL,
  `UNIT_PRICE` varchar(20) NOT NULL,
  `TOTAL_PRICE` varchar(20) NOT NULL,
  `ACTUAL_BALANCE` varchar(20) DEFAULT NULL,
  `DOCUMENT_MAKER` varchar(10) DEFAULT NULL,
  `DOCUMENT_MAKE_TIME` datetime DEFAULT NULL,
  `AUDIT_USER` varchar(10) DEFAULT NULL,
  `AUDIT_TIME` datetime DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `PRODUCT_NUMBER` varchar(10) NOT NULL,
  `SALE_ID` varchar(32) NOT NULL,
  `AUDIT_STATE` int(2) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of a_sale_receivable
-- ----------------------------

-- ----------------------------
-- Table structure for d_depot_inventory
-- ----------------------------
DROP TABLE IF EXISTS `d_depot_inventory`;
CREATE TABLE `d_depot_inventory` (
  `ID` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `GOODS_NAME` varchar(255) NOT NULL,
  `GOODS_ID` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL DEFAULT '',
  `SHIPMENTS_NUMBER` varchar(255) NOT NULL DEFAULT '0',
  `SALEABLE_NUMBER` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_depot_inventory
-- ----------------------------
INSERT INTO `d_depot_inventory` VALUES ('21cd14af2c754f85a190c52465631794', '原料', '汉白玉', '4498e94ab4744a798962a75000920641', '13000', '0', '14000');
INSERT INTO `d_depot_inventory` VALUES ('0e15d8b5b29f4ea58f378c0820245e27', '原料', '黑色板岩', '6413bc2019e940fca2a8d6f37d4d84c2', '120000', '0', '130000');
INSERT INTO `d_depot_inventory` VALUES ('6818023026374c99a506da22fcf1b987', '产品', '七彩石', '323a258d934144af9379686f3674e417', '14000', '0', '14260');
INSERT INTO `d_depot_inventory` VALUES ('4792723b6546414382af924193fcaf7d', '产品', '大理石', '6fc17521bd404f5ea24685a44b7c5a64', '800', '294', '506');
INSERT INTO `d_depot_inventory` VALUES ('a67d0e44de2d4bcc873ab9564eeeb34c', '原料', '樱桃红花岗石', '595ff4a952e8455382194af2576d9225', '1000', '0', '1000');
INSERT INTO `d_depot_inventory` VALUES ('f95adc02cd0d4ad2bede029c5b223690', '原料', '芝麻白花岗石', '1faa767ff0e84434b5f3ab6adb6c4f75', '470', '0', '478');
INSERT INTO `d_depot_inventory` VALUES ('151de7f0bc9240cf8417372e02c8c90a', '原料', '意大利木纹大理石', '4ffa35972038419b8b9599be77b03fa7', '1000', '15', '985');
INSERT INTO `d_depot_inventory` VALUES ('64d7d703c3aa42f683cc7e26d9bfb0c4', '原料', '雪花白大理石', 'edc4cc3f7da4486c8a0bb3cafe4ec772', '50', '0', '50');
INSERT INTO `d_depot_inventory` VALUES ('11d8fae6d87f4a20a84706d673703cca', '原料', '白色细砂石', 'd3a0520e6ca649e08255f4cce6388c25', '100', '0', '100');
INSERT INTO `d_depot_inventory` VALUES ('c33f18d22ee34b0eaa231d84f92827f1', '产品', '现实宝石', '01612d22019f4f88af8686ceb7383938', '64', '5', '59');
INSERT INTO `d_depot_inventory` VALUES ('824a77d7c71b4d178b1fcb7627c3c029', '产品', '寿山石', 'fae7282d040947aeb2c23140391d3a9b', '1', '0', '1');
INSERT INTO `d_depot_inventory` VALUES ('3227b280ec7e44f49cbeb29b55a79f89', '产品', 'qqq', 'f8fed885691c4a228b85fa0dfe14c464', '1', '0', '1');
INSERT INTO `d_depot_inventory` VALUES ('d70a3174c8074c23af1670199fb52003', '产品', 'huafeng', '3fae63afb73a42f19b03f5d279f10bd1', '1009', '0', '1009');
INSERT INTO `d_depot_inventory` VALUES ('419638b244d84d82aef43c5862696f8e', '产品', '洗衣池', '9a28951713b04ee98219b6f892cfe2e0', '7', '0', '7');

-- ----------------------------
-- Table structure for d_depot_inventory_check
-- ----------------------------
DROP TABLE IF EXISTS `d_depot_inventory_check`;
CREATE TABLE `d_depot_inventory_check` (
  `ID` varchar(255) NOT NULL,
  `SOURCE_USER` varchar(255) NOT NULL,
  `CHECK_TYPE` varchar(255) NOT NULL,
  `SOURCE_TIME` varchar(255) NOT NULL,
  `RECORD_NUMBER` varchar(255) NOT NULL,
  `CHECK_STATE` varchar(255) NOT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_depot_inventory_check
-- ----------------------------
INSERT INTO `d_depot_inventory_check` VALUES ('5d38f76ef3384c198bcb2e8d8f495d35', 'admin', '全部盘点', '2019-04-04 13:32:39', '6', '盘点结束', '审核通过');
INSERT INTO `d_depot_inventory_check` VALUES ('7b76704367a4414a9b8be8a26c59ee2e', 'admin', '部分盘点', '2019-04-04 13:34:16', '3', '盘点结束', '审核通过');

-- ----------------------------
-- Table structure for d_depot_inventory_check_detail
-- ----------------------------
DROP TABLE IF EXISTS `d_depot_inventory_check_detail`;
CREATE TABLE `d_depot_inventory_check_detail` (
  `ID` varchar(255) NOT NULL,
  `CHECK_ID` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `GOODS_ID` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL,
  `CHECK_USER` varchar(255) DEFAULT NULL,
  `CHECK_TIME` datetime DEFAULT NULL,
  `CHECK_NUMBER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_depot_inventory_check_detail
-- ----------------------------
INSERT INTO `d_depot_inventory_check_detail` VALUES ('1a8ae7e2ece54807a04b4c5b1a1b646b', '7b76704367a4414a9b8be8a26c59ee2e', '产品', '323a258d934144af9379686f3674e417', '14000', 'admin', '2019-04-04 13:34:41', '14000');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('2cadba24509b434b88b68d393a8d6b6a', '5d38f76ef3384c198bcb2e8d8f495d35', '产品', '6fc17521bd404f5ea24685a44b7c5a64', '800', 'admin', '2019-04-04 13:32:51', '800');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('630c1a4cbbbc466bbfdb41d2804dac61', '5d38f76ef3384c198bcb2e8d8f495d35', '原料', '1faa767ff0e84434b5f3ab6adb6c4f75', '470', 'admin', '2019-04-04 13:32:56', '470');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('6e0a832f83f0496d8bb894b6e8a1850b', '7b76704367a4414a9b8be8a26c59ee2e', '原料', '4498e94ab4744a798962a75000920641', '13000', 'admin', '2019-04-04 13:34:44', '13000');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('6fb57646e1234c2a81346e43f181e72e', '5d38f76ef3384c198bcb2e8d8f495d35', '原料', '595ff4a952e8455382194af2576d9225', '1000', 'admin', '2019-04-04 13:33:04', '1000');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('813fd656ed7f4daf91180a6e4c69c722', '5d38f76ef3384c198bcb2e8d8f495d35', '产品', '323a258d934144af9379686f3674e417', '14200', 'admin', '2019-04-04 13:33:13', '14200');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('953f91ac67b54c6db04d60447f6db37d', '7b76704367a4414a9b8be8a26c59ee2e', '原料', '6413bc2019e940fca2a8d6f37d4d84c2', '120000', 'admin', '2019-04-04 13:34:48', '120000');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('b380df7e598843039cb8a3a77296e934', '5d38f76ef3384c198bcb2e8d8f495d35', '原料', '4498e94ab4744a798962a75000920641', '14300', 'admin', '2019-04-04 13:33:20', '14300');
INSERT INTO `d_depot_inventory_check_detail` VALUES ('b6655becda5b4337b51fa2802cf02e47', '5d38f76ef3384c198bcb2e8d8f495d35', '原料', '6413bc2019e940fca2a8d6f37d4d84c2', '130000', 'admin', '2019-04-04 13:33:28', '130000');

-- ----------------------------
-- Table structure for d_depot_order
-- ----------------------------
DROP TABLE IF EXISTS `d_depot_order`;
CREATE TABLE `d_depot_order` (
  `ID` varchar(255) NOT NULL,
  `ORDER_TYPE` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `GOODS_ID` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL,
  `APPLY_USER` varchar(255) NOT NULL,
  `APPLY_TIME` datetime NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `ORDER_AUDIT_USER` varchar(255) DEFAULT NULL,
  `ORDER_AUDIT_TIME` datetime DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  `OUT_IN_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_depot_order
-- ----------------------------
INSERT INTO `d_depot_order` VALUES ('6a4891ff465a441fabcd190c93e80002', '入库单', '产品入库', '3fae63afb73a42f19b03f5d279f10bd1', '1000', 'admin', '2019-04-04 12:52:03', '已入库', 'admin', '2019-04-04 12:52:08', '111', null);
INSERT INTO `d_depot_order` VALUES ('c188cb78054f4d5a92474a19fba3e00d', '入库单', '报废出库', '3fae63afb73a42f19b03f5d279f10bd1', '10', 'admin', '2019-04-04 13:30:25', '已入库', 'admin', '2019-04-04 13:30:31', '111', null);
INSERT INTO `d_depot_order` VALUES ('CG040468982193', '入库单', '采购入库', '4ffa35972038419b8b9599be77b03fa7', '12', 'admin', '2019-04-04 13:13:15', '已入库', 'admin', '2019-04-04 13:14:10', '123', null);
INSERT INTO `d_depot_order` VALUES ('eaa3e365c21945729cd9bec7714009fb', '出库单', '生产领料', '1faa767ff0e84434b5f3ab6adb6c4f75', '10', '111', '2019-04-04 13:24:04', '已出库', 'admin', '2019-04-04 13:25:37', '10', null);
INSERT INTO `d_depot_order` VALUES ('FH040417334723', '入库单', '销售退货', '01612d22019f4f88af8686ceb7383938', '12', 'admin', '2019-04-04 13:06:36', '已入库', 'admin', '2019-04-04 13:06:58', '12', null);
INSERT INTO `d_depot_order` VALUES ('FH040429781385', '入库单', '销售退货', '01612d22019f4f88af8686ceb7383938', '1', 'admin', '2019-04-04 13:03:45', '入库单审核中', null, null, null, null);
INSERT INTO `d_depot_order` VALUES ('P631554384047874', '入库单', '生产入库', '9a28951713b04ee98219b6f892cfe2e0', '7', '林公子', '2019-04-04 13:26:19', '已入库', 'admin', '2019-04-04 13:26:40', '1·', null);
INSERT INTO `d_depot_order` VALUES ('xs040402092243', '出库单', '销售出库', '3fae63afb73a42f19b03f5d279f10bd1', '1', 'admin', '2019-04-04 12:48:44', '已出库', 'admin', '2019-04-04 12:52:46', '123', null);
INSERT INTO `d_depot_order` VALUES ('xs040457326977', '出库单', '销售出库', '01612d22019f4f88af8686ceb7383938', '5', 'admin', '2019-04-04 13:39:16', '审核通过', 'admin', '2019-04-04 13:39:45', 'aoe', null);

-- ----------------------------
-- Table structure for l_bill_check
-- ----------------------------
DROP TABLE IF EXISTS `l_bill_check`;
CREATE TABLE `l_bill_check` (
  `ID` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `CHECK_NUMBER` varchar(255) NOT NULL,
  `CHECK_DATE` date DEFAULT NULL,
  `CHECK_USER` varchar(255) NOT NULL,
  `CHECK_REMARK` varchar(255) DEFAULT NULL,
  `REACH_ID` varchar(255) NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `PLAN_ID` varchar(255) NOT NULL,
  `PRODUCT_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_bill_check
-- ----------------------------
INSERT INTO `l_bill_check` VALUES ('2b5994d72b63464fa5c9ac23c881836d', '合格', '1', '2019-04-04', 'xlxlxlxlx', null, '2a0debade85a482fbf5c6d01b9af94da', 'fae7282d040947aeb2c23140391d3a9b', 'daa8abf5ac2843a996880b8df1699f92', '寿山石');
INSERT INTO `l_bill_check` VALUES ('b3db51deb05749ddb4f5e9e6602020be', '合格', '7', '2019-04-04', '林公子', null, 'eaa3e365c21945729cd9bec7714009fb', '9a28951713b04ee98219b6f892cfe2e0', '7eb69bb000344e2b95031f57b35048e6', '洗衣池');
INSERT INTO `l_bill_check` VALUES ('d87f6bb1dbe243468a71155117c80852', '合格', '1', '2019-04-04', 'huaf', null, 'd1605113e8e549c7affeb7665212f2a5', 'f8fed885691c4a228b85fa0dfe14c464', '87574834c7474c9596db3da5ac65bdb5', 'qqq');
INSERT INTO `l_bill_check` VALUES ('e1ec3a23e1904867a604be6ba2f37bef', '合格', '1', '2019-04-04', '1111', null, 'c6fdd20afc934b868f999f2b77069acd', '3fae63afb73a42f19b03f5d279f10bd1', 'bfb43dcc285c4c15a39c35ce9052d7b8', 'huafeng');

-- ----------------------------
-- Table structure for l_bill_check_final
-- ----------------------------
DROP TABLE IF EXISTS `l_bill_check_final`;
CREATE TABLE `l_bill_check_final` (
  `ID` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `CHECK_NUMBER` varchar(255) NOT NULL,
  `CHECK_DATE` date DEFAULT NULL,
  `CHECK_USER` varchar(255) NOT NULL,
  `CHECK_REMARK` varchar(255) DEFAULT NULL,
  `REACH_ID` varchar(255) NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `PLAN_ID` varchar(255) NOT NULL,
  `PRODUCT_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_bill_check_final
-- ----------------------------
INSERT INTO `l_bill_check_final` VALUES ('042fbebaff30418288154fdfc6df51da', '6', '111', '2019-04-01', 'gameloft9', '11', 'a45321b01b434c249883e3ba916354f4', '3332554e8cd34c15a3dedfe3524180da', '182659105a2941c48f0900e330e7fb28', 'hua');
INSERT INTO `l_bill_check_final` VALUES ('0e16bb907c0146f8af29b9b0425abcde', '6', '2', '2019-04-02', 'lennon1', '1', '2d927a1e65e444889870a88e55eb6d63', '0ae550b55a9a496a8a6118b05cd6e1e4', '475be2061a1949dcb15b73d3a1f9cfb6', '七彩石');
INSERT INTO `l_bill_check_final` VALUES ('2abbe1c71874415db0b85e06ab0117a3', '6', '2', '2019-04-02', 'lennon1', '1111', '2d927a1e65e444889870a88e55eb6d63', '0ae550b55a9a496a8a6118b05cd6e1e4', '475be2061a1949dcb15b73d3a1f9cfb6', '七彩石');
INSERT INTO `l_bill_check_final` VALUES ('3a57d1f877d74326ab354ad987eef218', '6', '12', '2019-04-01', 'gameloft9', '2222', 'a45321b01b434c249883e3ba916354f4', '3332554e8cd34c15a3dedfe3524180da', '182659105a2941c48f0900e330e7fb28', 'hua');
INSERT INTO `l_bill_check_final` VALUES ('7c18d0f7fbea4a6e86f65be554b290f4', '6', '12', '2019-04-01', 'gameloft9', '11111', 'a45321b01b434c249883e3ba916354f4', '3332554e8cd34c15a3dedfe3524180da', '182659105a2941c48f0900e330e7fb28', 'hua');
INSERT INTO `l_bill_check_final` VALUES ('889b7c43e6f84bad8caec2c754f06568', '6', '1', '2019-04-01', 'lennon1', '111', 'a45321b01b434c249883e3ba916354f4', '3332554e8cd34c15a3dedfe3524180da', '182659105a2941c48f0900e330e7fb28', 'hua');

-- ----------------------------
-- Table structure for l_create_user_info
-- ----------------------------
DROP TABLE IF EXISTS `l_create_user_info`;
CREATE TABLE `l_create_user_info` (
  `ID` varchar(255) NOT NULL,
  `CREATE_USER` varchar(255) NOT NULL,
  `CREATE_TIME` date NOT NULL,
  `EMPLOYEE_ID` varchar(255) NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_create_user_info
-- ----------------------------
INSERT INTO `l_create_user_info` VALUES ('1', 'lennon', '2019-03-21', '111', null, null, null);
INSERT INTO `l_create_user_info` VALUES ('34b72fb0cf8e43c4ae13f793df224f7c', 'Mick', '2019-03-20', '0', null, null, null);

-- ----------------------------
-- Table structure for l_formula_reach
-- ----------------------------
DROP TABLE IF EXISTS `l_formula_reach`;
CREATE TABLE `l_formula_reach` (
  `ID` varchar(255) NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `PRODUCE_FORMULA_ID` varchar(255) NOT NULL,
  `PRODUCE_FORMULA_DETAIL_ID` varchar(255) NOT NULL,
  `DEPOT_AUDI` varchar(255) NOT NULL,
  `FORMULA_BACK` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `REACH_USER` varchar(255) NOT NULL,
  `REACH_TIME` date NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_formula_reach
-- ----------------------------
INSERT INTO `l_formula_reach` VALUES ('2a0debade85a482fbf5c6d01b9af94da', 'fae7282d040947aeb2c23140391d3a9b', '1', '111121212', '', '0', '', 'llll', '2019-04-03', null, null, null);
INSERT INTO `l_formula_reach` VALUES ('519799cea1254f86a39aceb7bf73d998', '6fc17521bd404f5ea24685a44b7c5a64', '1', '111121212', '', '0', '', '1', '2019-04-03', null, null, null);
INSERT INTO `l_formula_reach` VALUES ('c6fdd20afc934b868f999f2b77069acd', '3fae63afb73a42f19b03f5d279f10bd1', '1', '111121212', '', '0', '', 'len', '2019-04-03', null, null, null);
INSERT INTO `l_formula_reach` VALUES ('d1605113e8e549c7affeb7665212f2a5', 'f8fed885691c4a228b85fa0dfe14c464', '1', '111121212', '', '0', '', 'huaf', '2019-04-03', null, null, null);
INSERT INTO `l_formula_reach` VALUES ('eaa3e365c21945729cd9bec7714009fb', '9a28951713b04ee98219b6f892cfe2e0', '1', '111121212', '', '0', '', '111', '2019-04-04', null, null, null);

-- ----------------------------
-- Table structure for l_formula_reach_new
-- ----------------------------
DROP TABLE IF EXISTS `l_formula_reach_new`;
CREATE TABLE `l_formula_reach_new` (
  `ID` varchar(255) NOT NULL,
  `MATERIAL_ID` varchar(255) DEFAULT NULL,
  `MATERIAL_NUMBER` varchar(255) DEFAULT NULL,
  `DEPOT_AUDI` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `REACH_USER` varchar(255) DEFAULT NULL,
  `REACH_TIME` date DEFAULT NULL,
  `REACH_REMARK` varchar(255) DEFAULT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) DEFAULT NULL,
  `PRODUCT_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_formula_reach_new
-- ----------------------------

-- ----------------------------
-- Table structure for l_produce_formula
-- ----------------------------
DROP TABLE IF EXISTS `l_produce_formula`;
CREATE TABLE `l_produce_formula` (
  `ID` varchar(255) NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `FORMULA_TYPE` varchar(255) NOT NULL,
  `FORMULA_NUMBER` varchar(255) NOT NULL,
  `CREATE_USER` varchar(255) NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CREATE_TIME` date NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_produce_formula
-- ----------------------------
INSERT INTO `l_produce_formula` VALUES ('1', '133131312', '1', '11', 'lennon', null, null, null, '2019-03-22');
INSERT INTO `l_produce_formula` VALUES ('111111111', '9cd09b2c3b8f433e9f83642df0750e19', '1', '12', '11111', null, null, null, '2019-03-25');
INSERT INTO `l_produce_formula` VALUES ('aea434wefwwerfeweg3453453423fefw', '2c84332d3fcd4141a227eaaf94cf80da', '1', '11', 'Mike', null, null, null, '2019-03-25');
INSERT INTO `l_produce_formula` VALUES ('b344bd299bb6434c94672ba05c101cf3', '7545312412134124', '0', '121', '1111', null, null, null, '2019-03-22');

-- ----------------------------
-- Table structure for l_produce_formula_detail
-- ----------------------------
DROP TABLE IF EXISTS `l_produce_formula_detail`;
CREATE TABLE `l_produce_formula_detail` (
  `ID` varchar(255) NOT NULL,
  `PRODUCE_FORMULA_ID` varchar(255) NOT NULL,
  `MATERIAL_ID` varchar(255) NOT NULL,
  `MATERIAL_NUMBER` varchar(255) NOT NULL,
  `DEPOT_ID` varchar(255) NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_produce_formula_detail
-- ----------------------------
INSERT INTO `l_produce_formula_detail` VALUES ('111121212', '111111111', '1faa767ff0e84434b5f3ab6adb6c4f75', '1', '111', null, null, null);
INSERT INTO `l_produce_formula_detail` VALUES ('1412433141', 'b344bd299bb6434c94672ba05c101cf3', '1111', '111', '121', null, null, null);
INSERT INTO `l_produce_formula_detail` VALUES ('324dsaw', 'aea434wefwwerfeweg3453453423fefw', '2222', '22', '120', null, null, null);

-- ----------------------------
-- Table structure for l_produce_plan
-- ----------------------------
DROP TABLE IF EXISTS `l_produce_plan`;
CREATE TABLE `l_produce_plan` (
  `ID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `PRODUCT_ID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `PLAN_NUMBER` varchar(255) CHARACTER SET utf8 NOT NULL,
  `REAL_NUMBER` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `GOODS_NUMBER` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `BILL_CYCLE` varchar(255) CHARACTER SET utf8 NOT NULL,
  `STATE` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `PLAN_REMARK` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `PRODUCE_DATE` date NOT NULL,
  `FINISH_DATE` date NOT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_produce_plan
-- ----------------------------
INSERT INTO `l_produce_plan` VALUES ('182659105a2941c48f0900e330e7fb28', '3332554e8cd34c15a3dedfe3524180da', '1', null, '0', '1', '1', '', '2019-03-31', '2019-04-29', '2019-03-31', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('475be2061a1949dcb15b73d3a1f9cfb6', '0ae550b55a9a496a8a6118b05cd6e1e4', '13', null, '0', '1', '1', '111', '2019-04-01', '2019-05-01', '2019-04-01', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('48f186644a7e46d7b9f662012c8d79fd', '323a258d934144af9379686f3674e417', '22', null, '0', '1', '1', '自己生产补天七彩石', '2019-04-01', '2019-05-01', '2019-04-01', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('7a99e775ed9349e8afc36035625a822c', '6c9c6563436f43e89b4539795b39975f', '14', null, '0', '1', '1', 'qqqq', '2019-04-01', '2019-05-01', '2019-04-01', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('7eb69bb000344e2b95031f57b35048e6', '9a28951713b04ee98219b6f892cfe2e0', '6', null, '0', '0', '1', '111', '2019-04-04', '2019-04-04', '2019-04-04', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('87574834c7474c9596db3da5ac65bdb5', 'f8fed885691c4a228b85fa0dfe14c464', '1', null, '0', '0', '1', '', '2019-04-03', '2019-04-03', '2019-04-03', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('b7afd79ee7fa491f96bc294ef0c04c4c', '8bc80c84a1494b67840a221562e36e34', '13', null, '0', '1', '1', 'zzz', '2019-04-01', '2019-05-01', '2019-04-01', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('bfb43dcc285c4c15a39c35ce9052d7b8', '3fae63afb73a42f19b03f5d279f10bd1', '12', null, '0', '0', '1', '111', '2019-04-03', '2019-04-03', '2019-04-03', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('cc6427249b074118b1bae3338bd1d550', '6fc17521bd404f5ea24685a44b7c5a64', '1', null, '0', '0', '1', '', '2019-04-03', '2019-04-03', '2019-04-03', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('daa8abf5ac2843a996880b8df1699f92', 'fae7282d040947aeb2c23140391d3a9b', '1', null, '0', '0', '1', '', '2019-04-03', '2019-04-03', '2019-04-03', null, null, null);
INSERT INTO `l_produce_plan` VALUES ('db9c75734d2149b889e72b71a97347a8', '08b79f303f9449f4a360b603254aafce', '14', null, '0', '1', '1', 'qqqq', '2019-04-01', '2019-05-01', '2019-04-01', null, null, null);

-- ----------------------------
-- Table structure for l_product
-- ----------------------------
DROP TABLE IF EXISTS `l_product`;
CREATE TABLE `l_product` (
  `ID` varchar(255) NOT NULL,
  `PRODUCT_NAME` varchar(255) NOT NULL,
  `PRODUCT_TYPE` varchar(255) NOT NULL,
  `PRODUCT_STATE` varchar(255) DEFAULT NULL,
  `PRODUCT_DESCRIBE` varchar(255) DEFAULT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `PRODUCT_NUMBER` varchar(255) NOT NULL,
  `CAN_SOLD` varchar(255) NOT NULL,
  `SUPPORT_PRICE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_product
-- ----------------------------
INSERT INTO `l_product` VALUES ('01612d22019f4f88af8686ceb7383938', '现实宝石', '3', '1', '化想象为现实', 'P661554298076298', null, null, '1', 'gameloft9', null);
INSERT INTO `l_product` VALUES ('3fae63afb73a42f19b03f5d279f10bd1', 'huafeng', '2', '11', '111', 'P241554343538237', null, null, '12', 'lennon1', '12');
INSERT INTO `l_product` VALUES ('6fc17521bd404f5ea24685a44b7c5a64', '大理石', '1', '3', 'test', 'P251554023817382', null, null, '12', 'lennon1', null);
INSERT INTO `l_product` VALUES ('9a28951713b04ee98219b6f892cfe2e0', '洗衣池', '4', '12', '111', 'P631554384047874', null, null, '1', 'admin', '15');
INSERT INTO `l_product` VALUES ('c813b15060ba493b9476b99835b63694', '花岗岩', '4', '1', '', 'P791554291702321', null, null, '80', 'admin', null);
INSERT INTO `l_product` VALUES ('dc12af20108b459b8a2168f74b8cdfe0', '力量宝石', '3', '1', '获得无限力量', 'P291554297440989', null, null, '1', 'gameloft9', null);
INSERT INTO `l_product` VALUES ('f4eb153bc4a84b23ad268ab4266d41ac', 'aaaaaa', '4', '1', '111', 'P631554345611271', null, null, '12', 'gameloft9', null);
INSERT INTO `l_product` VALUES ('f8fed885691c4a228b85fa0dfe14c464', 'qqq', '4', '12', '', 'P151554380579657', null, null, '11', 'lennon1', '');
INSERT INTO `l_product` VALUES ('fae7282d040947aeb2c23140391d3a9b', '寿山石', '4', '11', '111', 'P641554377021989', null, null, '21', 'lennon1', '1233');

-- ----------------------------
-- Table structure for l_product_check
-- ----------------------------
DROP TABLE IF EXISTS `l_product_check`;
CREATE TABLE `l_product_check` (
  `ID` varchar(255) NOT NULL,
  `PRODUCE_PLAN_ID` varchar(255) DEFAULT NULL,
  `FORMULA_REACH_ID` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `CHECK_USER` varchar(255) NOT NULL,
  `CHECK_TIME` date NOT NULL,
  `WASTE_ID` varchar(255) DEFAULT NULL,
  `CHECK_REMARK` varchar(255) NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_product_check
-- ----------------------------
INSERT INTO `l_product_check` VALUES ('37f6cb82ad1242a3b2cafaf658e07838', null, '61c0ed5df2ed4f9c9ea8d4cdae33574a', '0', 'lennon', '2019-03-24', '1', '11111', null, null, null);
INSERT INTO `l_product_check` VALUES ('d033fc1518e24a449938249446e1111a', null, '46a96034df02440fbf4e6c9578a5dc33', '0', '111', '2019-03-27', '1', '1', null, null, null);
INSERT INTO `l_product_check` VALUES ('e7d4bb54489343ddac2854dade86c5f1', null, '61c0ed5df2ed4f9c9ea8d4cdae33574a', '1', 'lenn', '2019-03-25', '1', '326', null, null, null);

-- ----------------------------
-- Table structure for l_product_waste
-- ----------------------------
DROP TABLE IF EXISTS `l_product_waste`;
CREATE TABLE `l_product_waste` (
  `ID` varchar(255) NOT NULL,
  `WASTE_NUMBER` varchar(255) NOT NULL,
  `PRODUCE_FORMULA_ID` varchar(255) CHARACTER SET utf8 NOT NULL,
  `WASTE_TIME` date NOT NULL,
  `WASTE_REMARK` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `OTHER1` varchar(255) DEFAULT NULL,
  `OTHER2` varchar(255) DEFAULT NULL,
  `OTHER3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_product_waste
-- ----------------------------
INSERT INTO `l_product_waste` VALUES ('1', '11', '111', '2019-03-21', 'none', '0', null, null, null);

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `ID` varchar(255) NOT NULL,
  `GOODS_NAME` varchar(255) DEFAULT NULL,
  `GOODS_TYPE` varchar(255) DEFAULT NULL,
  `GOODS_DESC` varchar(255) DEFAULT NULL,
  `GOODS_TYPE_ID` varchar(11) DEFAULT NULL,
  `CREAT_DATE` date DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for material_goods
-- ----------------------------
DROP TABLE IF EXISTS `material_goods`;
CREATE TABLE `material_goods` (
  `SUPPLIER_ID` varchar(255) DEFAULT NULL,
  `MATERIAL_ID` varchar(255) DEFAULT NULL,
  `GOODS_PRICE` varchar(255) DEFAULT NULL,
  KEY `key1` (`SUPPLIER_ID`),
  KEY `ke2` (`MATERIAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material_goods
-- ----------------------------

-- ----------------------------
-- Table structure for messageboard
-- ----------------------------
DROP TABLE IF EXISTS `messageboard`;
CREATE TABLE `messageboard` (
  `ID` varchar(255) NOT NULL,
  `NOTIFY_INFO_ID` varchar(255) DEFAULT NULL,
  `MESSAGE_BORAD_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messageboard
-- ----------------------------

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `ID` varchar(255) NOT NULL,
  `CREATER` varchar(255) DEFAULT NULL,
  `CREATER_TIME` datetime(6) DEFAULT NULL,
  `SEND_TIME` datetime(6) DEFAULT NULL,
  `RECEIVER_ID` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `NOTIFYDESCRIBE` varchar(255) DEFAULT NULL,
  `NOTIFYLEVEL` varchar(255) DEFAULT NULL,
  `ISREADY` varchar(255) DEFAULT NULL,
  `SENDER_ID` varchar(255) DEFAULT NULL,
  `NOTIFY_INFO_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notify
-- ----------------------------

-- ----------------------------
-- Table structure for notify_info
-- ----------------------------
DROP TABLE IF EXISTS `notify_info`;
CREATE TABLE `notify_info` (
  `ID` varchar(255) NOT NULL,
  `TARGET_ID` varchar(11) DEFAULT NULL,
  `TITLE` varchar(11) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `NOTIFY_TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notify_info
-- ----------------------------

-- ----------------------------
-- Table structure for purshaseorder
-- ----------------------------
DROP TABLE IF EXISTS `purshaseorder`;
CREATE TABLE `purshaseorder` (
  `ID` varchar(255) NOT NULL,
  `NUMBER` varchar(255) DEFAULT NULL,
  `GOODS_ID` varchar(255) DEFAULT NULL,
  `GOODS_NUMBER` varchar(255) DEFAULT NULL,
  `APPLY_USER` varchar(255) DEFAULT NULL,
  `APPLY_TIME` date DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `ORDER_AUDIT_USER` varchar(255) DEFAULT NULL,
  `ORDER_AUDIT_TIME` date DEFAULT NULL,
  `PAY_AUDIT_USER` varchar(255) DEFAULT NULL,
  `PAY_AUDIT_TIME` date DEFAULT NULL,
  `APPLY_DESCRIBE` varchar(255) DEFAULT NULL,
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purshaseorder
-- ----------------------------

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `ID` varchar(255) NOT NULL,
  `MESSAG_BOARD_ID` varchar(255) DEFAULT NULL,
  `REPLY_CONTENT` varchar(255) DEFAULT NULL,
  `REPLY_TIME` datetime(6) DEFAULT NULL,
  `REPLY_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `ID` varchar(255) NOT NULL,
  `SUPPLIER_NAME` varchar(255) DEFAULT NULL,
  `SUPPLIER_DESCRIBE` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `GOODS_TYPE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------

-- ----------------------------
-- Table structure for sys_access_permission_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_access_permission_test`;
CREATE TABLE `sys_access_permission_test` (
  `ID` varchar(32) NOT NULL COMMENT '访问权限表',
  `URL` varchar(50) DEFAULT NULL COMMENT '访问链接',
  `ROLES` varchar(255) DEFAULT NULL COMMENT '角色列表用,分割',
  `SORT` int(11) DEFAULT NULL COMMENT '排序号',
  `IS_DELETED` int(2) DEFAULT NULL COMMENT '是否删除',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '更新用户',
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `uidx_request_url` (`URL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_access_permission_test
-- ----------------------------
INSERT INTO `sys_access_permission_test` VALUES ('0311bbf688d44f74914eda6b95452a69', 'payment/*', 'authc,roleOr[财务员工,财务主管,admin]', '450', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('0810577496104977add9445b9916e30b', 'supplier/*', 'authc,roles[admin]', '809', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('0aefd0baf1d04267bc2290485e259c6f', 'purchase/*', 'authc,roleOr[仓库管理员,test,财务员工,财务主管,仓库主管,生产部员工,生产部主管,admin]', '715', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('1', '/', 'anon', '1', '0', 'admin', '2017-12-25 16:41:02', 'admin', '2017-12-25 16:41:02');
INSERT INTO `sys_access_permission_test` VALUES ('134ab928a4b74572a5e46af684d3e560', 'finance/*', 'authc,roleOr[财务员工,财务主管,admin]', '705', null, 'afabao', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('146f710968814fbd8e0fc88c7887bb41', 'saleReceivable/*', 'authc,roleOr[财务员工,财务主管,admin]', '696', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('187594505ee341ce9f9d3faa76db92fe', 'payableReceivableList/*', 'authc,roleOr[财务主管,admin]', '686', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('1f26caa699dc44fca14121bd213ece4f', '/formula/pageList', 'authc,roleOr[生产部主管,admin]', '573', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('2', '/index', 'anon', '2', '0', 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('268877c11e5e4920a74f78e6c85e52a6', 'w_return_goods_audit/*', 'authc,roles[admin]', '458', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('2bc2f2a442714b8fa68b59576a018fb0', 'material/*', 'authc,roles[admin]', '591', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('3', '/login', 'anon', '3', '0', 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('3196143d52944d6ba7b4dc106758737a', 'saleBillPayable/*', 'authc,roleOr[财务员工,财务主管,admin]', '428', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('356bd72b047c49f29a424fc1e23459f0', '12345/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '408', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('3957ac4ac9164f7f836b22c76c773f3b', '#', 'authc,roles[admin]', '125', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('3ab2ca8d48ac4e83a19e67eb3e4a822c', 'depotSet/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '175', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('4', '/role/*', 'authc,roles[admin]', '5', '0', 'admin', '2017-12-25 16:41:08', 'admin', '2017-12-25 16:41:08');
INSERT INTO `sys_access_permission_test` VALUES ('466e90e389204826b618cda4038d77f5', 'outBox/*', 'authc,roles[admin]', '381', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('48e998c2752b43f1ac9dd6fa6287a19b', 'depotOrderOut/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '631', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5', '/getVCode', 'anon', '4', '0', 'admin', '2017-12-25 16:41:09', 'admin', '2017-12-25 16:41:09');
INSERT INTO `sys_access_permission_test` VALUES ('59d8dbb827ba4d14b869e980596fa295', 'purchaseOrder/*', 'authc,roleOr[test,采购部门员工,采购部门经理,admin]', '161', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5b2138ea4df741bebabc03888ecdd87e', '11', 'authc,roles[admin]', '468', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5b4e68133f5d4a82824208a6c2da0a7e', 'chart/*', 'authc,roleOr[财务员工,财务主管,admin]', '418', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5d1852e0e2244162aed40391a069e02b', 'materialGoodsManager/*', 'authc,roles[admin]', '988', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5e2a19bb3e634f7ebe33607e1519762e', 'purchase/apply', 'authc,roleOr[test,采购部门员工,admin]', '374', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('5f2cfc8474434a01a2973ab9118c4c56', 'yuSupplier', 'authc,roles[admin]', '647', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('6', '/**/*.do', 'authc', '999', '0', 'admin', '2017-12-25 16:41:09', 'admin', '2017-12-25 16:41:09');
INSERT INTO `sys_access_permission_test` VALUES ('61a0df7b31d94d5da4f9439f02c5d6bb', 'w_return_goods_order/*', 'authc,roles[admin]', '752', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('6319f8a7688343139ac4ba0d40877e2c', 'sys/*', 'authc,roles[test,admin]', '201', '0', 'gameloft9', '2017-12-28 11:04:58', null, '2017-12-28 11:04:58');
INSERT INTO `sys_access_permission_test` VALUES ('6c7556a93d74465dabe30c900b6e83a1', 'purchaseReceivable/*', 'authc,roleOr[财务员工,财务主管,admin]', '135', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('7042e4eeebf5433388ddfdfb09764369', 'log/*', 'authc,roles[test,admin]', '419', '0', 'gameloft9', '2017-12-27 17:04:10', null, '2017-12-27 17:04:10');
INSERT INTO `sys_access_permission_test` VALUES ('764be8283a9343d38f84e4e062c81a25', 'purchaseReturn/*', 'authc,roleOr[test,采购部门员工,admin]', '319', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('78a5f49d70be49aa9d3ae8ff54d417f8', 'purchaseBillPayable/*', 'authc,roleOr[财务员工,admin]', '496', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('7c1f21aa63cf4fd6a5be43072e81ab99', 'test/*', 'authc,roles[test,admin]', '135', '1', 'gameloft9', '2017-12-28 11:05:10', null, '2017-12-28 11:05:10');
INSERT INTO `sys_access_permission_test` VALUES ('7ee176c20c904536862da37aacb0f7c8', 'org/*', 'authc,roles[test]', '123', '0', 'gameloft9', '2017-12-27 17:04:18', null, '2017-12-27 17:04:18');
INSERT INTO `sys_access_permission_test` VALUES ('84baae5ad4fb44cf99387dda64cfed21', '/supplier/supplierList', 'authc,roles[admin]', '757', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('89c16eecf0fe4c9690abc1733db35f59', 'billPayable/*', 'authc,roleOr[财务员工,admin]', '635', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('8cd0998025194fb1b53059c85e7ca66a', 'purApplyInsList/*', 'authc,roleOr[test,采购部门经理,admin]', '278', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('8e68507e882c4165b06a4a9147d14a34', 'role/*', 'authc,roles[admin]', '669', '0', 'gameloft9', '2017-12-27 17:04:04', null, '2017-12-27 17:04:04');
INSERT INTO `sys_access_permission_test` VALUES ('8e94019a831843f8bad8137267ae8f1d', 'inventory/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '907', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('90762decb8874ba0955eb8cef21b6682', 'menu/*', 'authc,roles[admin]', '197', '0', 'gameloft9', '2017-12-28 11:02:26', null, '2017-12-28 11:02:26');
INSERT INTO `sys_access_permission_test` VALUES ('908f22cbda0e45e79f70d1d738ae4595', 'purchaseReceivalbe/*', 'authc,roleOr[财务员工,财务主管,admin]', '440', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('941aca52c7c549b4b1b9d3df0904fcc5', 'depot/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '347', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('9892bcc0d5eb4f238c8caaf583b43f77', 'gathering/*', 'authc,roleOr[财务员工,财务主管,admin]', '167', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('9a71e5c3ce0f4535818f79892c6d86cf', 'bill/*', 'authc,roleOr[财务员工,财务主管,admin]', '153', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('ab23a910cd7444c29873c0bc1c28f7d0', 'checkPending/*', 'authc,roleOr[财务员工,财务主管,admin]', '366', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('abc6b6df604746568e61fa4488718d69', 'chargeOff/*', 'authc,roleOr[财务员工,财务主管,admin]', '680', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('b045fc66c27644d380d8b927136b766a', 'w_marker_order/**', 'authc,roles[admin]', '305', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('b3c22d27086d49b9a6e29e18d93fda42', '123/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '885', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('b7575ce0089e48beb22921f1a6d89102', 'w_marker_order/*', 'authc,roles[admin]', '137', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('bd1db6980a8d44b4920ba93d7ec3c178', 'message/*', 'authc,roles[admin]', '231', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('c24b55ca309c4e3aa311643543bf7c13', 'purReturnList/*', 'authc,roleOr[test,采购部门员工,admin]', '540', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('c8c4e92a1ece4da79ede1763faecdf77', 'sysUser/*', 'authc,roles[admin]', '170', '0', 'gameloft9', '2017-12-27 17:05:49', null, '2017-12-27 17:05:49');
INSERT INTO `sys_access_permission_test` VALUES ('c9bed41706944834b310f52c850253ea', 'yuSupplier/*', 'authc,roles[admin]', '565', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('cf0b58640b4f4b4aa55f23c1e9e4426d', 'receipt/*', 'authc,roleOr[财务员工,财务主管,admin]', '273', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('d0153dad60e94f99aa767d893f7d7a6d', '/productWaste/pageList', 'authc,roleOr[生产部主管,admin]', '994', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('d103b11ce8e0487b857f745645b94c1b', 'depotOrder/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '755', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('d5f13bc7af6d407ba13a712f60160feb', 'depotInventoryCheck/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '341', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('ddfb66bf2e684463a0c0567bdc028ebc', 'materialGoods/*', 'authc,roles[admin]', '124', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('e366c89b2daa4f869e2ed7182ae925b3', '/detail/pageList', 'authc,roleOr[生产部主管,admin]', '228', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('e418c710874347398177d4a1a42a353b', '1111', 'authc,roleOr[生产部员工,生产部主管,admin]', '902', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('e4208b83f5d44dea992339e9b8281925', '1234/*', 'authc,roleOr[仓库管理员,仓库主管,admin]', '279', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('e6db39bf787e44ca994b9098fc4ea1b6', '/product/pageList', 'authc,roleOr[生产部员工,生产部主管,admin]', '381', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('ee48dcda2b61414da731f30c441685c3', 'w_audti_order/*', 'authc,roles[admin]', '520', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('f5ae8348c1394619b588cb78005be609', 'page/system/sysFinance/check_pending.html', 'authc,roleOr[财务员工,财务主管,admin]', '213', null, 'afabao', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('f76cf4c924e0448483716082dbcc1df3', 'check_pending/*', 'authc,roleOr[财务主管,admin]', '536', null, 'afabao', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('fb576a66bfa4462691be0381f9a3b6fd', 'w_shipment_order/*', 'authc,roles[admin]', '794', null, 'admin', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('ffa68ea27b7c4098aa034425534d6476', 'saleReceivalbe', 'authc,roleOr[财务员工,财务主管,admin]', '385', null, 'gameloft9', null, null, null);
INSERT INTO `sys_access_permission_test` VALUES ('ffabb58ebec34b219351896e57d8c0c1', 'purInList/*', 'authc,roleOr[test,采购部门员工,admin]', '219', null, 'gameloft9', null, null, null);

-- ----------------------------
-- Table structure for sys_material
-- ----------------------------
DROP TABLE IF EXISTS `sys_material`;
CREATE TABLE `sys_material` (
  `ID` varchar(255) NOT NULL,
  `GOODS_NAME` varchar(255) NOT NULL,
  `GOODS_TYPE` varchar(255) NOT NULL,
  `GOODS_DESCRIBE` varchar(255) NOT NULL,
  `GOODS_SPECIFICATION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_material
-- ----------------------------
INSERT INTO `sys_material` VALUES ('1', '芝麻白花岗石', '花岗岩', '广东浪花白大花', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('2', '樱桃红花岗石', '花岗岩', '花岗岩石材粉红麻', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('3', '汉白玉', '玉石', '天然大理石板雪花白', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('4', '云浮大理石', '大理石', '云朵拉灰水刀拼花石材', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('5', '白色细砂石', '矿物颗粒', '白石子', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('6', '彩色砂石', '矿物颗粒', '彩色', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('69333e158fcc4a80913981fbc2df0bd4', '雪花白大理石', '大理石', '国产大理石', '10cm*10cm*10cm');
INSERT INTO `sys_material` VALUES ('7', '黑色板岩', '变质岩', '天然青石板板岩', '20cm*20cm*3cm');
INSERT INTO `sys_material` VALUES ('8', '黄木纹板岩', '变质岩', ' 绿色冰裂纹', '30cm*40cm*5cm');
INSERT INTO `sys_material` VALUES ('decc34f2f3954795b60f77596a5226b6', '意大利木纹大理石', '大理石', '进口大理石', '50cm*30cm*3cm');
INSERT INTO `sys_material` VALUES ('fffd12c528db45e48016139d9089d5e4', '象牙米黄', '大理石', '土耳其产进口大理石', '400cm*267cm*5cm');

-- ----------------------------
-- Table structure for sys_material_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_material_goods`;
CREATE TABLE `sys_material_goods` (
  `ID` varchar(255) NOT NULL,
  `SUPPLIER_ID` varchar(255) NOT NULL,
  `MATERIAL_ID` varchar(255) NOT NULL,
  `GOODS_PRICE` varchar(255) NOT NULL,
  `GOODS_ORIGIN_PLACE` varchar(255) DEFAULT NULL,
  `IMAGE_URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_material_goods
-- ----------------------------
INSERT INTO `sys_material_goods` VALUES ('1faa767ff0e84434b5f3ab6adb6c4f75', '33eb96d3ccd840d6a9e9cf45fbee3f5f', '1', '120', '青岛', '/../../../../image/1554272944696u=4111272052,3315505947&fm=214&gp=0.jpg');
INSERT INTO `sys_material_goods` VALUES ('4498e94ab4744a798962a75000920641', 'd6f367d5ba614781b7ed7f1949354465', '3', '200', '九江', '/../../../../image/155356713209015522042174261.jpg');
INSERT INTO `sys_material_goods` VALUES ('4ffa35972038419b8b9599be77b03fa7', 'b24535e3b7484e29b76c39f69382b934', 'decc34f2f3954795b60f77596a5226b6', '350', '意大利皇家矿山', '/../../../../image/1554273153783意大利木纹大理石.jpg');
INSERT INTO `sys_material_goods` VALUES ('555127273ac04f868f5cbb737c664bd0', '556e448e34ee47cd9dc22619e08cf08e', '1', '500', '四川', '/../../../../image/155438174204615522042174261.jpg');
INSERT INTO `sys_material_goods` VALUES ('595ff4a952e8455382194af2576d9225', 'cc8052bad1874d4b9255d798ad79d987', '2', '240', '厦门', '/../../../../image/1554273106823樱桃红花岗石.jpg');
INSERT INTO `sys_material_goods` VALUES ('6413bc2019e940fca2a8d6f37d4d84c2', 'd6f367d5ba614781b7ed7f1949354465', '7', '150', '苏州', '/../../../../image/155356732690315522042174261.jpg');
INSERT INTO `sys_material_goods` VALUES ('8d648a85857842a8b1a932d867afdab2', 'cc8052bad1874d4b9255d798ad79d987', 'fffd12c528db45e48016139d9089d5e4', '100', '土耳其', '/../../../../image/1554273153783意大利木纹大理石.jpg');
INSERT INTO `sys_material_goods` VALUES ('d3a0520e6ca649e08255f4cce6388c25', 'd6f367d5ba614781b7ed7f1949354465', '5', '100', 'xxxx', '/../../../../image/1554273153783意大利木纹大理石.jpg');
INSERT INTO `sys_material_goods` VALUES ('edc4cc3f7da4486c8a0bb3cafe4ec772', '33eb96d3ccd840d6a9e9cf45fbee3f5f', '69333e158fcc4a80913981fbc2df0bd4', '230', '青岛', '/../../../../image/1554297212403u=4111272052,3315505947&fm=214&gp=0.jpg');
INSERT INTO `sys_material_goods` VALUES ('f10da94bac4d42e48136e888196cca5b', 'd6f367d5ba614781b7ed7f1949354465', '2', '490', '云南', '/../../../../image/1554273153783意大利木纹大理石.jpg');
INSERT INTO `sys_material_goods` VALUES ('f1e3b03f6db346c8a182bd0bf88b5a71', '6c3899edc0e24bd5befebc7e72633c7d', '69333e158fcc4a80913981fbc2df0bd4', '550', '湖北', '/../../../../image/1554272980230u=4111272052,3315505947&fm=214&gp=0.jpg');
INSERT INTO `sys_material_goods` VALUES ('fd39a6bf72e745ad9901956e98c3eed2', '93c01eeff9cc46a7ab9242eef5a3accf', '3', '800', '河南', '/../../../../image/1554273047507汉白玉.jpg');

-- ----------------------------
-- Table structure for sys_menu_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role_test`;
CREATE TABLE `sys_menu_role_test` (
  `ID` varchar(32) NOT NULL,
  `MENU_ID` varchar(32) DEFAULT NULL COMMENT '菜单id',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色id',
  `CREATE_USER` varchar(32) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_USER` varchar(32) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `uidx_menu_role` (`MENU_ID`,`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu_role_test
-- ----------------------------
INSERT INTO `sys_menu_role_test` VALUES ('00d5af4e3a4b475c9d726677af20846d', 'e42a3d261f0647b78285133918961e3e', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('022409ce02874f65a6783e3d28d481ff', '2fb26cc452354652a33974e1a8327465', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('02954f39dbf3420f84ad8c75c5e980fe', '44686e5cc03048f99cc36228326dcad2', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('057e472f770e41a5a8486b5cf250d467', '645c077a0f0546f5a7b43f6a3f2492ed', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1003f5ab48674497a6148f2542f990b1', '747229705bac48d3b1d726f26532c585', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1208f19ea7ff42948e8bf2eead3b2e39', '86856fbb730c4cf9a7deee517a3bb4b5', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1209be4229164243a78bd7d4c7ee65b4', '900ed0ecd11c42f3b5f7b0ed34b08fc0', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('123456789ijhg', '244247008b53450fa16bc503bc861b7c', 'J/F9-+?', null, '2017-12-26 09:46:03', null, '2017-12-26 09:46:03');
INSERT INTO `sys_menu_role_test` VALUES ('13ebc39d0250463499ab23e5eb7a5007', 'c0d2fb742fc44b418585c86e6c0f10d6', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1459b59795b3424f9fc7081c0477cc29', 'e525badeca8c4f98873e83b2c601fe85', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('191cc7fa9e074c4bbf0269c7df694e40', 'a167bb34292549218fa276a71f1305d9', 'eaceb6783430421090b8f84c57e48df5', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1929b27ff41c4206abedb5202ee21efa', '03d339905f094c28ab163dfe7eba12f3', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('1a718a52f78f4b35b898c79bff219df7', 'e525badeca8c4f98873e83b2c601fe85', '4449f85a9f014489aba2c4b3cc4cd9da', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('21cdc0d9d5df490ebdd4dac8fc63ce15', '03fbabb282f5492ba91e1e2842f9eb3e', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('22640febbbd34020926be4a8c933dc9d', '3775dec65b054a6d8fc9823857495412', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('2b0b54f9da4a45ef877442f777c77648', '0ca9466a975941f488fce15159144d24', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('2b71090abd464e8d96774e395b60c936', '8420460ef82e487e972d90f3d70ecf0a', 'eaceb6783430421090b8f84c57e48df5', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('2c01aa00dbdd4070afc18db2ac742ece', 'd47b0e6ffd72493faf08d4fb6fef8e18', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('300357f5b56f4e10a1be5279ee40ef35', '5311e475980344bfb54aeb96f322ebc7', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('3032441c65c14f52ab25fadac0ab9379', '645c077a0f0546f5a7b43f6a3f2492ed', '4449f85a9f014489aba2c4b3cc4cd9da', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('3a8c573921494c99afa7d4a6081cf399', '8262c0f7a4e24558b77c229e28cf22f1', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('414060aa9d23498f9fd0d064b6cca295', '631fc820ce1047b78be9a7d6f93314c0', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('41b6cb889b7446f6ba0e1035fc1eae7b', '03d339905f094c28ab163dfe7eba12f3', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('4523b393aae64771aac4f91c5623599b', '244247008b53450fa16bc503bc861b7c', '343859934ece44c988f53700fb34c80a', null, '2018-01-11 14:47:33', null, '2018-01-11 14:47:33');
INSERT INTO `sys_menu_role_test` VALUES ('46aba7e9b0c3488db17fc7a0e1712f0f', '1a9e663137884aeea5ba2553f62e3b12', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('483ac9aaa21d40618988be1a2edc00e7', '747229705bac48d3b1d726f26532c585', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('48a1a3f043634360ac3dc404a7bd4f48', '8599374cc3ac4ed9af198b406f9fede3', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('4fe64750313b42e1a74ed85906a2d971', 'f84240d345c049c29da23bd1177f90a9', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('5268b8dc0a1c43898306dcd16887fb64', '645c077a0f0546f5a7b43f6a3f2492ed', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('5545e1f97b714b66b69fd2c6896bdee2', 'e5104dcf5aae4ece99b6667ff245b819', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('56c98c3da1e5439bb31abfbbbe930a6c', 'd47b0e6ffd72493faf08d4fb6fef8e18', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('571274758a80479ab06e6227c75d968f', '688555c902a94a588000703c8d46e061', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('578ee66c2182441a87468dc9fdc23479', 'c7479de1ef1846d4bbef5aa4c00c18e9', 'eaceb6783430421090b8f84c57e48df5', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('58b423e9983d4677a0cfd038a96fa50a', '688555c902a94a588000703c8d46e061', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('5905556900e741a89aafec25948b8fca', '89e9f1eb41a240fea361e0d188375884', 'J/F9-+?', null, '2017-12-27 17:04:18', null, '2017-12-27 17:04:18');
INSERT INTO `sys_menu_role_test` VALUES ('5a3eecc80b0f4d289358ac81b9eb328b', '5153f5d5ab8f4922ab1a4b8a6bbc384e', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('5a6933a6510d4535b7984f5d9c1afc9f', '002dbed93cd64149a0150d61a4932b55', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('5b79d6e436154cfb9533a531fb7eb744', '882b26afbdbb4d63bcba0cd826a5b9c1', '343859934ece44c988f53700fb34c80a', null, '2018-01-11 14:47:16', null, '2018-01-11 14:47:16');
INSERT INTO `sys_menu_role_test` VALUES ('5bc943122f044aa9bc0181f6f8ef2481', 'ec55c087e1f943f4bc5fb78e7452ffb9', 'eaceb6783430421090b8f84c57e48df5', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('602d927821f044dc88bd642e8aa62eef', 'e23af0ef41e14c719171e735f4946120', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('60be538eb3e3483bb4d32c9efe83021b', '5b44d3142bf04903a38b451fb706af89', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('615d7c0658ac4b8aa3b05997e0bc1f84', '8420460ef82e487e972d90f3d70ecf0a', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('6188b9cf05434966916e29e1bec6bbeb', 'e5104dcf5aae4ece99b6667ff245b819', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('6302f705514e4c839400f804ec29ec5c', '89e9f1eb41a240fea361e0d188375884', '343859934ece44c988f53700fb34c80a', null, '2018-01-11 14:46:55', null, '2018-01-11 14:46:55');
INSERT INTO `sys_menu_role_test` VALUES ('63d7b2c854ec4dbea956fa971e0c32aa', '747229705bac48d3b1d726f26532c585', '58b02f3c30084e9a8124adb86373ad65', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('6e9d140683be4e45b76270d503e95fbe', '0f8e43c68f034bd1a227b222f7ade0f6', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('71fa522e4da04ab9abd6008d2cb1c070', '26b3df2c6d464a0b89858eb896b849d2', 'J/F9-+?', null, '2017-12-27 17:05:49', null, '2017-12-27 17:05:49');
INSERT INTO `sys_menu_role_test` VALUES ('7216697355934db0b26b3cedeb8a894d', '0ca9466a975941f488fce15159144d24', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('7292ac919daf473b807cc131bfce17ca', '0f8e43c68f034bd1a227b222f7ade0f6', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('7bb527653afb4926bfd118a6146756f6', '8599374cc3ac4ed9af198b406f9fede3', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('80b173f66ad346c58ff1638e82964d73', '3775dec65b054a6d8fc9823857495412', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('89361ddbb79a4de486bbeabbfd7e5e83', '5311e475980344bfb54aeb96f322ebc7', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('8cc3e5937a514241914591ab89300e25', 'e961ef46d0c34ff78a7570ad42f2722a', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('922c4800e4564dc78ffe7d5c846cf540', 'c0d2fb742fc44b418585c86e6c0f10d6', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('9251f489ec8c4c9a82b206c349e7e59f', 'e525badeca8c4f98873e83b2c601fe85', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('97ab8c85a1eb4eacb1c72cd8c1075449', '03d339905f094c28ab163dfe7eba12f3', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('9c347cceaf94404c9562d47814ff779e', '0ca9466a975941f488fce15159144d24', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('9c53c629bda948c0af8ead2fea2f2f40', 'a167bb34292549218fa276a71f1305d9', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('9ced52ab6c694ba1a5781bfba44d6b50', 'e42a3d261f0647b78285133918961e3e', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('9f458bc508474f4a9385b123e0aa10eb', '20ce98ab081742c18b28df44c04a3994', 'eaceb6783430421090b8f84c57e48df5', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a0265761a1cb4c95b757f32ec48fa5ee', '86856fbb730c4cf9a7deee517a3bb4b5', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a0e3926c01594530922b0e288ee077e8', '747229705bac48d3b1d726f26532c585', '4449f85a9f014489aba2c4b3cc4cd9da', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a1b5dfd03f46483fa6ad71b6af04c1bc', 'c90564516f9a48259792e4ca30d3c1b9', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a378ff2d9c424e1c8e6f3fbb90347eb4', 'd7990d1e9f75495981919fb6bdd00c98', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a3f681f3b6404e48b1b04199ec94e35b', 'c7479de1ef1846d4bbef5aa4c00c18e9', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a6c48d7e35d4483799ef8db467c7ee11', '3775dec65b054a6d8fc9823857495412', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a8c5d041f32346269c83ccefc7d4197e', 'f84240d345c049c29da23bd1177f90a9', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('a9587343d96b40d28e15d03bf5a95667', '40db5a9449b14d0cb8d109fcd3656bba', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('aaa33b8d4969465abfa0466d0a17c59c', 'c0d2fb742fc44b418585c86e6c0f10d6', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('aaf54b5ae18543c3915d8c1c096bd1ba', '8599374cc3ac4ed9af198b406f9fede3', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('ad93e63b0b924537865a3614711a09e5', 'cc1cccc5482d400796b054586945b280', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('asdfr456yhbv123', '4a7f3cae133e4a51b8f35769b55163dd', 'J/F9-+?', null, '2017-12-26 09:46:29', null, '2017-12-26 09:46:29');
INSERT INTO `sys_menu_role_test` VALUES ('bd3b96f8742940e8acc628ff5c940465', 'b53bfdfe33444703aa76c2c1a1b8d820', 'J/F9-+?', null, '2017-12-27 17:04:03', null, '2017-12-27 17:04:03');
INSERT INTO `sys_menu_role_test` VALUES ('bd6b058f43e5432f8f73e9d900c9a64c', 'c7479de1ef1846d4bbef5aa4c00c18e9', 'bf9286a4918b4a3c84267b3e2e0fea12', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('c0e267e7de3647af86e7507373f4ea91', 'ec55c087e1f943f4bc5fb78e7452ffb9', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('c550ac3bb26c4e0a882a920366050579', '900ed0ecd11c42f3b5f7b0ed34b08fc0', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('c89cc73ffa1b40da943cb542b9b9819f', '882b26afbdbb4d63bcba0cd826a5b9c1', 'J/F9-+?', null, '2017-12-27 17:04:10', null, '2017-12-27 17:04:10');
INSERT INTO `sys_menu_role_test` VALUES ('cf47a8f9bae74c579d46ecc138ec8d8a', 'e42a3d261f0647b78285133918961e3e', '98cd74e57fce4023a3470b1734e059a0', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('d5e955cd4c5a4e00aab850a3d0f24f22', '900ed0ecd11c42f3b5f7b0ed34b08fc0', '0a7a39c04bcb4f788b272aff132136ef', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('e59443e86bb24cc3aa9fbeffc175b636', '0f8e43c68f034bd1a227b222f7ade0f6', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('e63d0cfc8f2e44eea7c53e391699c361', '20ce98ab081742c18b28df44c04a3994', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f066f9f33c14452696595c719308db14', '86856fbb730c4cf9a7deee517a3bb4b5', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f155f4983f614b0ba8b779fef439e34b', 'c90564516f9a48259792e4ca30d3c1b9', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f1dc66bb9bd9413bb0eaf086b1829962', '688555c902a94a588000703c8d46e061', 'J/F9-+?', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f22aa1ff717b468eb5f1bb9775bda944', 'e5104dcf5aae4ece99b6667ff245b819', '58b02f3c30084e9a8124adb86373ad65', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f6786e720db74ba598ffd9c664f85e4f', 'a167bb34292549218fa276a71f1305d9', 'bf9286a4918b4a3c84267b3e2e0fea12', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f7f0c8ee441c45af82aec63f404e2648', 'cc1cccc5482d400796b054586945b280', '343859934ece44c988f53700fb34c80a', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('f9973d0b664546aa923a8f61b1679bc5', '5311e475980344bfb54aeb96f322ebc7', '809df45d06204d8fbd19019b0db3455d', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('fa023b41c1e6454eb2c342966dc973a7', 'c90564516f9a48259792e4ca30d3c1b9', '4449f85a9f014489aba2c4b3cc4cd9da', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('fa7f31399924465abc5308d1e1bff97b', 'cc1cccc5482d400796b054586945b280', '4449f85a9f014489aba2c4b3cc4cd9da', null, null, null, null);
INSERT INTO `sys_menu_role_test` VALUES ('fdf13367a016443cb1fed61f89b9188b', 'd47b0e6ffd72493faf08d4fb6fef8e18', '8449ee9c37ce405fa8c51756bf5f1c38', null, null, null, null);

-- ----------------------------
-- Table structure for sys_menu_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_test`;
CREATE TABLE `sys_menu_test` (
  `ID` varchar(32) NOT NULL COMMENT '菜单表',
  `TITLE` varchar(100) NOT NULL COMMENT '菜单名称',
  `HREF` varchar(200) NOT NULL COMMENT '访问链接',
  `REQUEST_URL` varchar(200) DEFAULT NULL COMMENT '后台请求url',
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `CODE` varchar(100) DEFAULT NULL COMMENT '菜单编码',
  `TARGET` varchar(50) DEFAULT NULL COMMENT '目标窗口,_blank则直接跳转，否则在子页面打开',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父菜单',
  `SORT` int(2) DEFAULT NULL COMMENT '排序号',
  `CREATE_USER` varchar(32) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_USER` varchar(32) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `uidx_title` (`TITLE`) USING BTREE,
  UNIQUE KEY `uidx_menu_req_url` (`REQUEST_URL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu_test
-- ----------------------------
INSERT INTO `sys_menu_test` VALUES ('002dbed93cd64149a0150d61a4932b55', '退货单', 'page/system/sysReturnGoodsOrder/allReturnGoodsOrders.html', 'w_return_goods_order/*', 'icon-text', '8-4', null, '1a9e663137884aeea5ba2553f62e3b12', '4', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('03d339905f094c28ab163dfe7eba12f3', '仓库管理', '#', 'depot/*', 'icon-text', '4', null, null, '4', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('03fbabb282f5492ba91e1e2842f9eb3e', '消息管理', '#', '#', 'icon-text', '10', null, null, '11', 'gameloft9', '2019-04-03 22:47:13', null, '2019-04-03 22:47:13');
INSERT INTO `sys_menu_test` VALUES ('0ca9466a975941f488fce15159144d24', '账单', 'page/system/sysFinance/bill.html', 'bill/*', 'icon-text', '3-5', null, '5311e475980344bfb54aeb96f322ebc7', '5', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('0f8e43c68f034bd1a227b222f7ade0f6', '收款单', 'page/system/sysFinance/receipt.html', 'receipt/*', 'icon-text', '3-3', null, '5311e475980344bfb54aeb96f322ebc7', '3', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('1a9e663137884aeea5ba2553f62e3b12', '销售管理', '#', 'w_marker_order/*', 'icon-text', '8', null, null, '8', 'admin', '2019-04-02 20:04:24', null, '2019-04-02 20:04:24');
INSERT INTO `sys_menu_test` VALUES ('20ce98ab081742c18b28df44c04a3994', '生产配方详情', 'page/system/ProductDepart/allProduceFormulaDetail.html', '/detail/pageList', 'icon-text', '6-4', null, 'c7479de1ef1846d4bbef5aa4c00c18e9', '4', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('244247008b53450fa16bc503bc861b7c', '系统管理', '#', 'sys/*', 'icon-text', '1', null, null, '1', 'gameloft9', '2017-12-25 19:31:22', null, '2017-12-25 19:31:22');
INSERT INTO `sys_menu_test` VALUES ('26b3df2c6d464a0b89858eb896b849d2', '系统用户管理', 'page/system/sysUser/allUsers.html', 'sysUser/*', 'icon-text', '1-5', null, '244247008b53450fa16bc503bc861b7c', '4', 'gameloft9', '2017-12-27 17:05:40', null, '2017-12-27 17:05:40');
INSERT INTO `sys_menu_test` VALUES ('2fb26cc452354652a33974e1a8327465', '供应商管理', 'page/system/sysSupplier/allSupplier.html', 'supplier/*', 'icon-text', '7-1', null, '8262c0f7a4e24558b77c229e28cf22f1', '1', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('3775dec65b054a6d8fc9823857495412', '图表', 'page/system/sysFinance/chart.html', 'chart/*', 'icon-text', '3-6', null, '5311e475980344bfb54aeb96f322ebc7', '6', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('40db5a9449b14d0cb8d109fcd3656bba', '发件箱', 'page/system/outBox/allOutBox.html', 'outBox/*', 'icon-text', '10-1', null, '03fbabb282f5492ba91e1e2842f9eb3e', '2', 'gameloft9', '2019-04-03 22:56:36', null, '2019-04-03 22:56:36');
INSERT INTO `sys_menu_test` VALUES ('44686e5cc03048f99cc36228326dcad2', '销售订单审核', 'page/system/sysOrderAudit/allOrderAudits.html', 'w_audti_order/*', 'icon-text', '8-2', null, '1a9e663137884aeea5ba2553f62e3b12', '2', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('4a7f3cae133e4a51b8f35769b55163dd', '菜单管理', 'page/system/sysMenu/allMenus.html', 'menu/*', 'icon-text', '1-1', null, '244247008b53450fa16bc503bc861b7c', '1', 'gameloft9', '2017-12-25 19:32:25', null, '2017-12-25 19:32:25');
INSERT INTO `sys_menu_test` VALUES ('5153f5d5ab8f4922ab1a4b8a6bbc384e', '销售订单', 'page/system/sysMarkerOrder/allMarkerOrders.html', 'w_marker_order/**', 'icon-text', '8-1', null, '1a9e663137884aeea5ba2553f62e3b12', '1', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('5311e475980344bfb54aeb96f322ebc7', '财务管理', '#', 'finance/*', 'icon-text', '3', null, null, '3', 'afabao', '2019-03-18 10:26:52', null, '2019-03-18 10:26:52');
INSERT INTO `sys_menu_test` VALUES ('5b44d3142bf04903a38b451fb706af89', '发货单', 'page/system/sysShipmentOrder/allShipmentOrders.html', 'w_shipment_order/*', 'icon-text', '8-3', null, '1a9e663137884aeea5ba2553f62e3b12', '3', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('631fc820ce1047b78be9a7d6f93314c0', '收件箱', 'page/system/message/allMessage.html', 'message/*', 'icon-text', '10-2', null, '03fbabb282f5492ba91e1e2842f9eb3e', '111', 'gameloft9', '2019-04-03 23:05:58', null, '2019-04-03 23:05:58');
INSERT INTO `sys_menu_test` VALUES ('645c077a0f0546f5a7b43f6a3f2492ed', '申请退货', 'page/system/purReturn/purReturnList.html', 'purReturnList/*', 'icon-text', '9-2', null, 'c90564516f9a48259792e4ca30d3c1b9', '2', 'gameloft9', '2019-04-03 17:29:41', null, '2019-04-03 17:29:41');
INSERT INTO `sys_menu_test` VALUES ('688555c902a94a588000703c8d46e061', '申请单', 'page/system/sysFinance/checkPending.html', 'checkPending/*', 'icon-text', '3-1', null, '5311e475980344bfb54aeb96f322ebc7', '1', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('747229705bac48d3b1d726f26532c585', '采购订单管理', '#', 'purchaseOrder/*', 'icon-text', '5', null, null, '9', 'gameloft9', '2019-04-03 14:43:09', null, '2019-04-03 14:43:09');
INSERT INTO `sys_menu_test` VALUES ('8262c0f7a4e24558b77c229e28cf22f1', '原料商品管理', '#', 'materialGoodsManager/*', 'icon-text', '7', null, null, '7', 'gameloft9', '2019-03-23 14:45:34', null, '2019-03-23 14:45:34');
INSERT INTO `sys_menu_test` VALUES ('8420460ef82e487e972d90f3d70ecf0a', '生产配方管理', 'page/system/ProductDepart/allProduceFormula.html', '/formula/pageList', 'icon-text', '6-2', null, 'c7479de1ef1846d4bbef5aa4c00c18e9', '2', 'gameloft9', '2019-03-22 07:59:58', null, '2019-03-22 07:59:58');
INSERT INTO `sys_menu_test` VALUES ('8599374cc3ac4ed9af198b406f9fede3', '库存列表', 'page/system/sysDepot/allDepotInventory.html', 'inventory/*', 'icon-text', '4-2', null, '03d339905f094c28ab163dfe7eba12f3', '2', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('86856fbb730c4cf9a7deee517a3bb4b5', '货物入库', 'page/system/sysDepot/allDepotOrderIn.html', 'depotOrder/*', 'icon-text', '4-3', null, '03d339905f094c28ab163dfe7eba12f3', '3', 'admin', '2019-03-28 12:55:00', null, '2019-03-28 12:55:00');
INSERT INTO `sys_menu_test` VALUES ('882b26afbdbb4d63bcba0cd826a5b9c1', '系统日志管理', 'page/system/sysLog/allLogs.html', 'log/*', 'icon-text', '1-4', null, '244247008b53450fa16bc503bc861b7c', '5', 'gameloft9', '2017-12-27 17:03:41', null, '2017-12-27 17:03:41');
INSERT INTO `sys_menu_test` VALUES ('89e9f1eb41a240fea361e0d188375884', '机构管理', 'page/system/sysOrg/allOrgs.html', 'org/*', 'icon-text', '1-2', null, '244247008b53450fa16bc503bc861b7c', '3', 'gameloft9', '2017-12-27 17:01:58', null, '2017-12-27 17:01:58');
INSERT INTO `sys_menu_test` VALUES ('900ed0ecd11c42f3b5f7b0ed34b08fc0', '货物出库', 'page/system/sysDepot/allDepotOrderOut.html', 'depotOrderOut/*', 'icon-text', '4-4', null, '03d339905f094c28ab163dfe7eba12f3', '4', 'admin', '2019-03-28 13:44:53', null, '2019-03-28 13:44:53');
INSERT INTO `sys_menu_test` VALUES ('a167bb34292549218fa276a71f1305d9', '产品列表', 'page/system/ProductDepart/allProduct.html', '/product/pageList', 'icon-text', '6-1', null, 'c7479de1ef1846d4bbef5aa4c00c18e9', '1', 'gameloft9', '2019-03-22 08:03:31', null, '2019-03-22 08:03:31');
INSERT INTO `sys_menu_test` VALUES ('b53bfdfe33444703aa76c2c1a1b8d820', '角色管理', 'page/system/sysRole/allRoles.html', 'role/*', 'icon-text', '1-3', null, '244247008b53450fa16bc503bc861b7c', '2', 'gameloft9', '2017-12-27 17:02:42', null, '2017-12-27 17:02:42');
INSERT INTO `sys_menu_test` VALUES ('c0d2fb742fc44b418585c86e6c0f10d6', '仓库设置', 'page/system/sysDepot/allDepot.html', 'depotSet/*', 'icon-text', '4-1', null, '03d339905f094c28ab163dfe7eba12f3', '1', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('c7479de1ef1846d4bbef5aa4c00c18e9', '生产管理', '###', '1111', 'icon-text', '6', null, null, '1', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('c90564516f9a48259792e4ca30d3c1b9', '采购收退货管理', '#', 'purchaseReturn/*', 'icon-text', '9', null, null, '10', 'gameloft9', '2019-04-03 14:44:01', null, '2019-04-03 14:44:01');
INSERT INTO `sys_menu_test` VALUES ('cc1cccc5482d400796b054586945b280', '采购申请', 'page/system/purOrder/purApplyList.html', 'purchase/apply', 'icon-text', '5-1', null, '747229705bac48d3b1d726f26532c585', '1', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('d47b0e6ffd72493faf08d4fb6fef8e18', '付款单', 'page/system/sysFinance/payment.html', 'payment/*', 'icon-text', '3-4', null, '5311e475980344bfb54aeb96f322ebc7', '4', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('d7990d1e9f75495981919fb6bdd00c98', '退货单审核', 'page/system/sysReturnGoodsAudit/allReturnGoodsAudits.html', 'w_return_goods_audit/*', 'icon-text', '8-5', null, '1a9e663137884aeea5ba2553f62e3b12', '5', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('e23af0ef41e14c719171e735f4946120', '原料商品', 'page/system/sysMaterial/allMaterial.html', 'material/*', 'icon-text', '7-2', null, '8262c0f7a4e24558b77c229e28cf22f1', '2', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('e42a3d261f0647b78285133918961e3e', '库存盘点', 'page/system/sysDepot/allDepotInventoryCheck.html', 'depotInventoryCheck/*', 'icon-text', '4-5', null, '03d339905f094c28ab163dfe7eba12f3', '5', 'admin', '2019-03-30 14:53:28', null, '2019-03-30 14:53:28');
INSERT INTO `sys_menu_test` VALUES ('e5104dcf5aae4ece99b6667ff245b819', '审核订单', 'page/system/purOrder/purApplyInsList.html', 'purApplyInsList/*', 'icon-text', '5-2', null, '747229705bac48d3b1d726f26532c585', '2', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('e525badeca8c4f98873e83b2c601fe85', '采购入库', 'page/system/purOrder/purInList.html', 'purInList/*', 'icon-text', '9-1', null, 'c90564516f9a48259792e4ca30d3c1b9', '1', 'gameloft9', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('e961ef46d0c34ff78a7570ad42f2722a', '原料商品一览表', 'page/system/sysMaterialGoods/allMaterialGoods.html', 'materialGoods/*', 'icon-text', '7-3', null, '8262c0f7a4e24558b77c229e28cf22f1', '3', 'admin', null, null, null);
INSERT INTO `sys_menu_test` VALUES ('ec55c087e1f943f4bc5fb78e7452ffb9', '生产废料管理', 'page/system/ProductDepart/productWaste.html', '/productWaste/pageList', 'icon-text', '6-3', null, 'c7479de1ef1846d4bbef5aa4c00c18e9', '3', 'gameloft9', '2019-03-22 08:01:42', null, '2019-03-22 08:01:42');
INSERT INTO `sys_menu_test` VALUES ('f84240d345c049c29da23bd1177f90a9', '应收、付单列表', 'page/system/sysFinance/payableReceivableList.html', 'payableReceivableList/*', 'icon-text', '3-2', null, '5311e475980344bfb54aeb96f322ebc7', '2', 'gameloft9', null, null, null);

-- ----------------------------
-- Table structure for sys_oper_log_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log_test`;
CREATE TABLE `sys_oper_log_test` (
  `ID` varchar(32) NOT NULL COMMENT '应用日志表',
  `USER_ID` varchar(32) DEFAULT NULL,
  `LOGIN_NAME` varchar(32) DEFAULT NULL,
  `IP_ADDR` varchar(64) DEFAULT NULL,
  `OPER_TYPE` varchar(200) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `MEMO` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log_test
-- ----------------------------
INSERT INTO `sys_oper_log_test` VALUES ('00b86358511d4f878b15f6f89bd879d4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:06:12', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('00fc9c5a46d84f6b986d66daded16c3b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 04:47:54', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0110ec404a5046349bf604ecaaf35d6a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 10:11:12', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('0152e9ffbfbf4a3cb97a86d05aa94fb9', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 15:31:33', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('018e9225a5274efd8b5c95a6bc86f444', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:44:33', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('019690b1d4c144f69169699d9f09c53a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:03:18', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('01af430d85d34c93b0771cd65095090b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 11:29:37', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('01ba10a07e434297bbeab6650e20f014', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:57:10', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('02a191b945b14860a2894cb32e40617e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 02:27:15', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('02c3d9973b134d21a503b510a8ff7ed4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 03:17:14', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('03d8090e04c344edafc2acc4661b8632', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:50:12', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('040ce080365947248241afed63a14832', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 08:54:49', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0424d5326a8d4b5c98e7d5d87fc846a7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:48:09', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('04491afbe513463dac6f7b39369e5e4a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:19:15', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('0488fbef697f4bc1835ba079f58a112a', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:32:22', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0550b6ee6c8f40a79d0805ada28b7fc5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:57:33', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('056b0d85072f4fdcb32b46a11d26b426', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('059fb6a53d8645c492d9eb53f3da44d0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:10:41', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('05ac331317c74eb6abd3c2d43413e349', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:31', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('05b9a2d495b24f168a7e3d320b774c8e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:16:54', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('05c746f016dc4b92a951719baec927d5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:04:09', '更新个人密码');
INSERT INTO `sys_oper_log_test` VALUES ('05ef88d1d2124acea69b15a55276bda6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 15:30:10', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('063474a0343648a4b0dbee65cc08c3ef', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:32:51', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('07093660f05b4f4583fb30310e00b044', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 12:59:40', '更新仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('070e5c914c194d23aae9ae643ba21841', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:55:55', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('0728c8cf6f65451ea69df9ed57fc1952', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:57:33', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('072b69a98c3b48d69ceb354f7752de61', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:48:44', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('077a29d944424dd685a8a5df9dff6ba3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:01:40', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('079ce5a343614b769a4a23a3da6c203a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:05:00', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('07a50ca8fe2d4e2eb59e0322f19b0304', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:25:43', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('07f766240a204e82b55b95a2607b8c02', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 09:29:49', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('083076b4b14f4ef5a11b326c4cd24641', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:58', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('084d2f238736480a99a845d52ea45598', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 12:06:26', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('08bd639503d143e9a9f42c90c6568bb4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:40', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0904c38b405c40fcab6b52195d94da0e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:34:36', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('09777abf84e44390a7aa647de42c572c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 03:07:08', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('09cc212a5be04b58bbbc078df95e1714', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:15:08', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('09dc23d8450848c899fe78f40594b848', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:57:33', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('0a4f2e477d8c479eaacd5645fff00683', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:14:29', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('0ac5866a7e904b29bd45fd5b08ecf7ef', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0be5ec08df5b49baa9103c308266b1e1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 13:06:03', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('0ccc9e74232e47dca3f96afa73ac341c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:10:50', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('0d6f25159b7744009ff893eeb2d480e5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:22:33', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('0d8037f0617744ed8f094db85d2d07cd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:30:31', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('0dd79263d9a94a00bae8a0a86b42dbed', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 10:11:09', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('0e38a3c9b234458593d376b78ca22ec5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 12:35:12', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('0e43863d138b462bbd3b7f011d73fc62', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 12:08:15', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('0f2c09635f674e4cad1121003ab5d08c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:49:03', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('0f2fda8bc010482b88cfa7eb5f01e06e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:37:23', '更新供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('0f5b3a2e9d834afeb8bb42d2ea9cbb32', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:34:41', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('0f97d31044724b86ad6b075e147673dc', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:12', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1043fbb6e00e47a8bd2647a86080b04c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:12:22', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('106dbccd1b404eba924fc94cc3fce57a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 04:28:53', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('10cc5d71a4964060829e7bb01a26e8d3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:40:45', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('10f62f784ea94565aefe405dea5dcd12', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 14:35:29', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('111c7ad764b2413892abe8ce0eb175c9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:11:56', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('11ce4769969440ba89a18de86bcc79bb', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:16:46', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('11cf2374cec6472f845896bb91d2d069', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:05:28', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('11f640634cd24dda8220b3765fdfff29', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 12:54:57', '更新仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('12d808ed93b4476fb09d509e7856e634', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:01:58', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('12efd4b11f564e2f9b6c19e58981041f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:49:38', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('12fbc5e63d2e4848b8276bb110bd3787', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:46:19', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('13427621b23c455ea2c7fd15eda6ba3a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:17:26', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('13bf7c7e4e64465fb0aac302f767a03d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:33:20', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('13fbb02ff3834cebb91d1fc337cfe5eb', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:44:33', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('1460360fdc2844b2b260591fe35dd3ca', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:25:52', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('14e05f67d1fc45eca86bed18f66cbc6f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:41', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('14e2a8badbc34a50958e157e658220b2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 11:37:34', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('15196d96727d43a4836a4d9dcebc8085', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 11:06:16', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('156c91a161c24a83b317eaabb02653c4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 11:29:42', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('158629a35e3044959d7d000af1f48561', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 02:27:16', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('15cecf0e366f446c8591f174cfce3454', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:24:25', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('164431a247254a2eb1b743c11a187064', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 08:05:56', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('16469e8b85f3498897eea3933665d15f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:49:44', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('166c68857f764c09a63d828817519d24', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:15:33', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('166e9d8d166b4fb1af66bfbb73a3b1db', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:20:55', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('172777d2e5924ec9904e820ff2db7ffc', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 23:57:16', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('172f873e7f3744d5a94aec69aea2b387', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:43:49', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('1768052ccbdf4504b27968b55be2f6b6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:52:58', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('17948582264c4497b9bcd0c60633431a', '123', 'gameloft9', '127.0.0.1', 'upd', '2019-04-04 07:48:39', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('17c4ccf9f60f46eb80f87d612718c28f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:47:14', '更新角色');
INSERT INTO `sys_oper_log_test` VALUES ('17f4a2d8505840d593e38f30cd0e33d5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 03:29:35', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('180b7d433ccf4b7fa26e6979beca8ccd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:48:28', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('1828e66497a84118902cde361541a953', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('183518ad539948e59a68fbb9e8a35f9f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:02:16', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('187ad29963724c91888a9c134bd805da', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:10', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1883cd0b647e47a78b47f4bc24c2a4b9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-26 02:28:49', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('18965becd371471aa7fb2d66f202f46b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:52:46', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('18b365be169f437f98325d1b442e2d97', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 06:32:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('193e00ec9a7f4108bf0f3617505f3b57', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1974d4c4d3b048fc9c4d2871516ffd51', '123', 'gameloft9', '127.0.0.1', 'upd', '2019-03-18 02:03:49', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('199c18e895ca486e856f9a90fc1676e4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:27:53', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('19f251a3325140ad94c70bb8393ccece', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:33:58', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('19f5d0855e084780acc52c383decdd6b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:47:09', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('1a0e1d423fe344dbac7b237bc29566b4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 16:37:31', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('1a3cade2cebb4ee7b43985ba0091735a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:35:18', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('1a92328710994859b55005799171eca9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:15:25', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('1ac7e01d4be84b148575cfe7f4151aed', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:24:28', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('1af0239fa15947df840a5a8ed70bf4de', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:51:01', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('1bad351f11554e49b1b3a93797f434e6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:30:37', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('1bb1bd56f92244d4a05c4a2b514fe805', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 10:00:47', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('1bfa19b275754e359a4908217d73508e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:51', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1c81299d18a4428981144d5ea7368362', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:29:09', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('1d2302573a23494a9420c835c93bd5e6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 02:46:18', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1d4609635c514dbabf4c57ea838666a0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1db5d446980f4d04a4b430e1f324a2a4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:28', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1df8d1a5b590449d96ec2b48d1c17c0d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:46', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1e2473d367a449f59baf0e57e0a5cda6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:50:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1e93f1247d054412923469daca3534f3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:44:04', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('1eae3f085b294791be446976fcf11f50', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:55:06', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('1eb4c65ca0f649f2b3dfe360a23f3a11', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:08', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('1ebd687a49c144ada6b65265858e8210', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 22:49:42', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('200dee7837d142abb14050f869495e82', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2025cdd4647c4cf6a7750941277c4e3d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:06:30', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('202f217155ae4337b34da8ac0b31f4ca', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:41:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('204ede6434e84d638948c1587f9c88dc', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:44:38', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('20b74a9a2e614e67807045f9d29f4ab7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('20c105c1e25b4d69b432dee1b4bb0eae', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('20c5988c170d4a20bcca6ccf76284579', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:52:11', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('20eb3726c73d44a4afd93b5b9c3aa2c1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:28:18', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('21b11e20752048ed9234166ee54b2745', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:25:54', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('21bbbb78d95743119c770cbe3a9fcd2d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('21edc0a995b24e64856a7bca19c9264b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:11:23', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('222abe516c2548cd8925271e01dabb00', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:32:56', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('2336b36a9a874eec80c34e23939723f8', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 06:32:27', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('239f1696b4c04504b5df4414855af895', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:17:21', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('23da9a2be8534bcd8de400b1d2b09e7c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:09', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('243e5eedb9a64aaaa65e933d0d676c41', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:19:08', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('24ba3bfe2eb04e6d99526593c3923764', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:03:11', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('24debcc6f5334578aa83db6238a332b5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:22:05', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('251becfd64ae4b309c73cac674b5a1a2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2550608eee554be8a89a3e70e2ae86f8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:56:12', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('25b3608d15324b19a5bc7fe96bb5795c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:50:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('272cc18c2e9c4e8cabe8263bb554dd17', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 09:37:17', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('27546f4d50c345e78902812615baf8d4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:41:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('27819feceb4240329103b5d1ec57bba7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:07:36', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('2785f809941f48989f0f2744670b8f65', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:21:45', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('278956fe21ec48e787155ac0b4831cdb', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:26:53', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2793ffc0515c47a6abca33f01deb9bde', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:13:55', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('27951cb607cd448eb4a49c24a0d83f16', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:16:48', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('279c1e28a7b549ac96a2a6ad0188870e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:04:42', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('27dddd90010d4a848062c8c105649f56', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:31:10', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('27e7b603d2c44bbca7a0a81ea5698ad6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 21:44:47', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('27f0ea2477be4ecaa21ec18c9ad8c893', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:17:17', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('285fedebe1e04ad98076e86c32feadd1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-26 02:37:31', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2863a0844799480992921e7cd8cb750f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:33:59', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('287b494fe23747c8a6cf4d793848e2d4', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:25:40', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('288795b0c12c4ae0a50cf210b8e9bd91', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:47:28', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('28d0b2173bdf4414b1a3ef30f6b8b285', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:36:19', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('28d5e37a817649aea940bbc1fde80dee', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:33:02', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('29498982046f40eb9c533f827c888336', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:44', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2998328cd4bc47a98477daf54401bf6d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 16:40:05', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('29a41a5ae2aa4d21984f3ec9563fd775', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('29e8e0ccf2014f0c85b0330ef709ecae', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 03:16:53', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('2ab6a5cb8bf74ac3b5d20af533431dcb', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:26:58', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('2b7fe69be561477994530a4da4f4c1de', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:58:11', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('2c6dfeea43544238b7270a33d852f489', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:28:49', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('2ca1a1ccf681455e86e44802daec1973', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:25:25', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('2cf5335c044e4e2881046df5cfbe8413', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-23 06:49:23', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2d17eccf39b54276b54c9902897bdeab', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:09:15', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('2d3f65a4618e44efa4ada083a229ea0a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 16:37:20', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('2d95414e53844f46a51fb64ff993349f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:03', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2dc3ba8a15424c08b362e157a38e1290', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 10:08:45', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('2e30878369254e01ae8402e7bb115715', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 03:14:07', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('2e74eadf04fb4eb3875cf2496f009315', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 09:34:43', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2e8d4da21e5440bba46bccdf7341dca4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:45:34', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2eeb85da6cbb4be1bf68c498df479d9d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-28 02:31:50', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('2f30e34df14a46048fd244494ae2806a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:38:07', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('3008ea854f724992a6dc3027f49d01f6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:59:07', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('307cb15a273d445189dc538063c78ae6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:03', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('309dbc0e4dbf47d099a475029542bcf3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:33:28', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('30b5ba1a62ec4f27b2c109b5e9c76baf', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:51:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('313b09a8115e402b935d05879cfe2ae2', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:05:48', '新增生产入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3170db54765f4e7495c7f8d2dfe8d47d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:23:11', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3198dd89467c45a8a942a4f4f846172c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:18:21', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('31b3b9484b6c42fc8959f4c81f4ed0d2', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 02:46:06', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('323e81223ee049f99f0a725996a63c82', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 01:42:53', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('328deff67c9d46e8b2b9268fe7d7bffa', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 09:36:25', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('32bb22e77bbe4ed2a27532300243a3a6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:35:53', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('330402ae9ce04b0e89b026f245cc8ae0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 08:30:39', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3356bce0324f468d936fdd970904976f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:22:45', '审核通过入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3433d6badc1d4b4a87906d8014f62245', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 02:47:39', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3492034ae0e940fab0ff544257720f80', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('34ddb0018e9d4c038e3c44a261e2d14e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:09:26', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('351cdf76e32948cb8fba856c3b686ed2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:49:10', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('357e4ab89d0c49be94e4e2b312c4c0a8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:57:12', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('35bc5dd2b87f45ec9d4796b4e45b4040', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:49:09', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('36031a88a5314a779fb3a0b0c9366c19', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:15:09', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('36419a7f257140f0ba87de2d4f0c6a58', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:21:17', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('365b7c7ae2104ca3ab518456c7efb17f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('36707f081e02447192fc4a8ec013566e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:55:07', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('36c62ba59ff34aae851f59dd99bb5cf3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 08:28:46', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3845450a1f5d42ada3aacb8b08207765', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:17:10', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('38606a8d10f04f13a1f15a1ddd710965', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3876e30b72a64ff59dd2432acda43380', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:36', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('387f242d89814bf08c9df62e597554a3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:21:35', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('389478b23d194975ad4f12062c02c57f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:48:16', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('3897eec70ff94a4bb4bce3a2f14e993a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:34:49', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('38cb7cde84434d048448e5b11084b35d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 10:09:27', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('38cd8b00ed0c41fca9e540cb2d7dce5c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:14:39', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('38e84b095bdc4ea1a650731490ef4aa4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 09:36:47', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('397c2ad8de5e47818d63cdc9b9ad0ee7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:06:36', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('39930a2ea3bc4b3d9dbc61e9c686bd84', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:25:03', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('39adef58d4d5454eb308251a0c3e880c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:46:04', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('39e26eb1d2244938834bd09ca1cff86d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:50:47', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('3a0957df801345dcb5187f01f827cb10', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:40:57', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3a38ddb6bdfc452cb5c67e1dcc22f5e9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:42:24', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('3ad3cbf4282d4d44815087712b769f65', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 07:24:43', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('3ae9e1ce57644a0794bc4152c3a75b74', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:07:23', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3aef0fac1fed4919972660f4743806f4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:13:00', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3b0a06174d8b48f98bf1d321c8d77d45', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 01:45:22', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3b2f907049324d92aedf7171e26f899d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:09:13', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('3c18fa5a4e6342a2bc5476b0790c4e07', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:43:55', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3c6c5093489d4bd6a2aa90bab1bdf624', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 06:31:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3d2f1a4ffe524638a0846d119d70be9f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:01:56', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3d408c1cac7f4195ade716ed7fb38b2f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:51:29', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3dd358d67ad04ed29ef2e601d2ee14ac', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:22:11', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3e22141c6ffa4631a184940b61c7d80e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:31:09', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('3e7675b694ff48b6a1667035b2f7dc85', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 12:11:21', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('3ec0edd176d64d238fda62d49ee884c7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3ed344b49dc7409b87e3f381b93363b8', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:42:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3ee54124b2fc46a9862fe0738b895640', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:57:52', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3ef0d7ff8b344afb8459b3f35764a9ab', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 12:07:46', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3f17cadbdf6047599285707de9b708df', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:34:26', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('3f2704791e734a3db27a69cd954ca2ed', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('3f705b81b5864db7b48745e05a456664', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 15:00:32', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('3fc1c31b6f2f47019ba43c807938a7d2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:08:27', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('3febc6efc1e943c58163ec3bb3462ec4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-29 15:21:35', '批量删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('4104012521744df5924713b25fadbbc6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:30:35', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('412c889d37214304b595da712aa87f26', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:13:15', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('418c2836872046329b2a849972b8302a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-28 05:44:54', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('42181c8d397d4a5382ac0961f9595628', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 11:21:16', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('422dd4d4ce604f66be7277dbfa566910', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:15:41', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('42a85572744c48809334ab1862faafea', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:47:07', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('42c6ab01ea4d4115b92d66b387a5b819', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:39:14', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('42eca08eca3d413e83592d929f43754f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:29:13', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('433e32048fbd47f6a5f5bdc64fb02a35', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:13:10', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('434cf2651edf45da8c380e869bb52109', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:48:57', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('434d8d4b1cc74d489def30d82574a021', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:00:30', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('435ee91da1b54e69bb0ef34fd90c4c2b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-30 05:28:35', '批量删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('441a778fee644a878b7ee9339046de58', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:43:06', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('443080a067194c028d08ef3882ee449b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 15:01:24', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('445c204bf9f94fdd9e86db85913e8595', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:00:54', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('44a35e341d70422db4d6ddaa54f6b8ee', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 13:06:19', '更新仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('44fba7caf73e44c9addec0d8d724a434', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 15:29:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('451f0a6c6ce948178882ff115e537483', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:02:04', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('4530a0cbd4d24aae9d89e172199a9911', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:24:48', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('45cab40649964c4dbbbd08627be8ad76', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:44:22', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('45d4d335014643b4b01592f873486fe7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:13:35', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('45fbe21f29ab4521b2d4a1a5ce931462', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('460d57d2354344cda70d677f3e86cd6b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:25:37', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('465e0b7a05744bf9af094823cb03dfd4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:17', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('474be147119c4b2bb0f72da86feeb227', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:05:24', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('47895fcd4437449ba7cd640ba557c6d6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:42', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('485eb7a860a74cf2ad7a98c72e602a02', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:36:06', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('4889e74853424b5cb95723bbda2c90d6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:12:11', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('48947e135edb40c58b2f749b0a458935', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:52:03', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('48b00226146b45b295258a0b940ceb7b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:58:26', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('491c0e092756441a8dc0c616f160427f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:24:55', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('4935723365914f3d970f440f211869c7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-28 02:31:30', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4a0f4fe74b9c43d4b52828f1f54be40e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4a3be0c92c82468db56d0bea9b034d05', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:51:26', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4a5c7fcf7d494d95b732978a67641873', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:06:58', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('4aed001bcd7b4bbb9fe0f17a27edd02a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:52:35', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('4b02a8cc34e74b019da3808f577329e1', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4b0c69ae54864003a89b2e746061e6fe', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:33:13', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('4b61a9c001ab47c2b231d2c22556fe69', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:59:18', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('4be54dcfd16840eb8efe62bd684a6b8d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:57:08', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4be9e957160946de8fdd6781049f76a5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:40:00', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('4cb3c7325eae4e3f83b4b0220e5ba0dc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:54:18', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('4d25e06ec4814a0a8cd9b3be07676fb3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:39:54', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4d9a2044a69d446fb04045e27d57108e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 01:43:19', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4dd2e01ddf0644ef8adcb42a78204846', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:47:14', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('4e36b26caf184406adb48aad1864cc25', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:09', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('4e6aa12b71e041e09184b70db64faa61', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:19:05', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('4edcc65fc77641e4bc2192e18ac66a7e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:10:08', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('4f1f9b3d72cc4a588021424bb153923e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 16:37:41', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('4f58d421b2184238a535d673b25598ba', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:22:49', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('4f62d9a08c1f4340b319b22917475dec', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 01:58:33', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('4ffd47ca4396453c84e51158360f1bf7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:13:13', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('50137f5c7e9d40e3a115a8962a38a912', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:23:15', '审核通过入库单');
INSERT INTO `sys_oper_log_test` VALUES ('502895c1219d4dcaba8578f99998c57b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:39:20', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('50c256e74a424112855ff240bd48bacf', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:09:26', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('50cb85f2c6d244fe8bda15517fd26ccd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 03:28:35', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('51296a024c064f16937997f4538afeeb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:12:52', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('5140461ba33442d1af14e2850d168d3c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:35:56', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('519659eb13ea4ff8b86e2515e927c26a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:26:19', '新增生产入库单');
INSERT INTO `sys_oper_log_test` VALUES ('51ac6b65f43e409799e5be6831ac99b3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:54:01', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5276e656c01e4de486dbf94271532b79', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:30:37', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('52be46bc54b14a04b2cb91d2f3be2308', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:12:05', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('52e9f54fad42478eb5271efda5c3aff1', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-22 08:21:45', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('533270d6562d4e7f94c5ca35d919af60', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:05:50', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('536d79ba28984ca3b2d30e159c2c6e18', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 12:38:27', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5398cef7aad8485f9c931a90fded5151', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 22:26:29', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('53b8fb91ac29429e8e780f68c05136f8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:58:37', '驳回盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('53bfd8590be04d209fa68cd8956a23f2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 02:42:33', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('53ce8cc111d34878b029ce6708f8bb2d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:32:58', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('53d25017279b47afbc527be4ba6beede', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:49:28', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('53d887136c074558b3b0e4936a23eb38', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:13:26', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('53d973cecbb749d9bc51fcf8948facee', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:23:36', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('544ba97cc03b4d98bf9175e1349aa4ec', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('54f291eb804e4f4fa37e38a1dd833a54', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:58:56', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5521acec73d54c6a86b4e45916e42093', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:01:59', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('553c425296f3497babeefb9bed2cef93', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:32:49', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('55560dd7926a481599ca85928aa690b8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:53:44', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5561a8a8a0b94db6b6d587fea73f7090', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:35:14', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('55673f2ddaf5425d9ebe9cde969214c3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:23:08', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('55951368e0ea478f9ee4c217a2ab383d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:32:41', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('5601ac59c52d4ddeafb73b4966bc1b9c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 02:18:54', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('562d2f972f0b4ea09ca920ad17b3167c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:50:48', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('564e17eb691343f0a5b58b67285aee0e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 15:34:55', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('56e1544f2d434c0ea05f2a50e4632b43', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:38:03', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('56fc8873d1734cb8a04a904d96e3fdd9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:33:30', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('575280b3982d4790ba22639f124cd486', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:29:21', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('5775eb7750de4a12b00e9bd75eea71b3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:53:31', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('57ae6460a42c46d99b34786146b46028', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-28 02:30:49', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('57c0f6cfbb3a497c85714abe44d6fdf7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:28:14', '新增采购入库单');
INSERT INTO `sys_oper_log_test` VALUES ('58553aae156a4d1b9619c11c2905149e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:23:40', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('5889ab3c794544c38211d469c0eb2f61', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:27', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('58c4d58dd0264868addd8b3420b5b37c', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:21:55', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('58d19ca0837a42629e6d4aec33c3dc6e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:25:30', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('59c88794c0e04961be4b87327a6d5e44', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 04:48:29', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5a26b0c180964c83a5244dd851ff2648', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:13:12', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('5a2bf032bb9044b98a55b8db47301356', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:30:25', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('5ad7c9566829456d99959d5906256fed', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:23:10', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('5add469a10c9440797df51bd354981f8', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5b5e2fa7396948a68c7107eba3cb68ce', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:19:16', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('5bbd3a56965e40eca2ca712709c7eb6e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:22', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5bf2ceaa1dcd430aa7f16403340cf050', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:18', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5c1d3f2bab184ca78b1dcc89597195ed', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:14:10', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5c5801f8596d4b42b48cc1ea61cc0e2c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 01:58:42', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('5c62acfcd75048a181a808686b658d3c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:42', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5da3ea81b4234eea97aa26251c69c9e3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:56:37', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5dba9428fad448adab8891d0e947222a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:23:02', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('5e33079090df4257a06a87941b6499b6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:27', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('5ed9993bb56543fab47f7e638ded0c39', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 15:21:46', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('5f040b914f094f74bbd82ac933e9ff1c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:28:29', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('5f18c41ec57a4839bd303e5a05f8bae1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:12:56', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('5f4cbe642a674b57b806df739581dc4c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 09:30:23', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('5fa6a0b4022244ad82b7b6eec92c7d86', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 07:08:57', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('5fd077241e054e8a8ab21d6e7b0c3ee7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:24:04', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('5ffcd80b2db6462aaa9beb5b36950e71', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:51:57', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6046c0a5616944f2bb7b28ef1943a181', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 03:27:28', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('60a9f7926c894a9e90de1051f7f35f3c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:25:51', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('60ba87cfd38a43378fa747f41685795f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:55:33', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('61cbee2d0d094e74bdc324189e504d80', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:22:21', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('61ddb113b0bb45d5871d704f5d315090', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6251dd95dc6c44e09f24f0104292b142', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:39:20', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('62e5d8fd728e4d18a69c2e9e4466bb79', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:03', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('63cdec04a4eb44f0b834adb1f8e4c78a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 12:03:04', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('642f02d32cb24b50bd3d65d6ac65d747', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:52:34', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('643d07ce46c74bddbbce973ca8043a6a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:47:00', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('645699fdf7bc47ea99a5ca1bb1a5cfb9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:46:51', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('64f95bd5e426463988ebecaa0a8858df', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:34:18', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('6545604c5d7d4338b11b182469b5f026', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:22:02', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('65492dc0bcbd42be9811ea88a718b9f5', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 02:54:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('656d752817c64885bb1dc33f505258fa', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:17:49', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('65d97297e5aa45919bcc69f846cc4062', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:24:15', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('65e853b6cfd841198d1adef994660faa', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:40:23', '更新供应商');
INSERT INTO `sys_oper_log_test` VALUES ('66207186d92b422d9bba5280c1602467', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:55:07', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('667a553501834795806ffc45a5e343f7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:46', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('668584b5e7134d1b80dc23e19bfe1080', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:23:43', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('66c5a1c8a1ed46a7b786e77f900369f0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:50:08', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('66f97c5f4fac4681a47aaea701a99807', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-04-04 12:42:06', '删除原料');
INSERT INTO `sys_oper_log_test` VALUES ('68242858fb114e5d956a68efc293bab4', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:26:40', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('68a30f2589a142b6bc4b5c7ac4d7c534', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:35', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6b1bcfa1f4ce45538f5cf09743dc3678', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:39:04', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('6b277fb79a5b4f3c95625db6ee76a5e5', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:32:37', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6b9f46c87ea742639f2eed0b79aeb0a2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 05:56:32', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6ba8d59331d546eb84b869f0cd112b23', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 09:37:38', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6bd0cab40a724e668b5d7474a87105f8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:06:01', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('6bfb6bb1af9d4e81a2f21b05c0e321b7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-23 06:50:33', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6c2c691f8d904fc0800300109a1e79bd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:35:13', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('6cc790def199499098cf2ae07e95064f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:20', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6cc7be9f438d4b618462bdb95e0f971f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6d36721c8cfc4f1e815a83c8bc04408d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:44:27', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('6d547b8a50b148a59da07b610634bf30', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 11:21:17', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('6d7e71fdbb284fbc80fb5be1895547ca', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:24:20', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('6d82414bb5724ff29e2ddf252df5a8cf', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:22:36', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('6db8e7c1b29745679564dc628b684dc1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:31:48', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('6e4643aa1e7d4f9da539101ba237a295', 'beace5044a9844b182c5707156b5ca27', 'lennon12', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 03:07:39', '更新个人密码');
INSERT INTO `sys_oper_log_test` VALUES ('6e7d6cdaab9f4ec88e37d0505c35ba26', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 14:22:30', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('6ea7850e052b4fffb734399dac3cf951', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:15:59', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('6f077ee073314ba5ac275ed45e188b4e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:31:18', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('6f745ee03ff94d6bb6d1809ec2d4029c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:42:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('6fb705d12d794bae95bb6cebacbe6617', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:09:58', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('6fe97b3c7e9e40b6960b59d7815f4c25', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:16:39', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('7089b396ca4842109dff0fb30b85a8aa', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:59:47', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('70c5f80fd56c49748ffa4857c2831efd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('70f2877c99a34194ba6abd38db6f1309', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:38:18', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('716531fc1b1a4f03ae4a40dc5d120f15', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:56:03', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('717edd219b594dd0986d419cc3415e5f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:13:28', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('71d83e732c684775bb760d82aacc0851', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:35:44', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('72177c2cf7784c1894924b12a1b2f08f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:15:46', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('72d170b6a40745bd8e6e8782f243cb28', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 04:54:28', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('72e1f5942dc34f019d5d5ddf3baa0468', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:22', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('72edf0e79fef4bc2989d581084883897', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 05:56:38', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('735073088bbc414d9aa1842e26948fdd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('73536a6b1a6941fd948468618c36be01', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:03:16', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('73b79220799f462e8b9003fa8d143dbd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 02:50:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7419048b499c462bbb3f4cc4c77f7f1e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:02:40', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('748f772e65bd4ab7baf770001347b951', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:40', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7490b8ced4c94e44bafaef51625ddb03', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:55:41', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('7497a5e638d14fe6a45b72fd032ac850', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:45:43', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('74edd35ab53c4e95a240193c46f3faa1', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:48:36', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('753054c4d9b4428c83773104f04873e5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:19:03', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('7536484f39044022acd4f224c183868b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:43:32', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('7582916889a54446b0fed32246403666', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 03:28:18', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('768da25daaa84e33905fdcef6fb33aa9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 01:00:46', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('77436f4cfa2e4ee08d64ff7f0101ae84', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:32:04', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('7751ba6dbc884730808d1de48e6b16f2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:33:56', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('78403b587b9a4a2ab1a38aee2926117c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:10:59', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('7862a349f1d94fa99084319d86e456cc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:54:12', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('78e49ec5cb9f4c70bae59b167466e79b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 15:31:50', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('790c7ed245e5416f8996d88caf523cde', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 10:10:51', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('793da726516442b7a5e773d94a11d14b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:57', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7a0c9c5004d54550bff2e92e7deebbd6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 10:11:09', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7a70e39342e34ccb9e709308893af8a7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:31:01', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('7a8e26152c7b41e89ba57cbfac7628e3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:56:08', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('7a93fe5cdecb464ca741ba6bd75f18f0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:30:24', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7aace72045994d8ab59c4a471ea6a7d2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:24:15', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7ad784bab0ab4ea3a1936945b144bb47', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 02:47:01', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7bcaa5f59b554b229966b7143e1711f6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:44:39', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('7bd9f6b8ba7b46df984d6be648955a58', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:16:43', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7c350f48b1a8438e94376b6e67b4bbcf', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:39:56', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7c5e8229023245a1aa9dfded5ed7ba4b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:49:38', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7c749f9aa46943d09e387022e3b488f8', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 03:06:32', '初始化密码');
INSERT INTO `sys_oper_log_test` VALUES ('7d3558069aaa44f9a5c67a220df3ecd4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:58:12', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('7d51ca64aa454ce1ae6a2d9315ec1114', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 14:21:24', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('7d880c80e807442895a94a057cfe6d9a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 11:30:49', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('7da36880faed47a9a8ef8a486befbe25', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:47:09', '更新角色');
INSERT INTO `sys_oper_log_test` VALUES ('7e7aeeac144e4a5394f836ee9a0c6284', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:23:59', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('7e7e9bab41684de5ab22a01b007a7104', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 01:44:38', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7eeaf80a36564962933455a292d8eac7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:19:30', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('7eec075405dd470189305787ccfc0a89', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:28:09', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('7f07914901ac4431bcdd8f749973a4f0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:03:44', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('7f74ffce7d484b7aa8d05d3ed56aabcb', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('7f76f3a5f3cd40e3bbdac857a700a736', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'del', '2019-03-18 02:26:15', '删除用户');
INSERT INTO `sys_oper_log_test` VALUES ('804ca6e71c094943a772590d65a552c4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:22:40', '审核通过入库单');
INSERT INTO `sys_oper_log_test` VALUES ('807092380ddf45df8b6cc2a923babfea', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:11:26', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('80d4e871af5640539ad2de5eaed9d04f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:08', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('81ed1e3d270741208cbce3f0a748c6ac', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:16:47', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('81fd393835554348b18bfe7c8650e86f', '123', 'gameloft9', '127.0.0.1', 'upd', '2019-04-04 08:25:52', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('82ee93515f3f4ad6bf86deede314f9d8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:25:42', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('83981cfba11d4ee08e4273c39ecaf5ea', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:14:34', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('84924c6adf62486db06c2bdbe4deae1d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:35:07', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('84f7b8ad007b47329d2e41f5488642f2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:14', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8537f23edb3e403e84906e58de54bd51', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 10:16:47', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('8543bf3bebf946df86b48e29f706563a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 11:32:20', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('857909c0501446229697eefcb887512d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:54:26', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('85ac9fa78d8245cfb82ba7eec8de9793', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:58', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('862aaaaac8394a768cfbefa9510fc01e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:25:45', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('8648f964ecbb4eef8ff458fbec3551b3', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:27:17', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('868694ff24ce41f3906d36a8f9f61dbc', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:48:57', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('868c2a9465f84c6f8e9e16a37a9ea50d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:41:21', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('86b9effe597d4478acd15a769304ef4c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:55:31', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('874a35d5e10a4b2d9283d8fdaaf57f2f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:17:24', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('8769ecf454634ab68931b95b4857aa77', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 02:50:29', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8772d0f903c44112a1b50d45f080cf23', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('878117cde0dc4b7187ee867e1ca5ebbf', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:35:18', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('87aba5c97e6a48c89f87c636b1628149', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:12', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('87cbf6d7fbe6468fbb81ccad16cd9b0b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:42', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8826f0470d0c4627a8e437ef1cc18a74', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:40:30', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('8891abadab4449d697bb07add6622263', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:03:29', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('88d12f79633047f9885d44e79266ce15', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:19', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('894ac23a88e64889b8bc674cf5ad0f3f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 15:00:37', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('895d7495424e4ab9a2ab37824a057e84', '123', 'gameloft9', '127.0.0.1', 'add', '2019-03-18 02:03:32', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('89e6a9c079b94cc3a79dcd3d6591ee50', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:48:31', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8a0678a0dd524be9b5cef96e48fef5dd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:23:03', '审核通过入库单');
INSERT INTO `sys_oper_log_test` VALUES ('8a620340565742ef9b66a8495f32300e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:47:16', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('8a6be42c5aab48d4839433f917e716a0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:09', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8ab91613074a44ba954255003b4d2620', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:08:54', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('8b5b3fb34d87494999ebfd16496220dd', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:25:45', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8bd265089a224600a70614c61345b0f6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:28:38', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('8bd3b132f5f141529d03f2ab50910da0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:48:36', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('8be091078efc43b8a2d7c05d2b6890bb', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 07:22:09', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8c1788624a564b3bb4e81682d55a84fb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:24:10', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('8c9f293f4b5845eebf848a6daa32723d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:44:13', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('8ca891fe00074b3491080ba49b882929', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:11:09', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('8d2b06ca204b4eba9e0e363a1bf552f9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:51:37', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8d596034cdff45fb96cae5ecc10a32a4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:06:10', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('8d9e62d445bd40e6b7beb916b889b5ee', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 12:05:54', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('8e1108059110441fbc8572254c38ed85', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 11:33:52', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('8ef0ef9b6e3c49eeb332f0116a9d0d44', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:31:45', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('8f26434f01a648da82a7172cd5792afe', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 10:16:31', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('8f50bbe08ef34b00ab493d43ebc39725', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:29:25', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('8ffe662dd78c4b4a98e1df4bb8ce8471', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:11:59', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('8fff276296e94d51bcaa1c4f1ba4218e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:20:47', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('9073db54d0a544eab95a3beb6a6c750a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:15:03', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('90939256564547a9bcbb3fe7ec7fd674', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:16', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('90c93fdaf3504c88976ac60a1cff3962', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:48:03', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9113e8d4d8e144aab173e7f191e44f45', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:13:31', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('914399db70a44262be158f31b29b0d82', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 10:06:28', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('9179d1394f5845889d9a923ce94f459f', '123', 'gameloft9', '127.0.0.1', 'del', '2019-03-21 15:46:27', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('919a0e0213b649df9ea6353750a1582a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:36:10', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('91b92f1a2f884d36a22301c7d463f100', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:17:23', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('92880fff935a41aeaa4b6cf6559170c3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('928f93153d3e465da53f4a698548d59c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:03:46', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('9339afba0b874d3b9e45cc9f348c503b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:25', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9365e07a3e84433dad51e3a1512a2f44', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:14:42', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('93ad06949d564611ab73ce83157b9519', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('93ba3679ca034837a502f32372b5d61c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('93eec9ca3c474cbcb9b6417a21058432', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 03:17:03', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('9483168d0fd942069aa70a30bfbbd155', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:48:34', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('94a31e7b4ec048ba90650077aca6ce4a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:29:07', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('94a765fbd0214997be51bcdf0e7b2703', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:49:24', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('9540f3471a9c45d1b4423fd874324d22', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-23 06:50:16', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9569b5539d1549f7b6aaebb615be0ebc', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:37', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('957a1fc39c3b4409b0e4a16dbcc569a5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 11:22:49', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('9583fc70a40240ac9fdc5652e2a9fbc3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:45', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9635de05730f43e0b7094c5aeb625e2d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:57:29', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('965ab570d6214141944cb0b523d57fd1', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'del', '2019-03-18 02:26:00', '删除用户');
INSERT INTO `sys_oper_log_test` VALUES ('966e59a53c824a02a05c901e997b9a6a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:07', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('96fc240933134b4596afee58de9a8377', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:26:43', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('971c61892f06446295aba65bfc9e13b1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:24:50', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('9763eceee7bc4c0caccf9bf8d24cea3e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:52:27', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('9788bd6733f0495aa023917439aebeeb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 03:16:11', '驳回盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('97c54f56b45c48cfbbaa0e97e46039dd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:08:09', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('97e87fa18c154c6992837c24bc48ae95', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 10:01:50', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('97fb795042324b99aa0ad85f12a8b386', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-23 07:09:26', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('980b45035b314a04a96d7cda97a706b9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:04:46', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('981cf673a4bc4a6c8b2e4b898ba060c3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 15:32:54', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('983fe5307de64c7589ccfe62a2a8b684', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 23:58:59', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9877d28eae5447a486ee718bf49138df', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:46:30', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('98f65d9a79034aca94fb202793af297b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:09:27', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('9907a7ce7ff448228852fd67a7bcd6c4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('992bdb9773b546a79014464bebdb4349', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 03:18:46', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('995c9c55948c484a98e24b51b7d79ab5', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 14:44:50', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9979c1595a6043879dd4123737cd2b1f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:40:52', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('9983a29775ad408bbc69012af898ebd3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 09:30:07', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('9990dec507024cb5a5b0a1083cfc3a05', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:02:05', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('99ef595b26d3426fa7fe206bc93f142f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:18:47', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('9a0eddd347d44fde982bdebadaaf9d42', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:05:15', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9aa44bbb72014cae8d51795f2270bbe7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:47:03', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('9ad21b4de16d427292c3fabb10b0c277', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 04:30:50', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9b014c63cc614a80aa2ae16df78a0d45', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:26:40', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('9b4a3dd972b84a9883be8eafabad80d3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:28', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9b4b2d5c04444a0da089107e4e41b83a', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:38:29', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9bcb71462ff04b4d919f7d08d10c8bb1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:20:11', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('9bcd57070d4c4289a4cc5de177621930', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:23:50', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('9c1daf3d61954812b008dfaa4410268a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 12:04:07', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9c7b960877764350ba233ee7e5d7f24f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:46:41', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('9cb5ac1548f643829784bc204f778dde', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:18:20', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('9cd77a5d00e14f11968dda8cdf64779c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:09:05', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('9ce965964dfa464b8f4e3d3f61063eea', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9d09563d3646414594cf3075b0bcf49a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:48', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9d199d0f23974a2cb87294fcfde054c3', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:07:09', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('9d480eeaba7f45a8b9263d1a73a32097', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 23:54:59', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9d8461f4b2d945009567562823c5c81b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 13:26:59', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('9db101e80f824be593b166ab231c82e0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 11:01:28', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('9e1e2f9f10db4e9ebf759357118c79ee', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 07:21:43', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9e435fb9321d4ea2b01c0a476279ee42', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:26:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9e479b63e0d54ae7b7fac73c04c93013', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:07:55', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('9f06f2c4fcbb4db88b4e3226f2fbf397', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:29:36', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('9f39295f5bdb4bcaa8de8ac0b1ced257', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:53:22', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('9f7511b8975743c1acd45551029fab3c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:16', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a06e2e2652ae4263bc0da14753503929', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:29:15', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a0709c38b1c1411aaa6d6639deeed887', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:33:04', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a09f242b0cf74107a11f9d77f0e7bcfe', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:48:21', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('a119700b51eb45d4b6a807518af6deb8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:49:51', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('a1588070ebaf4891a2c6b6c37b5ee55e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-28 02:33:10', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a1d7e857021049bfa8e712834e5e4e36', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:13:09', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('a246e37799f14ea4ab8286398c397943', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:22:32', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('a25fe92a2b0e4786ad4f95d35c549470', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:38', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a260cb4a2454456c94866649260ba233', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a268eea9f72e400aad5035e2b983216f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:58:30', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('a2e35cf40fc840c5aedb758df20358f2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-04-04 12:40:41', '删除供应商');
INSERT INTO `sys_oper_log_test` VALUES ('a36d7632d9154ab6ae7be100f5c7c630', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 23:59:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a38e351db2134f26991aa53aace67576', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:32:48', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('a3c8f56d5e914731ab8fe279c4ba49b1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 12:06:06', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a449172053204222a6d922172806d590', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a4936d3deba8401e92c9938071f835a1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 14:20:31', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('a4c3280e35c74ae2b0806d1514d89999', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:22:56', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('a59b0d5775044602a2257055ecdedfdc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:19:26', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('a5a147104ca74c7d9fcf3e3559e53ade', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-30 05:21:53', '批量删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('a5f886a7142a4c048271772a6853c599', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 03:17:09', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a600c367a20248f7b03872b7369c40dc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:43:41', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a6131f955d3e4ca5821d4f4d1a818e05', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 04:40:35', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('a62adc6ae2b747a5aa79c943bf1ddff6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:04:23', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('a6320781ad4f44228af78f443af38f76', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:55:51', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a6a10432f5a64c6b92b28effbbd19b50', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 08:57:07', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('a6ef18c475ad49088ca11f4196cc195d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:49:06', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('a738e22a9121411cbe43b1dab1938e6a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:25:11', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a793e7096ab34ce3b7fb193e445ce485', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:09:15', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('a794a07506fa482f8b8627b0d18dbc78', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a8c5c85e055b419888b7de616e20ae56', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:19:13', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('a8e7019e5f0443e5a35c1ccbb6a0c7f5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:12:47', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('a8f2f20441e34b7f885203fcba1e230c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:58', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('a93e11cae3b14d0db816f2f88ec07117', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:55:35', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('a99317bde54e41ae89c14a4a3edadfd4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:14:14', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('a9da3e034cee4d33a5f4d53c71d41804', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 03:06:51', '更新个人密码');
INSERT INTO `sys_oper_log_test` VALUES ('aa3b48a1fa964613a37f4dd1b0ad1f09', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:15:11', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('aa54965e5d1e4a6eb8bbe1391cc06d1c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:21:02', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('aa5e91f8e9364ef78160a1361661119b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:41:38', '添加原料种类');
INSERT INTO `sys_oper_log_test` VALUES ('aa620f21e6844a1dbdd4586746333d0a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:28:02', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('aaa28691ec854a32b6332c58654b2b25', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:35:15', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('aaa28ab24f9b44188ab9bb5f4497e6a6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:21:09', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('aacc28800ec24aada45852954e6d47fe', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:32:40', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('abf974f982aa4f41aefb29a7f796ba35', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:21:42', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('ac156aa84a87431fab7ead658917745f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('acc155d08dd248b5959c89d6c97e6b90', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:07', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ace63ae8b0494cf19d22c7897e8e735b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 02:31:17', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('acf7c290394346fb8ab7682ee1a7fe3d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 12:03:45', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ad0ecc68ee4b4180847d1e77a6bf6908', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:19:10', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('ad8d4b246505478b8e6adeb6cf6cb845', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:43:22', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('af20966ec4504a70b7d4c536e86710ac', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:16:50', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('af292e89f18c40e58b94a487240faf24', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:30:11', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('afb4df8e5caa4407854c9e0fc515b302', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:27:10', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('afcc82471c1c4cdead8c7f005564f6fe', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:55:44', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('affb2f436ca04b89b00a8641eb7c073f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 09:38:40', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b108ee5ebf314b6d9f55916402283661', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 03:07:49', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('b13c9e305d084f98899bbb66c9ae35fc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:47:04', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('b1b8f00c718b48f6baa6514cfbfa089a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-30 05:21:24', '删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('b1f515c589ed4528a3e1741622811c07', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:09:24', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('b1f900a5353d452d8c177081963c1ec4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:56:57', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('b247a848cf7747fea0a419d17418edbd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 14:20:53', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('b26b18815e3e4a16b607f07e5047b320', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-29 15:15:57', '删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('b2be131bd2944102a7dbd80a52568725', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:33:59', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('b305e2f9527741888e4f7fbffdd52075', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:34:36', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('b30f608f33dc410cb9b1984a34611fdc', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:15:29', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('b32284b2403e4c5aa79e9977e6fc5469', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:03', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b32b65365c33475ca1b6279063adef97', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:28:28', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('b337be0a6f3f4699a7c2d859444502f2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:13', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b3d5d184e89a40458b9ec360b30331e3', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:56:00', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('b4404dd33b2941728b45baaa5eea4d94', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:37:53', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('b44924d1dac648e882b091bdb86744a6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:29:03', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('b4594a149f3f4c19a8011a0b4d45cdbb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:10:06', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('b4ccb8fea4824f79b06b21cf51596a35', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 09:29:02', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b50d695a16d94033a3a443250843d156', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 04:32:20', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b5141f76aae94376a685ca36b3a8d22a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b538f7f469c947fd9bfa03427455a02b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:58:29', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('b54d228e123642e79b05322a11f16046', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-28 02:33:23', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b5c986f01400487eb88068ea9b28a7f5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:22:46', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('b5f8b3afa147438e9ff8d66e64e02c6d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:31', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b60957a7a949480cbc73310c8d76fad9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:44:26', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('b60a06d47e4443b1a48522c0ebfe626f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:47:57', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b62e0f2bdf084592a42c267e8782deb1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 07:21:24', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('b69dc094cb7e402f8b82e46429b1793b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:44', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('b7228ebc85c641b4937baf4c13abd51b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 02:18:56', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('b742f35856a4485a97dbf7b8b1482ad5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 13:07:11', '更新仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('b7720cd4890342448f2128f9e6a2ad77', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:17:41', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('b912fa4c877242bd97e1b2fbd5e60bf0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:55:47', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('b97fc4002752423db6a21f98e1f2d5cc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:39:24', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('b9df1c787d0e46289f4585f0436af22e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 11:36:26', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ba14901f698b4a19a75fa40e01fa3d4e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ba66147a514942f5a6cf41f352530645', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:32:04', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('ba68a57630704c50b0a7f1ae166feea4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:38:19', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('baf0e06868ba47a582a430e7b3292d4c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:50:59', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('bafba978baff451fb844e9c1ac9a79e8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-26 02:37:51', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('bb1f43c2774c4260a9e8c9a694a9626d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:34:03', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('bb69c842c4194767b109b7462b82a3e2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:44:22', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('bb863c77a308491480c168f7f00e55d3', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:57:05', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('bb8f483b8f4e4e299e8cd08378d9cbf9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:36:49', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('bbf03e2405004515a4011ac6194cfc4a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:59:05', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('bc52cdd8080c44b2ba833c8f2cae0ddf', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:49:49', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('bc9bbe16424943c38fc667a67574846e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:34:44', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('bcc2e6b583934c859017d5d9f330687d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:12:03', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('bd38dc8516814fbcb14101596a590c14', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 11:40:31', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('bd5380918fcf4b4581cf67b966e7e7ff', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:32', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('be34b804b030431da10351eff07ab445', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 11:24:30', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('be586e3887914b46bbad1d9404df2dd1', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:07:39', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('be6d66ed8f3c43d18b08ce5ec232cfd1', '123', 'gameloft9', '127.0.0.1', 'add', '2019-04-04 07:47:50', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('beb11a58167c4ecc99a1fd2788cd7fc5', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:08:28', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('bf4abf7329014ae38cec308d0ed5f2c4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:46:34', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('bfd1f7fb1f7749c6a8356c734c02d281', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-26 02:25:34', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('c02ff2f67fec427995b8d700ee0eda21', '123', 'gameloft9', '127.0.0.1', 'add', '2019-04-04 08:24:22', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('c0a58b09e9f64760938c9d2f4d5b0d2e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 15:32:39', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c0a8e829a8b7400598aee94b52e5186e', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:51:00', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c0bff41dffb54f44b51c3da60e7a5239', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 01:57:40', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('c149657cb02c4d979ed4e853a18fce70', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 16:37:35', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('c209afa832754a6cbb512511cc275056', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:43:02', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c21e1aee15fb44ca8bd805a10d2f829e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 08:25:08', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c22bb32f21a540cd8b68271348992562', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'upd', '2019-03-18 02:35:36', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c2badb7c31ea4c02b38a341b9f30fd42', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 16:39:58', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('c2f037f0471647a295602bd7f5ed9ff8', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 11:37:33', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c349ca5e52f4479bb3a3d3db87dcf321', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:26:58', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c358dbf201b745989e4ffb19224889dc', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:06', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c386ecb700dd431395a70feb55eea364', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 22:14:06', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('c3b1c8a8d3454bba982d20bb4d307cf4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-22 08:45:18', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c447d889c4b341d78f7be691a5303d52', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:29:08', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('c49b5fb5b93a4c5fa16e644cb09aeb01', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:14:30', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c4cd08903f8e4ed69a2e959021884957', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:03:18', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('c51990c2cd3a449d83e5a8e3c0aab7da', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:57', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c52fdae75fb24299a84ebe79c070e5db', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 02:55:19', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c53a86e5a3dd42849dbc5f50dfdeea9e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:52:08', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c557a50cca594c93ae5a64e3ca08f87c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:34:04', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('c570b7ad2fee41018f2ceb3b57c2c1b6', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:03:18', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('c64468b849734cbfb29d3e268a670e53', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:16:14', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c69cae65d8a2495c94bf4eeea13b98c2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 00:01:44', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c6acf61f0db347c883834e09d6b8649a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:40', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c6c5ee10520f4476a805dfd939d44876', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:28:01', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('c6f99ad6d8264070b1310bcecf601364', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:50:05', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c71bd51a2c704c87b3d7f79f86971017', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:36:47', '更新供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('c739a00243db4ed8b093d9db90ca4179', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:11:18', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('c74c2c9b7e314dd292c95634e4941b0a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:43', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c756c97e6e494fb1b66f107b30f7cc83', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:44:04', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('c77e1fd0d0a045d48d9e461fdfcb7f6a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:53:18', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('c7f0bdf28e754b5597ef2df8fca50e6e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 07:17:51', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('c867ec81a83b4249b1b4bee5d7d7d60b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:07:04', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('c880fb541f204b269fd39c8f231e338d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:39:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c8a4a80bc97645808b8c5a05447f9d46', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 02:36:02', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('c8b5c64f91dd445287133dadf95b7a4c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:01:37', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c8f75ddbb2c8444ca28a9ddaf8366807', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:57:55', '盘点单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('c8fdce7dbb424f43a33e8bd1c2ab1a59', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:58:38', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('c93bf920a50545e68eb3de088affab6f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:41', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('c943fec8aeee4ae2b205388bf79eafd9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 09:34:54', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('c9d121e0c77f497497bea006d06eefd2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:12:25', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('cc353066c17545988fde343bc94ae496', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:30', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ccec15bde1c14e069a121a4f6b1d3391', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:10:03', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('cdde36537b2d4da89cd3578bed878c0c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:42', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ce9803e86d934b57b715271820e44c68', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:51:40', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('ced0a75235e4402b83ab97e13aa3e42e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:46:55', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('cf7896a7fa324eb9a69a00d3529fe601', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:37:09', '更新供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('cfb4ccab3a204eb9b44bab2d592e4918', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 02:30:04', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('d057331516504c5d96e537792570442d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 13:13:34', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('d0d18e5b7f1140aa97576cb527c9efab', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:27:02', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('d1558497bbf842c1ab03721fee8e2c24', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-19 09:38:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d19616ff76224c99acae40b5024c2cd6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:39:23', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('d1f93dbfe73940da80917b0ccc6dd598', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d2450e6161904b14b485f4514008f5e7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 15:46:34', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('d25151809fea416bb56cba8b97516cda', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:23:02', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('d25af8bcfcdb44b7995713fab9d51e71', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-28 04:55:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d306faf234dc4fd1acc32f4b644c33a5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:03:32', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('d34fd9f6ac9744098ccad8e0294605c1', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:12:18', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d414809681e641cebdd708f3279b6e04', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:47:52', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('d4226d96415a458f975a0741450d74e2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 11:23:26', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d43b1fe507174b0a887e94c6b06bfdaa', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 04:45:41', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d4e09eef13b44a08a910c7aa90c0ff3f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:19:28', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d4e1988a08be4023b836fedfa5f443ff', '123', 'gameloft9', '127.0.0.1', 'upd', '2019-03-18 02:05:57', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('d51263a3607849688ebb7860180b0810', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 15:04:27', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d55b9fa239f14eb28f6ac5d1f30a9b28', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:36:16', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('d564839840ca4bcd875904424e271676', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:14:33', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d5a62f790ab244d8858bac3417d92ac9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:39:56', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('d5bb7b270d5641d08dda8bec8cff06bd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d5d69cc6413d415d958549144bf0596d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d5f6627c23bf4e3a905d037b131bd523', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-21 23:59:11', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d64d1200802443f79ed09158f0f00acb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:51:18', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d65f91843bc0445588a7d5450126b994', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:11:53', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d6738326bf5f4caea22c754a92af72b0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 09:32:33', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('d67cb10672f247f789fd3df5bfe26cab', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:59:06', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('d6a43d5411574f8aad11c1cb0eaa353c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 06:48:56', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d6b7174c9d514898abfe20e7928afb47', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:29:07', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('d6ddb8b11533436cbdbb6a5cb29ce522', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:34:16', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('d6faed71b7bd4c2db0736ce51517cfd0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:25:47', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('d74cf9590c784368b65889f79e156907', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:42:00', '更新原料');
INSERT INTO `sys_oper_log_test` VALUES ('d74e1bf671a04f22b29e8f0524f8049d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 11:35:44', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d7f12dd1d5354a42acd5970f569a3f58', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:11:16', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('d7fb88475c0043f5b918e8952e15f011', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:25:12', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('d80e419d10de4f1bb0f187fd2cbc6eb4', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:05:38', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('d8bca8e28ec0461091ff2f9c2fba40b5', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:37:59', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d8e977e90ba74195aa15d4116445c2dd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:06:40', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('d93ae6f4ce8a444faea12e231b2e21a7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 01:14:04', '更新供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('d943a0ccf7e2476ebbcb86dceba9c1c2', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 02:37:40', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('d9819d12fe5f4d709f6876190113d299', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:57:36', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('d98f890446d04bb4bf42c18355d61eda', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 01:45:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('da21840ee3304761bc061041d4dd5e3d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 04:29:52', '更新角色');
INSERT INTO `sys_oper_log_test` VALUES ('da2972ba718e45c6a4f88c9b87e9441f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:47:49', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('dae3c36f971d46f5b45371368cee4c60', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:43:45', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('db3c871b4fe34a5fa12f427ff7ebb1fc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:14:47', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('db6970be2e35427e994e091e84b59c38', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-29 15:19:23', '批量删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('dc4e5ccde5ec4deda1beb097f6055c83', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 10:52:55', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('dc7a391eca8841dfb6ed8476ae64e459', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:11:50', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('dc96f38dbb0a43c7ae8531b8a3eae2e9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 12:40:31', '更新供应商');
INSERT INTO `sys_oper_log_test` VALUES ('dd4721e71614436580bff73159a94bae', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 08:25:55', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('ddc503c2cf5240b99051717801fa74bc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:49:46', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('ddfb0cb00d594fb198aba2bfd143e819', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-28 02:31:01', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('de030af16f34479cb85875ad853e83d4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 22:47:37', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('de37758b3e5244589f6df91450b0ba93', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:43:37', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('de6033bcb8f84543bc5f21c60ede8edc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:28:57', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('de60d98880474c50a53778cabb848e7f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:36:04', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('de761c7a299948a99a8973a840a4578e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:28:44', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('de88018bbe01406083ed49ca0f9863c4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-26 02:16:28', '删除供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('dedc991a537747ffb732f4abb9b4179f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:05:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('dee3653765864d6cb09ec8cbe6fb4ce5', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:12:39', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('df834a9ce0a64ff6a7e911083d02bae6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:22:34', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('dfc68e2e67a44ea2aee2eb50173c0c64', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-02 03:16:54', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('e010b9d7afec4f0e95a116ddf530887b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e01f9c0c8e934624856155202aaa507b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:32:56', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('e02543d694f74ca9a4d2d64b4604a9b4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:52:32', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e051b4592cd7424aac0f4655740a8db8', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 07:51:44', '新增生产领料单');
INSERT INTO `sys_oper_log_test` VALUES ('e11b4367f495498ea7af7bc27c257491', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:49:19', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('e16796fe13764c0eb939f6b21013736f', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:36:18', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('e1b39ce9088f466d830c7d165c9e28af', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:39:45', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e1d7418b974148a0b4484c07401b82e9', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:24:40', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e21448fdcdc04ee8a4a7a58286b6deb0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 13:27:26', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('e2155e9d34154f6196ede9c3355aac63', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 02:50:14', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e319c0dd81464bf190bd65eb748a6b90', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 07:31:54', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e31b220b29de4802b2a71a72faa57896', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e32312327dd44dbaaf7b17bb9a9bae21', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 09:53:55', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('e3253b36085f4be1982987c27cc2d961', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:47', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e3314c71dacd49508685ae16ca8c205c', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 10:20:38', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e33e1175a7284bbb83f21f7f6682b667', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'del', '2019-03-30 05:33:43', '删除仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('e34b3f312a0148df93560a7fe0de1593', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:31:33', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e42165541f4f4a049eb289c3ec7604df', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:34:17', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('e4322b5152294039a602f1eab4b14ccf', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:04:16', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('e459c5852f134ee996b6a7babf4fae59', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 00:29:52', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e4b99c2aaaaf43bfa9399b7e154573aa', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:37:04', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e4c0748a723b4774b33ead0cbfc6fafb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:15:26', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('e510e4b225104a49a16426ee3adb049a', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:00:46', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('e52a03e575474c3f931d505d247ad5a4', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:10:52', '添加角色');
INSERT INTO `sys_oper_log_test` VALUES ('e545089b2c564411bd5b81637119ff01', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:29:58', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('e58665c2b87d4b73bfc090701abcfb25', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:20:19', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e58e86ebfd944e5db73f887f0b91d75f', '7469a0cba4a9487385e9269607a12dab', 'lennon', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:27:26', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('e5d63bf8f5fb4461914283f0985a7525', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:27:36', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e5f2883f32e2405eb7d7f2a58935e4a7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:34:08', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e69cc4cf9c9342de94d33d3620544d8e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-21 11:38:12', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e74e118c0ac84f1cb1a2278a0e76e3ad', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 00:03:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e796330d3ed948eab4ca652008ead3d4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:58:22', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('e8382f210ab1416282be8a157344d815', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:26:37', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('e87b4718bf0b4bcda094ef73aa39c751', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-19 15:29:54', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e88bbdffe1804e1d8103d34fcb22a633', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-18 02:26:21', '更新用户');
INSERT INTO `sys_oper_log_test` VALUES ('e89360ce9ee44d529b22d0d789178430', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('e8bcfb158fac4585ba811ab0621e22a7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:17:53', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('e8e4fe5e6b794206b2228ef3c172f8be', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 13:39:16', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('e9522b368567407492e000ce65249f96', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:03', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ea9fa7317bb94806a60acd23426eac06', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 06:07:45', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('eac03cf35a8a4637b0d643aa6fd38790', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-26 02:11:23', '添加供应关系');
INSERT INTO `sys_oper_log_test` VALUES ('eacd9555686d46d4add1b8f4b5107f71', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:49:52', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('eaea388d8e6d4dec9fa7ab1d0e8e9afd', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:10:56', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('eb7b31620fda4101ae4a40cfaf6d25bd', '123', 'gameloft9', '127.0.0.1', 'upd', '2019-04-04 07:32:28', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('ebd34217363a4a60a933bd604a3b6243', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:48:12', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('ebf17280c1e545deaca8f2bc3d41178d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 11:26:49', '新增销售出库单');
INSERT INTO `sys_oper_log_test` VALUES ('ebf46c252969450088115bdbdd33931c', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 05:59:24', '添加供应商');
INSERT INTO `sys_oper_log_test` VALUES ('ec1208df99384fadb27d1cef8e5ad124', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 01:44:26', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ec8966ed710341e881a523a0169cd59f', '123', 'gameloft9', '127.0.0.1', 'add', '2019-04-04 07:31:26', '新增销售入库单');
INSERT INTO `sys_oper_log_test` VALUES ('eca6c26c481043f7836a6e4eaf57ce55', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:56:36', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('eca9033c1ae14c23994db56ae2813f0b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 14:47:13', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ecdfe2ab29c747cd98eded765288ed4e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 15:05:06', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('ed09ed85cf7641ffb6bfc0fbbc41e2e6', '7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 12:24:44', '新增生产入库单');
INSERT INTO `sys_oper_log_test` VALUES ('ed4c8de6e46045e785e6c159c4975b4a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 10:57:16', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('edd4e923e5a546c2b94bf3c244964d6a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:10:23', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('eddec10172a1481cb680a43598865dbb', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 22:50:14', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('ede937a73e144d418bbb6b72a30cb0f0', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 03:03:33', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ee1cf9f926384d57977a35a9c07b1aac', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 02:45:53', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ef1045adf3dc4a129a68ae3db346098e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 06:05:21', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('ef15657ec2784ee28fd6d1889435125a', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-02 01:47:15', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('ef58e968091a4334be2022150919a489', 'a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '127.0.0.1', 'add', '2019-03-18 02:25:23', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('efad8df51100400d90e5481ff008e392', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:08:02', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('efb521031e3d4d0eade2cfbece82085e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:17:24', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('efcf39a97595459c895f7a67afd0318f', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:10:12', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('f027c9e643854e9888a8d31d34b5fae4', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 09:00:30', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('f19ea3ecf5104fbab8ea6405d04bf3d8', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 06:53:29', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f1cbdbcc635a4db3b7a025ca98b51335', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 02:43:26', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('f217ea7c42f746acaaf5696196173cbf', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:45:00', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('f2440ca64a7747d2b87f9dad7dac5627', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-29 22:50:49', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('f2466a52af6644a8aca620b46175d0da', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-23 07:10:07', '更新原料');
INSERT INTO `sys_oper_log_test` VALUES ('f2786dea62264b3aa643cd60a301b36b', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:39', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f35fbd50943b4c62945c7899931ab454', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 21:58:14', '货物入库');
INSERT INTO `sys_oper_log_test` VALUES ('f37f080a5eae4b7eb6b70af544137ce0', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:09:51', '新增入库单');
INSERT INTO `sys_oper_log_test` VALUES ('f386efe781e24180a4abc67c21b101df', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-23 06:49:54', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f4574b24d89b41639406df22aec9c764', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-03-30 05:23:31', '新增出库单');
INSERT INTO `sys_oper_log_test` VALUES ('f4634320a91645aa9777c87a1b4a64b6', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 02:18:14', '入库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('f4b34050eed3415486262cb73ad5c266', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 13:34:59', '盘点结束');
INSERT INTO `sys_oper_log_test` VALUES ('f515eb07c2fd4638ad97a7ce3b03f618', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:15:01', '货物出库');
INSERT INTO `sys_oper_log_test` VALUES ('f54e198b438947caacb696e07bf3bfe7', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 09:35:08', '出库单审核通过');
INSERT INTO `sys_oper_log_test` VALUES ('f58ea98fb2a840d6863b2af45acd39d2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:20', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f5a06f5390bb49179e998c6c8db65182', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:49', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f62ba09b927047808068f4c0dbdd9e2b', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:25:10', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('f6745df9e6794f7390b3bee8e88edc00', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:56', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f6c1fb1f2302412fa73a0bef080a8020', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 08:57:24', '更新盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('f70f7006aa2c4e0eb1b45d8716fbe589', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 09:12:29', '批量添加盘点单明细');
INSERT INTO `sys_oper_log_test` VALUES ('f7229caa78084a5d884161a1df93f0de', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:56:32', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f758e1e903bb45e3bcb4d97ec89afc68', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'del', '2019-04-03 02:44:55', '删除菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f77c111e1f0d4c18a159f2a1b9ce2287', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 00:00:25', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f86ea4248b9e4541a2d064a091fc3bbc', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 09:36:57', '驳回出库单');
INSERT INTO `sys_oper_log_test` VALUES ('f879381d96524dec89170cf24929cdfd', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:02:59', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('f8a7c392d3144bc288581cd0d13187e7', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 02:04:42', '添加用户');
INSERT INTO `sys_oper_log_test` VALUES ('f8dc079f555a4aba88f014425b83a590', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:48:34', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('fa0bb81f16a3479daadb33dbb545400d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-04 06:39:15', '新增采购退货单');
INSERT INTO `sys_oper_log_test` VALUES ('fa8804118fb24d79a5c1ddba5fa08dc2', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 06:45:30', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('fae2d645f5e74900b4c35aed0a5d9cf9', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('fb11ad43ba2046d0b7e918faa7db747e', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-01 08:21:28', '新增盘点单');
INSERT INTO `sys_oper_log_test` VALUES ('fbc0f4ce3d3d405a928f8b073f59db1d', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:40:42', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('fbf88d75607e462e9ad25e4293d5c813', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-03-18 07:23:31', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('fcdadd20e5ef40e29dd45a1c1b40ec38', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-30 05:14:13', '驳回入库单');
INSERT INTO `sys_oper_log_test` VALUES ('fd9b2fe98b564b00b5f2030d4dc06aab', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'upd', '2019-03-29 12:59:09', '更新仓库单');
INSERT INTO `sys_oper_log_test` VALUES ('fe2fa6e94f0d4ad0abe4115d49c23304', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-04-03 06:50:22', '更新菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ff0ad418f475483a8b901bcfd01cca71', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'add', '2019-04-03 14:50:08', '添加菜单');
INSERT INTO `sys_oper_log_test` VALUES ('ff1d3605e7794e8c9643bc045b4ac83d', '48f63b114ea344b2a7b7f8296b929fde', 'admin', '0:0:0:0:0:0:0:1', 'add', '2019-04-04 10:06:25', '新增销售退货单');
INSERT INTO `sys_oper_log_test` VALUES ('ffe6c9dc4ddd4eee82fcfc63193ff03e', '123', 'gameloft9', '0:0:0:0:0:0:0:1', 'upd', '2019-03-22 09:03:00', '更新菜单');

-- ----------------------------
-- Table structure for sys_organize_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_organize_test`;
CREATE TABLE `sys_organize_test` (
  `ID` varchar(32) NOT NULL COMMENT '组织机构表',
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `ORGANIZE_NAME` varchar(255) NOT NULL COMMENT '机构名称',
  `ORGANIZE_CODE` varchar(8) NOT NULL COMMENT '机构编码',
  `TREE_LEVEL` int(11) NOT NULL COMMENT '机构级别',
  `TREE_PATH` varchar(200) DEFAULT NULL COMMENT '机构路径',
  `CREATE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `uidx_org_name` (`ORGANIZE_NAME`) USING BTREE,
  UNIQUE KEY `uidx_org_code` (`ORGANIZE_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_organize_test
-- ----------------------------
INSERT INTO `sys_organize_test` VALUES ('21a6ff8a6abf410eaf97981c1da039d8', null, '总公司', '010001', '1', '21a6ff8a6abf410eaf97981c1da039d8', '2017-12-27 17:06:04', '2017-12-27 17:06:04');
INSERT INTO `sys_organize_test` VALUES ('2722c1aea4f84754ae1a0a03a15f5698', '54fa67f3d443427c9f47432ead20f2f4', '生产部', '030003', '3', '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4#2722c1aea4f84754ae1a0a03a15f5698', null, null);
INSERT INTO `sys_organize_test` VALUES ('423c7c2b400d49fc89262ed1cbca0aff', '54fa67f3d443427c9f47432ead20f2f4', '供应部', '030002', '3', '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4#423c7c2b400d49fc89262ed1cbca0aff', null, null);
INSERT INTO `sys_organize_test` VALUES ('54fa67f3d443427c9f47432ead20f2f4', '21a6ff8a6abf410eaf97981c1da039d8', '湖北分公司', '010002', '2', '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4', '2017-12-27 17:06:22', '2017-12-27 17:06:22');
INSERT INTO `sys_organize_test` VALUES ('f08cfae91825474a8075a8826720011a', '54fa67f3d443427c9f47432ead20f2f4', '财务部', '030001', '3', '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4#f08cfae91825474a8075a8826720011a', null, null);
INSERT INTO `sys_organize_test` VALUES ('fdbc2172ce8e432ea65490554ca01e55', '54fa67f3d443427c9f47432ead20f2f4', '采购部', '030004', '3', '21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4#fdbc2172ce8e432ea65490554ca01e55', null, null);

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `available` int(11) DEFAULT NULL COMMENT '是否可用，0表示可用，1表示不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role` varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `available` int(11) DEFAULT NULL COMMENT '是否可用，0表示可用，1表示不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_test`;
CREATE TABLE `sys_role_test` (
  `ID` varchar(32) NOT NULL COMMENT '角色表',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名',
  `IS_SUPER` int(2) DEFAULT NULL COMMENT '是否是超级管理员',
  `IS_DELETED` int(2) DEFAULT NULL COMMENT '是否删除',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建用户',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '更新用户',
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_test
-- ----------------------------
INSERT INTO `sys_role_test` VALUES ('0a7a39c04bcb4f788b272aff132136ef', '仓库管理员', '0', '0', 'gameloft9', '2019-03-18 10:00:45', null, '2019-03-18 10:00:45');
INSERT INTO `sys_role_test` VALUES ('343859934ece44c988f53700fb34c80a', 'test', '0', '0', 'gameloft9', '2018-01-11 14:46:22', null, '2018-01-11 14:46:22');
INSERT INTO `sys_role_test` VALUES ('4449f85a9f014489aba2c4b3cc4cd9da', '采购部门员工', '0', '0', 'gameloft9', '2019-04-03 14:47:53', 'gameloft9', '2019-04-03 06:47:14');
INSERT INTO `sys_role_test` VALUES ('58b02f3c30084e9a8124adb86373ad65', '采购部门经理', '0', '0', 'gameloft9', '2019-04-03 14:47:48', 'gameloft9', '2019-04-03 06:47:09');
INSERT INTO `sys_role_test` VALUES ('809df45d06204d8fbd19019b0db3455d', '财务员工', '0', '0', 'afabao', '2019-03-18 10:11:54', null, '2019-03-18 10:11:54');
INSERT INTO `sys_role_test` VALUES ('8449ee9c37ce405fa8c51756bf5f1c38', '财务主管', '0', '0', 'afabao', '2019-03-18 10:11:53', null, '2019-03-18 10:11:53');
INSERT INTO `sys_role_test` VALUES ('98cd74e57fce4023a3470b1734e059a0', '仓库主管', '0', '0', 'gameloft9', '2019-03-18 12:29:38', 'admin', '2019-03-18 04:29:52');
INSERT INTO `sys_role_test` VALUES ('bf9286a4918b4a3c84267b3e2e0fea12', '生产部员工', '0', '0', 'lennon', '2019-03-18 10:22:35', null, '2019-03-18 10:22:35');
INSERT INTO `sys_role_test` VALUES ('eaceb6783430421090b8f84c57e48df5', '生产部主管', '0', '0', 'lennon', '2019-03-18 10:22:38', null, '2019-03-18 10:22:38');
INSERT INTO `sys_role_test` VALUES ('J/F9-+?', 'admin', '1', '0', 'v[l_4zL8', '2008-03-01 04:17:00', ':9He2+GW', '2002-09-27 03:13:16');

-- ----------------------------
-- Table structure for sys_supplier
-- ----------------------------
DROP TABLE IF EXISTS `sys_supplier`;
CREATE TABLE `sys_supplier` (
  `ID` varchar(255) NOT NULL,
  `SUPPLIER_NAME` varchar(255) NOT NULL,
  `SUPPLIER_DESCRIBE` varchar(255) DEFAULT NULL,
  `CHARGE_NAME` varchar(255) NOT NULL,
  `PHONE` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_supplier
-- ----------------------------
INSERT INTO `sys_supplier` VALUES ('33eb96d3ccd840d6a9e9cf45fbee3f5f', '青岛海磊石材', '', '戴鹏', '15092086003', ' 532354354@xx.com');
INSERT INTO `sys_supplier` VALUES ('556e448e34ee47cd9dc22619e08cf08e', '厦门奥普勒斯石材化工有限公司', '位于福建厦门市集美区,常年对外销售水性防护剂、油性防护剂、石材清洗系列、石材色差处理系列等产品', '涂经理', '18046364289', ' 7865456@qq.com');
INSERT INTO `sys_supplier` VALUES ('6c3899edc0e24bd5befebc7e72633c7d', '五莲县三友石材厂', '集矿山开采加工销售于一体的企业 ', '席庆俊', '13910079218', '111111@xx.com');
INSERT INTO `sys_supplier` VALUES ('6d3016652c5e41a19eb3cc93abda68d4', 'xxx供应商', 'bcvnv', ' xx', '132434534', '34554645df  ');
INSERT INTO `sys_supplier` VALUES ('93c01eeff9cc46a7ab9242eef5a3accf', ' 莱州市磐石石材厂', ' 以诚信交朋友，以质量求生存，以低价求市场，以实惠予客户', '刘先生', '86-0535-2361782', ' 25436653@qq.com');
INSERT INTO `sys_supplier` VALUES ('b24535e3b7484e29b76c39f69382b934', ' 南京环宇石材有限公司', '专业经营进口国产石材、加工和工程设计安装的企业', '王瑞雨', '25-86453685', ' 2586453685@qq.com');
INSERT INTO `sys_supplier` VALUES ('b9a63f9650ff4145942f400a4fd21324', '深圳市广磊石材', '', '柯丰', '13434754716', '526436@qq.com');
INSERT INTO `sys_supplier` VALUES ('cc8052bad1874d4b9255d798ad79d987', '厦门永峰华石材有限公司', '产品专业生产加工的公司', '某某某', '0592-6251002', '253346@qq.com');
INSERT INTO `sys_supplier` VALUES ('d6f367d5ba614781b7ed7f1949354465', '曲阳鼎弘有限公司', '牛逼Class', '老王', '15659267776', '123@qq.com');
INSERT INTO `sys_supplier` VALUES ('e1bdf280fd304b41bb0d43e309c468b9', '莆田市三鑫石材有限公司', '', ' 沈建华', '18850988098', ' 18850988098@qq.com');
INSERT INTO `sys_supplier` VALUES ('e457c0e16fb64d029bac8ef0cbc128b0', '南安市鸿顺石材厂', '主要生产国内外畅销花岗岩石材：美国红麻，英国棕，印度红，幻彩红，金孔雀，虾红，锈石', '杨', '15160366779', ' 10806449@qq.com');
INSERT INTO `sys_supplier` VALUES ('ea03d17a3f674bb7a3a54a30167205a0', '重庆市米黄石材有限公司', '拥有完整、科学的质量管理体系', '黄大钢', ' 86-023-75707588', '5678365@qq.com');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '用户名',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `locked` int(11) DEFAULT NULL COMMENT '是否锁住，0表示未锁，1表示锁住',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_test`;
CREATE TABLE `sys_user_role_test` (
  `ID` varchar(32) NOT NULL COMMENT '用户角色表',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_test
-- ----------------------------
INSERT INTO `sys_user_role_test` VALUES ('03f9afb1d42e49d6a026f537d9f033b6', '9dbdc3a6cc444190bb5ac4e15df6234b', '343859934ece44c988f53700fb34c80a');
INSERT INTO `sys_user_role_test` VALUES ('0dd21a8fc8914509bbf2f528038f54f4', 'c3053bdf0f584bb1ac7dad28b8d57f46', '4449f85a9f014489aba2c4b3cc4cd9da');
INSERT INTO `sys_user_role_test` VALUES ('1cded9d2189147f68599963bc8abac4d', '7469a0cba4a9487385e9269607a12dab', 'J/F9-+?');
INSERT INTO `sys_user_role_test` VALUES ('2d821e07a5264fcaa21767aa1c6d3dc6', '056e306ddec648189055f153e2e080ff', '8449ee9c37ce405fa8c51756bf5f1c38');
INSERT INTO `sys_user_role_test` VALUES ('2fb2a9fb965e462eb72c14361a83f006', '123', 'J/F9-+?');
INSERT INTO `sys_user_role_test` VALUES ('31a47cd46ccd41f5b1be496da7e110bc', '48f63b114ea344b2a7b7f8296b929fde', 'bf9286a4918b4a3c84267b3e2e0fea12');
INSERT INTO `sys_user_role_test` VALUES ('44dbaca72e9a4f48b8384f974557eff7', '893177e72bcb4d2cb2b3c1374ec01d16', '809df45d06204d8fbd19019b0db3455d');
INSERT INTO `sys_user_role_test` VALUES ('470a8ab0881a422d8bbcec8f0be00c69', '48f63b114ea344b2a7b7f8296b929fde', 'eaceb6783430421090b8f84c57e48df5');
INSERT INTO `sys_user_role_test` VALUES ('5b0484472f98493a9155dcf90360ff9d', '6668972296454bd8b3ce96b519f37cc2', '58b02f3c30084e9a8124adb86373ad65');
INSERT INTO `sys_user_role_test` VALUES ('619769592cc54fd387749a8a9e9cb772', '48f63b114ea344b2a7b7f8296b929fde', 'J/F9-+?');
INSERT INTO `sys_user_role_test` VALUES ('6e272268b70f489a98be44876faf4588', '7469a0cba4a9487385e9269607a12dab', '343859934ece44c988f53700fb34c80a');
INSERT INTO `sys_user_role_test` VALUES ('75c29757153444bab4e114e775a0fac1', '48f63b114ea344b2a7b7f8296b929fde', '8449ee9c37ce405fa8c51756bf5f1c38');
INSERT INTO `sys_user_role_test` VALUES ('84649e66741b4302a4830247acbc623e', '7469a0cba4a9487385e9269607a12dab', 'eaceb6783430421090b8f84c57e48df5');
INSERT INTO `sys_user_role_test` VALUES ('8b8240f562984ffd82a077163973ce82', 'a0f8c6ff46464463bd5f8f485d7e98af', 'J/F9-+?');
INSERT INTO `sys_user_role_test` VALUES ('98d57e1963ed42f7a4891e7b7180e29a', 'beace5044a9844b182c5707156b5ca27', 'bf9286a4918b4a3c84267b3e2e0fea12');
INSERT INTO `sys_user_role_test` VALUES ('9c632fbeda614f87b2bcad820cc01efd', '48f63b114ea344b2a7b7f8296b929fde', '0a7a39c04bcb4f788b272aff132136ef');
INSERT INTO `sys_user_role_test` VALUES ('b19d39d319e04f989b35879bc5d394a2', '48f63b114ea344b2a7b7f8296b929fde', '809df45d06204d8fbd19019b0db3455d');
INSERT INTO `sys_user_role_test` VALUES ('d6c5d9c5eb104a86818ccc0c0313692c', '48f63b114ea344b2a7b7f8296b929fde', '343859934ece44c988f53700fb34c80a');
INSERT INTO `sys_user_role_test` VALUES ('da3c38b9648e4f30ac80353f780f6b2d', '7469a0cba4a9487385e9269607a12dab', 'bf9286a4918b4a3c84267b3e2e0fea12');
INSERT INTO `sys_user_role_test` VALUES ('df7bcde4a361435c9538806369ead006', '48f63b114ea344b2a7b7f8296b929fde', '98cd74e57fce4023a3470b1734e059a0');
INSERT INTO `sys_user_role_test` VALUES ('f83cbdceed014b5b8b5a2f20c48dea06', '7c6b9dd82aa642ecaeabc6f5677779ba', 'eaceb6783430421090b8f84c57e48df5');

-- ----------------------------
-- Table structure for sys_user_socket
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_socket`;
CREATE TABLE `sys_user_socket` (
  `id` varchar(255) NOT NULL,
  `websocketid` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_socket
-- ----------------------------

-- ----------------------------
-- Table structure for t_depot_order
-- ----------------------------
DROP TABLE IF EXISTS `t_depot_order`;
CREATE TABLE `t_depot_order` (
  `ID` varchar(255) NOT NULL COMMENT '仓库订单ID',
  `ORDER_TYPE` int(11) NOT NULL COMMENT '仓库单类型',
  `GOODS_ID` varchar(255) NOT NULL COMMENT '原料/成品ID(关联的关联字段)',
  `GOODS_NUMBER` varchar(255) NOT NULL COMMENT '货品数量',
  `APPLY_USER` varchar(255) NOT NULL COMMENT '申请人',
  `APPLY_TIME` date NOT NULL COMMENT '申请时间',
  `STATE` varchar(255) NOT NULL COMMENT '订单状态',
  `AUDIT_TYPE` int(11) DEFAULT NULL COMMENT '订单类型',
  `ORDER_AUDIT_USER` varchar(255) NOT NULL COMMENT '审核人',
  `ORDER_AUDIT_TIME` date NOT NULL COMMENT '审核时间',
  `APPLY_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '申请描述',
  `AUDIT_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '审核描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_depot_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `t_purchase_order`;
CREATE TABLE `t_purchase_order` (
  `ID` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '采购订单ID',
  `ORDER_NUMBER` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '订单编号(原NUMBER)',
  `GOODS_ID` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '原料商品ID(关联表的关键字段)',
  `GOODS_NAME` varchar(255) NOT NULL COMMENT '商品名称',
  `SUPPLIER_NAME` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '货品数量',
  `PRICE` varchar(255) NOT NULL COMMENT '货品单价',
  `TOTAL_PRICE` varchar(255) DEFAULT NULL COMMENT '货品总价格',
  `APPLY_USER` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '申请人',
  `APPLY_TIME` datetime NOT NULL COMMENT '申请时间',
  `APPLY_PAY_TIME` datetime DEFAULT NULL COMMENT '申请人付款时间',
  `STATE` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '订单状态',
  `FINANCE_STATE` varchar(255) DEFAULT NULL COMMENT '财务审核状态',
  `ORDER_AUDIT_USER` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单审核人',
  `ORDER_AUDIT_TIME` datetime DEFAULT NULL COMMENT '订单审核时间',
  `APPLY_DESCRIBE` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '申请描述',
  `AUDIT_DESCRIBE` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核描述',
  `FINANCE_AUDIT_USER` varchar(255) DEFAULT NULL COMMENT '财务审核人',
  `FINANCE_AUDIT_TIME` datetime DEFAULT NULL COMMENT '财务审核时间',
  `AUDIT_TYPE` int(11) DEFAULT NULL COMMENT '审核订单类型',
  `FINANCE_AUDIT_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '财务审核描述',
  `DEPOT_STATE` varchar(255) DEFAULT NULL COMMENT '仓管审核状态',
  `DEPOT_USER` varchar(255) DEFAULT NULL COMMENT '仓库审核人',
  `DEPOT_TIME` datetime DEFAULT NULL COMMENT '仓库审核时间',
  `DEPOT_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '仓库审核描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_purchase_order
-- ----------------------------
INSERT INTO `t_purchase_order` VALUES ('013867c4169b477996d9214841dc1108', 'CG040468982193', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '12', '350', '4200.00', 'admin', '2019-04-04 13:10:07', null, '审核通过', '已付款', 'admin', '2019-04-04 13:11:37', '12', '123', 'admin', '2019-04-04 13:12:20', '1', '12', '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('2bee6469d7084ff194cbea189aad3cf8', 'CG040413485352', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '12', '890', '10680.00', 'gameloft9', '2019-04-04 07:30:11', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 07:30:21', '12', '12', 'gameloft9', '2019-04-04 07:30:51', '1', '可以', '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('3336e330c99247c5a0f4cacf0d152e3c', 'CG040396965387', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '1', '563', '563.00', 'gameloft9', '2019-04-03 13:26:10', null, '审核通过', '已付款', 'gameloft9', '2019-04-03 13:26:26', '1', 'wq', null, null, '1', null, '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('3f5f6bdb81154a0bbbcdbae5ef07e214', 'CG040429935810', 'd3a0520e6ca649e08255f4cce6388c25', '白色细砂石', '曲阳鼎弘有限公司', '9', '499', '4491.00', 'gameloft9', '2019-04-04 16:22:53', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 16:23:14', '9', 'ewq', 'gameloft9', '2019-04-04 08:23:54', '1', 'keyi', '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('4ae5f4c08dc74788ba325603003dbd67', 'CG040472295762', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '12', '350', '4200.00', 'gameloft9', '2019-04-04 13:29:27', null, '未提交', null, null, null, '23', null, null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('4ed8688b0a6543dd90927b48ce6c5795', 'CG040498702923', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '22', '563', '12386.00', 'gameloft9', '2019-04-04 04:18:50', null, '审核通过', '未通过', 'gameloft9', '2019-04-04 04:19:07', '22', '22', 'gameloft9', '2019-04-04 04:19:39', '1', '不行', null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('4f590cf4f9614106b688b3083736c23c', 'CG040443126718', '1faa767ff0e84434b5f3ab6adb6c4f75', '芝麻白花岗石', '青岛海磊石材', '90', '345', '31050.00', 'gameloft9', '2019-04-04 03:53:44', null, '审核通过', null, 'gameloft9', '2019-04-04 03:59:06', '90', '90', null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('518f568addca46a5b857ca1c8d8e4c0c', 'CG040490580788', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '12', '350', '4200.00', 'gameloft9', '2019-04-04 11:53:09', null, '审核通过', null, 'gameloft9', '2019-04-04 12:31:21', '12', '12', null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('727250fe36354e0da5ece7a63effa764', 'CG040487300078', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '22', '240', '5280.00', 'gameloft9', '2019-04-04 13:29:49', null, '未提交', null, null, null, '12', null, null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('80125e9a4fff4819963ebc8e50721e75', 'CG040407034049', '6413bc2019e940fca2a8d6f37d4d84c2', '黑色板岩', '曲阳鼎弘有限公司', '33', '600', '19800.00', 'gameloft9', '2019-04-04 06:06:31', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 06:06:43', '33', '33', null, null, '1', null, '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('8b0838c613ae42b0baa60988177075bb', 'CG040428167532', '1faa767ff0e84434b5f3ab6adb6c4f75', '芝麻白花岗石', '青岛海磊石材', '1', '345', '345.00', 'admin', '2019-04-04 06:02:13', null, '审核通过', '已付款', 'admin', '2019-04-04 06:03:01', '123', '123', null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('96681e2b70f7455f8ebc8f2f87fbc5d9', 'CG040449484773', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '12', '563', '6756.00', 'gameloft9', '2019-04-04 06:23:53', null, '审核通过', null, 'gameloft9', '2019-04-04 11:28:29', '12', '78', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('a789b28e0b3d4342868e98721bef1036', 'CG040473282579', '1faa767ff0e84434b5f3ab6adb6c4f75', '芝麻白花岗石', '青岛海磊石材', '22', '345', '7590.00', 'gameloft9', '2019-04-03 17:21:57', null, '审核通过', '已付款', 'gameloft9', '2019-04-03 18:57:47', '222', 'ter', null, null, '1', null, '确认中', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('b4e6c8fd6a284eebb43872f292f81fa5', 'CG040457924226', 'edc4cc3f7da4486c8a0bb3cafe4ec772', '雪花白大理石', '青岛海磊石材', '33', '455', '15015.00', 'gameloft9', '2019-04-04 07:46:26', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 07:46:40', '33', '33', 'gameloft9', '2019-04-04 07:47:16', '1', 'keyi', '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('e3aceafa700e467ba9f7b4fa8629568e', 'CG040450039644', '6413bc2019e940fca2a8d6f37d4d84c2', '黑色板岩', '曲阳鼎弘有限公司', '88', '150', '13200.00', 'gameloft9', '2019-04-04 13:29:35', null, '提交审核中', null, null, null, '8', null, null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('e3b696937337442fb54e02f2e12d6829', 'CG040448114893', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '38', '563', '21394.00', 'gameloft9', '2019-04-04 06:41:25', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 06:41:40', '38', '38', 'gameloft9', '2019-04-04 06:43:41', '1', '可以', '审核通过', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('ee235643a35c473ba358eb4fef3cf847', 'CG040451662907', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '123', '890', '109470.00', 'gameloft9', '2019-04-04 11:26:45', null, '审核通过', null, 'gameloft9', '2019-04-04 11:27:07', '34', '32', null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('ee5434cdbf844fe78e403a6a59388f19', 'CG040407770141', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '11', '563.2', '6195.20', 'gameloft9', '2019-04-04 04:17:11', null, '审核通过', '已付款', 'gameloft9', '2019-04-04 04:17:23', '11', '11', 'gameloft9', '2019-04-04 04:18:21', '1', '可以', '确认中', null, null, null);
INSERT INTO `t_purchase_order` VALUES ('f5cfcb5fba8644c2a646c0058de9bb6c', 'CG040483066131', '6413bc2019e940fca2a8d6f37d4d84c2', '黑色板岩', '曲阳鼎弘有限公司', '88', '600', '52800.00', 'gameloft9', '2019-04-04 11:23:58', null, '审核通过', null, 'gameloft9', '2019-04-04 11:24:16', '88', '90', null, null, '1', null, null, null, null, null);
INSERT INTO `t_purchase_order` VALUES ('f5d7a2e6d99740f6b93579cead0e44ed', 'CG040485732924', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '8', '890', '7120.00', 'gameloft9', '2019-04-04 11:23:49', null, '审核通过', null, 'gameloft9', '2019-04-04 11:24:22', '9', '80', null, null, '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_purchase_return
-- ----------------------------
DROP TABLE IF EXISTS `t_purchase_return`;
CREATE TABLE `t_purchase_return` (
  `ID` varchar(255) NOT NULL COMMENT '申请退货ID',
  `ORDER_NUMBER` varchar(255) NOT NULL COMMENT '订单编号',
  `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `GOODS_NAME` varchar(255) NOT NULL COMMENT '商品名称',
  `SUPPLIER_NAME` varchar(255) DEFAULT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL COMMENT '商品数量',
  `PRICE` varchar(255) NOT NULL COMMENT '单价',
  `TOTAL_PRICE` varchar(255) NOT NULL COMMENT '总价格',
  `APPLY_USER` varchar(255) NOT NULL COMMENT '申请人',
  `APPLY_TIME` datetime NOT NULL COMMENT '申请时间',
  `DEPOT_STATE` varchar(255) NOT NULL COMMENT '审核状态',
  `FINANCE_STATE` varchar(255) DEFAULT NULL COMMENT '财务审核状态',
  `AUDIT_TYPE` varchar(255) DEFAULT NULL COMMENT '订单类型',
  `DEPOT_USER` varchar(255) DEFAULT NULL COMMENT '仓库审核人',
  `DEPOT_TIME` datetime DEFAULT NULL COMMENT '仓库审核时间',
  `DEPOT_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '仓库审核描述',
  `FINANCE_AUDIT_USER` varchar(255) DEFAULT NULL COMMENT '财务审核人',
  `FINANCE_AUDIT_TIME` datetime DEFAULT NULL COMMENT '财务审核时间',
  `FINANCE_AUDIT_DESCRIBE` varchar(255) DEFAULT NULL COMMENT '财务审核描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_purchase_return
-- ----------------------------
INSERT INTO `t_purchase_return` VALUES ('20be1c0d744f448081c1bf9f9e96cdd1', 'CG040407034049', '6413bc2019e940fca2a8d6f37d4d84c2', '黑色板岩', '曲阳鼎弘有限公司', '33', '600', '19800.00', 'admin', '2019-04-04 13:14:41', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('247187f8c15646f9ac5e09c8fb0c328f', 'CG040448114893', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '38', '563', '21394.00', 'admin', '2019-04-04 13:15:33', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('6fcbe6001718438fbe6250cdc6787969', 'CG040429935810', 'd3a0520e6ca649e08255f4cce6388c25', '白色细砂石', '曲阳鼎弘有限公司', '9', '499', '4491.00', 'gameloft9', '2019-04-04 08:25:41', '提交审核中', '已收款', '3', '审核通过', null, null, 'gameloft9', '2019-04-04 08:29:50', '111');
INSERT INTO `t_purchase_return` VALUES ('8a4fb4ba9eb14d5185e7176fbf9c3a03', 'CG040407034049', '6413bc2019e940fca2a8d6f37d4d84c2', '黑色板岩', '曲阳鼎弘有限公司', '33', '600', '19800.00', 'gameloft9', '2019-04-04 06:08:24', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('9237897417e545f3aa827c8d93d8c130', 'CG040448114893', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '38', '563', '21394.00', 'gameloft9', '2019-04-04 06:44:35', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('cfde40f48ed04ebf858a8aa0af9637b4', 'CG040457924226', 'edc4cc3f7da4486c8a0bb3cafe4ec772', '雪花白大理石', '青岛海磊石材', '33', '455', '15015.00', 'gameloft9', '2019-04-04 07:48:35', '提交审核中', '已付款', '3', '审核通过', null, null, 'gameloft9', '2019-04-04 08:15:34', '可以');
INSERT INTO `t_purchase_return` VALUES ('e988054c2b774bffbd64e8256511616d', 'CG040413485352', '4ffa35972038419b8b9599be77b03fa7', '意大利木纹大理石', ' 南京环宇石材有限公司', '12', '890', '10680.00', 'gameloft9', '2019-04-04 07:32:23', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('fae6e3572c2c4fd1817a359316efa9f2', 'CG040352385730', '1', '芝麻白花岗石', '青岛海磊石材', '213', '345', '73485.00', 'gameloft9', '2019-04-03 11:49:44', '提交审核中', null, null, null, null, null, null, null, null);
INSERT INTO `t_purchase_return` VALUES ('fbdf99f6769d4a9cbe435911a462ed2b', 'CG040396965387', '595ff4a952e8455382194af2576d9225', '樱桃红花岗石', '厦门永峰华石材有限公司', '1', '563', '563.00', 'gameloft9', '2019-04-04 06:39:12', '审核通过', '已收款', null, '', null, null, null, null, null);

-- ----------------------------
-- Table structure for upplier
-- ----------------------------
DROP TABLE IF EXISTS `upplier`;
CREATE TABLE `upplier` (
  `ID` varchar(255) NOT NULL,
  `SUPPLIER_NAME` varchar(255) DEFAULT NULL,
  `SUPPLIER_ DESCRIBE` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `GOODS_TYPE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upplier
-- ----------------------------

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test` (
  `ID` varchar(32) NOT NULL COMMENT '用户测试表',
  `LOGIN_NAME` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(128) DEFAULT NULL,
  `REAL_NAME` varchar(50) DEFAULT NULL,
  `IS_FORBIDDEN` varchar(2) DEFAULT NULL,
  `MOBILE` varchar(11) DEFAULT NULL,
  `ICON` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(32) DEFAULT NULL,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `uidx_login_name` (`LOGIN_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_test
-- ----------------------------
INSERT INTO `user_test` VALUES ('056e306ddec648189055f153e2e080ff', 'manager', '123456', '张大三', '0', '13405918212', null, 'f08cfae91825474a8075a8826720011a', '财务部', null, null);
INSERT INTO `user_test` VALUES ('123', 'gameloft9', '7C4A8D09CA3762AF61E59520943DC26494F8941B', 'leiyao', '0', '13618629441', null, 'gameloft9', '总公司', '2017-12-28 16:24:32', '2017-12-28 16:32:57');
INSERT INTO `user_test` VALUES ('48f63b114ea344b2a7b7f8296b929fde', 'admin', '7C4A8D09CA3762AF61E59520943DC26494F8941B', 'tanghuafeng', '0', '15659267776', null, '423c7c2b400d49fc89262ed1cbca0aff', '供应部', '2019-03-18 10:03:54', null);
INSERT INTO `user_test` VALUES ('6668972296454bd8b3ce96b519f37cc2', 'OliverCH', '123456', '奥海', '0', '15260741111', null, 'fdbc2172ce8e432ea65490554ca01e55', '采购部', null, null);
INSERT INTO `user_test` VALUES ('7469a0cba4a9487385e9269607a12dab', 'lennon', 'AEA745963A1B734222A349D75DD32C515EB43820', 'lenonyuan', '0', '13255939700', null, '21a6ff8a6abf410eaf97981c1da039d8', '总公司', '2019-03-18 10:05:30', null);
INSERT INTO `user_test` VALUES ('7c6b9dd82aa642ecaeabc6f5677779ba', 'lennon1', '7C4A8D09CA3762AF61E59520943DC26494F8941B', '施大雨', '0', '18700001111', null, '2722c1aea4f84754ae1a0a03a15f5698', '生产部', '2019-03-18 11:06:35', '2019-03-18 11:06:53');
INSERT INTO `user_test` VALUES ('893177e72bcb4d2cb2b3c1374ec01d16', 'staff', '123456', '张小三', '0', '13405918210', null, 'f08cfae91825474a8075a8826720011a', '财务部', null, null);
INSERT INTO `user_test` VALUES ('a0f8c6ff46464463bd5f8f485d7e98af', 'afabao', '33A86349EA49B9AD31AA59B952219D7F93CE616F', '啊发包', '0', '13405918215', null, '21a6ff8a6abf410eaf97981c1da039d8', '总公司', '2019-03-18 10:03:48', null);
INSERT INTO `user_test` VALUES ('beace5044a9844b182c5707156b5ca27', 'lennon12', '7C4A8D09CA3762AF61E59520943DC26494F8941B', '施小雨', '0', '18700001111', null, '2722c1aea4f84754ae1a0a03a15f5698', '生产部', '2019-03-18 11:07:11', '2019-03-18 11:07:41');
INSERT INTO `user_test` VALUES ('c3053bdf0f584bb1ac7dad28b8d57f46', 'TsangHai', '123456', '奥海', '0', '15260741111', null, 'fdbc2172ce8e432ea65490554ca01e55', '采购部', null, null);

-- ----------------------------
-- Table structure for w_marker_order
-- ----------------------------
DROP TABLE IF EXISTS `w_marker_order`;
CREATE TABLE `w_marker_order` (
  `ID` varchar(255) NOT NULL,
  `ORDER_ID` varchar(255) NOT NULL,
  `ORDER_TIME` datetime NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `CUSTOMER` varchar(255) NOT NULL,
  `DELIVER_NUMBER` varchar(255) NOT NULL,
  `PLANNED_NUMBER` varchar(255) NOT NULL,
  `ACCEPTED_AMOUNT` varchar(255) NOT NULL,
  `APPLY_USER` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `ORDER_AUDIT_USER` varchar(255) DEFAULT NULL,
  `ORDER_AUDIT_DEPOT` varchar(255) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  `DEPOT_REMARKS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of w_marker_order
-- ----------------------------
INSERT INTO `w_marker_order` VALUES ('da4fcf023ec34bb29ce478e4a882d739', 'xs040402092243', '2019-04-04 20:45:20', '3fae63afb73a42f19b03f5d279f10bd1', 'aaa', '1', '15', '15.00', 'admin', '财务收到货款', '销售主管', '仓库主管', '。。。。', null);
INSERT INTO `w_marker_order` VALUES ('dc6116bf5afa482c82ab3ec0c6fad4aa', 'xs040457326977', '2019-04-04 21:38:26', '01612d22019f4f88af8686ceb7383938', 'xiang', '5', '15', '75.00', 'admin', '财务收到货款', '销售主管', '仓库主管', '本公司举办', null);

-- ----------------------------
-- Table structure for w_order_audit
-- ----------------------------
DROP TABLE IF EXISTS `w_order_audit`;
CREATE TABLE `w_order_audit` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_order_audit
-- ----------------------------
INSERT INTO `w_order_audit` VALUES ('1');
INSERT INTO `w_order_audit` VALUES ('2');
INSERT INTO `w_order_audit` VALUES ('3');
INSERT INTO `w_order_audit` VALUES ('4');
INSERT INTO `w_order_audit` VALUES ('5');

-- ----------------------------
-- Table structure for w_return_goods_audit
-- ----------------------------
DROP TABLE IF EXISTS `w_return_goods_audit`;
CREATE TABLE `w_return_goods_audit` (
  `ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_return_goods_audit
-- ----------------------------

-- ----------------------------
-- Table structure for w_return_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `w_return_goods_order`;
CREATE TABLE `w_return_goods_order` (
  `ID` varchar(255) NOT NULL,
  `GOODS_ID` varchar(255) NOT NULL,
  `GOODS_NAME` varchar(255) NOT NULL,
  `CUSTOMER` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL,
  `GOODS_AMOUNT` varchar(255) NOT NULL,
  `APPLY_USER` varchar(255) NOT NULL,
  `APPLY_TIME` datetime NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `AUDIT_USER` varchar(255) NOT NULL,
  `AUDIT_TYPE` varchar(255) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_return_goods_order
-- ----------------------------
INSERT INTO `w_return_goods_order` VALUES ('6248be0355bb4eb59fc0101be99a2e27', 'xs032544639093', 'da', 'sdf', 'we', 'da', 'fa', '2019-03-25 11:28:24', '等待审核', 'da', 'fs', '');
INSERT INTO `w_return_goods_order` VALUES ('d95aba650609499781d6e81be43601fc', 'xs032569394413', 'sa', '厦门有限公司', 'SD', '9845', '张三', '2019-03-25 10:16:57', '等待审核', 'sa', '4', '');

-- ----------------------------
-- Table structure for w_shipment_order
-- ----------------------------
DROP TABLE IF EXISTS `w_shipment_order`;
CREATE TABLE `w_shipment_order` (
  `ID` varchar(255) NOT NULL,
  `GOODS_ID` varchar(255) NOT NULL,
  `PRODUCT_ID` varchar(255) NOT NULL,
  `GOODS_NAME` varchar(255) NOT NULL,
  `CUSTOMER` varchar(255) NOT NULL,
  `GOODS_NUMBER` varchar(255) NOT NULL,
  `GOODS_AMOUNT` varchar(255) NOT NULL,
  `APPLY_USER` varchar(255) NOT NULL,
  `APPLY_TIME` datetime NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `AUDIT_USER` varchar(255) NOT NULL,
  `AUDIT_TYPE` varchar(255) NOT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_shipment_order
-- ----------------------------
INSERT INTO `w_shipment_order` VALUES ('8556a0f670444e23850ce017ca80653a', 'FH040429781385', '01612d22019f4f88af8686ceb7383938', '', '111', '1', '12', 'admin', '2019-04-04 20:56:33', '等待仓库审核', '销售主管', '1111', '111');
INSERT INTO `w_shipment_order` VALUES ('8c38ce38e8514b8580e0ac3b489e95e9', 'FH040417334723', '01612d22019f4f88af8686ceb7383938', '红色花岗石', '111', '12', '12', 'admin', '2019-04-04 13:05:42', '财务查收成功', '销售主管', '123', '');

-- ----------------------------
-- Procedure structure for prc_init_db
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_init_db`;
DELIMITER ;;
CREATE DEFINER=`stoneAdmin`@`%` PROCEDURE `prc_init_db`()
begin
   /*
   清空表
   */
   delete from   `sys_access_permission_test`;
   delete from   `sys_menu_role_test`;
   delete from   `sys_menu_test`;
   delete from   `sys_oper_log_test`;
   delete from   `sys_organize_test`;
   delete from   `sys_role_test`;
   delete from   `sys_user_role_test`;


   /*初始化数据-user_test*/
   insert into `user_test`(`ID`,`LOGIN_NAME`,`PASSWORD`,`REAL_NAME`,`IS_FORBIDDEN`,`MOBILE`,`ICON`,`ORG_ID`,`ORG_NAME`,`CREATE_DATE`,`UPDATE_DATE`)
values('123','gameloft9','7C4A8D09CA3762AF61E59520943DC26494F8941B','leiyao','0','13618629441',null,'gameloft9','总公司','2017-12-28 16:24:32','2017-12-28 16:32:57');

/*初始化数据-sys_access_permission_test*/
insert into `sys_access_permission_test`(`ID`,`URL`,`ROLES`,`SORT`,`IS_DELETED`,`CREATE_USER`,`CREATE_TIME`,`UPDATE_USER`,`UPDATE_TIME`) values
('c8c4e92a1ece4da79ede1763faecdf77','/sysUser/**','authc,roles[admin]','170','0','gameloft9','2017-12-27 17:05:49',null,'2017-12-27 17:05:49'),
('90762decb8874ba0955eb8cef21b6682','/menu/**','authc,roles[admin]','197','0','gameloft9','2017-12-28 11:02:26',null,'2017-12-28 11:02:26'),
('7ee176c20c904536862da37aacb0f7c8','/org/**','authc,roles[test]','123','0','gameloft9','2017-12-27 17:04:18',null,'2017-12-27 17:04:18'),
('7c1f21aa63cf4fd6a5be43072e81ab99','/test/**','authc,roleOr[test,admin]','135','0','gameloft9','2017-12-28 11:05:10',null,'2017-12-28 11:05:10'),
('7042e4eeebf5433388ddfdfb09764369','/log/**','authc,roleOr[test,admin]','419','0','gameloft9','2017-12-27 17:04:10',null,'2017-12-27 17:04:10'),
('6319f8a7688343139ac4ba0d40877e2c','/sys/**','authc,roleOr[test,admin]','201','0','gameloft9','2017-12-28 11:04:58',null,'2017-12-28 11:04:58'),
('6','/**/*.do','authc','999','0','admin','2017-12-25 16:41:09','admin','2017-12-25 16:41:09'),
('5','/getVCode','anon','4','0','admin','2017-12-25 16:41:09','admin','2017-12-25 16:41:09'),
('4','/role/**','authc,roles[admin]','5','0','admin','2017-12-25 16:41:08','admin','2017-12-25 16:41:08'),
('3','/login','anon','3','0','admin','2017-12-25 16:41:08','admin','2017-12-25 16:41:08'),
('2','/index','anon','2','0','admin','2017-12-25 16:41:08','admin','2017-12-25 16:41:08'),
('1','/','anon','1','0','admin','2017-12-25 16:41:02','admin','2017-12-25 16:41:02');

/*初始化数据-sys_menu_role_test*/
insert into `sys_menu_role_test`(`ID`,`MENU_ID`,`ROLE_ID`,`CREATE_USER`,`CREATE_TIME`,`UPDATE_USER`,`UPDATE_TIME`) values
('e92a68532e134b2e9b8af46a43ce61e0','1688998d013c4ebf95be6fd7e1fecb20','343859934ece44c988f53700fb34c80a',null,'2018-01-11 14:47:42',null,'2018-01-11 14:47:42'),
('c89cc73ffa1b40da943cb542b9b9819f','882b26afbdbb4d63bcba0cd826a5b9c1','J/F9-+?',null,'2017-12-27 17:04:10',null,'2017-12-27 17:04:10'),
('bd3b96f8742940e8acc628ff5c940465','b53bfdfe33444703aa76c2c1a1b8d820','J/F9-+?',null,'2017-12-27 17:04:03',null,'2017-12-27 17:04:03'),
('asdfr456yhbv123','4a7f3cae133e4a51b8f35769b55163dd','J/F9-+?',null,'2017-12-26 09:46:29',null,'2017-12-26 09:46:29'),
('874970f9c9b14021909485f201479373','1688998d013c4ebf95be6fd7e1fecb20','J/F9-+?',null,'2017-12-28 11:05:10',null,'2017-12-28 11:05:10'),
('71fa522e4da04ab9abd6008d2cb1c070','26b3df2c6d464a0b89858eb896b849d2','J/F9-+?',null,'2017-12-27 17:05:49',null,'2017-12-27 17:05:49'),
('6302f705514e4c839400f804ec29ec5c','89e9f1eb41a240fea361e0d188375884','343859934ece44c988f53700fb34c80a',null,'2018-01-11 14:46:55',null,'2018-01-11 14:46:55'),
('5b79d6e436154cfb9533a531fb7eb744','882b26afbdbb4d63bcba0cd826a5b9c1','343859934ece44c988f53700fb34c80a',null,'2018-01-11 14:47:16',null,'2018-01-11 14:47:16'),
('5905556900e741a89aafec25948b8fca','89e9f1eb41a240fea361e0d188375884','J/F9-+?',null,'2017-12-27 17:04:18',null,'2017-12-27 17:04:18'),
('4523b393aae64771aac4f91c5623599b','244247008b53450fa16bc503bc861b7c','343859934ece44c988f53700fb34c80a',null,'2018-01-11 14:47:33',null,'2018-01-11 14:47:33'),
('123456789ijhg','244247008b53450fa16bc503bc861b7c','J/F9-+?',null,'2017-12-26 09:46:03',null,'2017-12-26 09:46:03');

/*初始化数据-sys_menu_test*/
insert into `sys_menu_test`(`ID`,`TITLE`,`HREF`,`REQUEST_URL`,`ICON`,`CODE`,`TARGET`,`PARENT_ID`,`SORT`,`CREATE_USER`,`CREATE_TIME`,`UPDATE_USER`,`UPDATE_TIME`) values
('b53bfdfe33444703aa76c2c1a1b8d820','角色管理','page/system/sysRole/allRoles.html','/role/**','icon-text','1-3',null,'244247008b53450fa16bc503bc861b7c','2','gameloft9','2017-12-27 17:02:42',null,'2017-12-27 17:02:42'),
('89e9f1eb41a240fea361e0d188375884','机构管理','page/system/sysOrg/allOrgs.html','/org/**','icon-text','1-2',null,'244247008b53450fa16bc503bc861b7c','3','gameloft9','2017-12-27 17:01:58',null,'2017-12-27 17:01:58'),
('882b26afbdbb4d63bcba0cd826a5b9c1','系统日志管理','page/system/sysLog/allLogs.html','/log/**','icon-text','1-4',null,'244247008b53450fa16bc503bc861b7c','5','gameloft9','2017-12-27 17:03:41',null,'2017-12-27 17:03:41'),
('4a7f3cae133e4a51b8f35769b55163dd','菜单管理','page/system/sysMenu/allMenus.html','/menu/**','icon-text','1-1',null,'244247008b53450fa16bc503bc861b7c','1','gameloft9','2017-12-25 19:32:25',null,'2017-12-25 19:32:25'),
('26b3df2c6d464a0b89858eb896b849d2','系统用户管理','page/system/sysUser/allUsers.html','/sysUser/**','icon-text','1-5',null,'244247008b53450fa16bc503bc861b7c','4','gameloft9','2017-12-27 17:05:40',null,'2017-12-27 17:05:40'),
('244247008b53450fa16bc503bc861b7c','系统管理','#','/sys/**','icon-text','1',null,null,'1','gameloft9','2017-12-25 19:31:22',null,'2017-12-25 19:31:22'),
('1688998d013c4ebf95be6fd7e1fecb20','测试一级菜单','/memCard/index.do','test/**','icon-text','2',null,null,'2','gameloft9','2017-12-27 17:09:17',null,'2017-12-27 17:09:17');

/*初始化数据-sys_organize_test*/
insert into `sys_organize_test`(`ID`,`PARENT_ID`,`ORGANIZE_NAME`,`ORGANIZE_CODE`,`TREE_LEVEL`,`TREE_PATH`,`CREATE_DATE`,`UPDATE_DATE`) values
('54fa67f3d443427c9f47432ead20f2f4','21a6ff8a6abf410eaf97981c1da039d8','湖北分公司','010002','2','21a6ff8a6abf410eaf97981c1da039d8#54fa67f3d443427c9f47432ead20f2f4','2017-12-27 17:06:22','2017-12-27 17:06:22'),
('21a6ff8a6abf410eaf97981c1da039d8',null,'总公司','010001','1','21a6ff8a6abf410eaf97981c1da039d8','2017-12-27 17:06:04','2017-12-27 17:06:04');

/*初始化数据-sys_role_test*/
insert into `sys_role_test`(`ID`,`ROLE_NAME`,`IS_SUPER`,`IS_DELETED`,`CREATE_USER`,`CREATE_TIME`,`UPDATE_USER`,`UPDATE_TIME`) values
('J/F9-+?','admin','1','0','v[l_4zL8','2008-03-01 04:17:00',':9He2+GW','2002-09-27 03:13:16'),
('343859934ece44c988f53700fb34c80a','test','0','0','gameloft9','2018-01-11 14:46:22',null,'2018-01-11 14:46:22');

/*初始化数据-sys_user_role_test*/
insert into `sys_user_role_test`(`ID`,`USER_ID`,`ROLE_ID`) values
('2fb2a9fb965e462eb72c14361a83f006','123','J/F9-+?'),
('03f9afb1d42e49d6a026f537d9f033b6','9dbdc3a6cc444190bb5ac4e15df6234b','343859934ece44c988f53700fb34c80a');
   
end
;;
DELIMITER ;
