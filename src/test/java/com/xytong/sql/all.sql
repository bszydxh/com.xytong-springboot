SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_fkey` int NOT NULL,
  `likes` int NULL DEFAULT NULL,
  `comments` int NULL DEFAULT NULL,
  `forwarding` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `text` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `user_fkey`(`user_fkey`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (1, 2, 1, 1, 1, 'test1', 'test1', '2022-09-03 00:55:11');
INSERT INTO `forum` VALUES (2, 1, 2, 2, 2, 'test2', 'test2', '2022-09-03 18:57:30');
INSERT INTO `forum` VALUES (3, 1, 3, 3, 3, 'test3', 'test3', '2022-09-03 22:52:07');
INSERT INTO `forum` VALUES (4, 2, 4, 4, 4, 'test4', 'test4', '2022-09-03 22:53:35');
INSERT INTO `forum` VALUES (5, 1, 5, 5, 5, 'test5', 'test5', '2022-09-03 22:54:11');

-- ----------------------------
-- Table structure for re
-- ----------------------------
DROP TABLE IF EXISTS `re`;
CREATE TABLE `re`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_fkey` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `text` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of re
-- ----------------------------
INSERT INTO `re` VALUES (1, 2, 'test1', 'test1', '2022-09-03 00:55:11', 1.11);
INSERT INTO `re` VALUES (2, 1, 'test2', 'test2', '2022-09-03 18:57:30', 2.22);
INSERT INTO `re` VALUES (3, 1, 'test3', 'test3', '2022-09-03 22:52:07', 3.33);
INSERT INTO `re` VALUES (4, 2, 'test4', 'test4', '2022-09-03 22:53:35', 4.44);
INSERT INTO `re` VALUES (5, 1, 'test5', 'test5', '2022-09-03 22:54:11', 5.55);

-- ----------------------------
-- Table structure for sh
-- ----------------------------
DROP TABLE IF EXISTS `sh`;
CREATE TABLE `sh`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_fkey` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `text` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sh
-- ----------------------------
INSERT INTO `sh` VALUES (1, 2, 'test1', 'test1', '2022-09-03 00:55:11', 1.11);
INSERT INTO `sh` VALUES (2, 1, 'test2', 'test2', '2022-09-03 18:57:30', 2.22);
INSERT INTO `sh` VALUES (3, 1, 'test3', 'test3', '2022-09-03 22:52:07', 3.33);
INSERT INTO `sh` VALUES (4, 2, 'test4', 'test4', '2022-09-03 22:53:35', 4.44);
INSERT INTO `sh` VALUES (5, 1, 'test5', 'test5', '2022-09-03 22:54:11', 5.55);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_timestamp` timestamp NOT NULL,
  `is_admin` enum('N','Y') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` enum('unknown','male','female') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'unknown',
  `birthday_timestamp` timestamp NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`, `name`, `phone`) USING BTREE,
  INDEX `id`(`id`, `create_timestamp`, `name`, `gender`, `birthday_timestamp`, `phone`, `email`) USING BTREE,
  INDEX `id_2`(`id`, `name`) USING BTREE,
  INDEX `id_3`(`id`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2022-09-03 00:41:36', 'N', 'bszydxh', 'https://qlogo4.store.qq.com/qzone/2733879291/2733879291/100?1656158377', '在知识的海洋随波逐流', 'male', '2022-02-03 00:41:48', '18243998035', '2733879291@qq.com', '227695cd8ea3b7194e9c2cbd9eba4342');
INSERT INTO `user` VALUES (2, '2022-09-03 22:58:33', 'N', 'xzx', 'https://qlogo1.store.qq.com/qzone/1472135712/1472135712/100?1624526244', '索尼大法好', 'male', NULL, '18605586653', '147235712@qq.com', '8eab34801b8f05644302ecacb5eadc49');
INSERT INTO `user` VALUES (3, '2022-09-26 16:35:27', 'N', 'xzx2', 'https://qlogo1.store.qq.com/qzone/1472135712/1472135712/100?1624526244', NULL, 'female', '2022-09-26 16:34:47', '13630773737', '1234123411@qq.com', '456');

SET FOREIGN_KEY_CHECKS = 1;
