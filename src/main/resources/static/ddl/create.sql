-- データベースの作成
CREATE DATABASE betelgeuse;

-- データベースの確認
SHOW DATABASES;

-- データベースの切り替え
USE betelgeuse;

-- ## テーブルの作成 ##

-- 会員情報テーブルの削除
DROP TABLE t_members;

-- 会員情報テーブルの作成
CREATE TABLE t_members(
member_id TINYINT(8) ZEROFILL NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '会員ID',
name_sei VARCHAR(5) NOT NULL COMMENT '会員名_姓',
name_mei VARCHAR(10) NOT NULL COMMENT '会員名_名',
kana_sei VARCHAR(6) NOT NULL COMMENT '会員名_姓_かな',
kana_mei VARCHAR(16) NOT NULL COMMENT '会員名_名_かな',
sex VARCHAR(1) COMMENT '性別',
age TINYINT(3) NOT NULL COMMENT '年齢',
birthday DATETIME COMMENT '生年月日',
tel VARCHAR(13) COMMENT '電話番号',
mail VARCHAR(255) NOT NULL COMMENT 'メールアドレス',
address VARCHAR(255) COMMENT '住所',
password TEXT NOT NULL COMMENT 'パスワード',
limit_day DATETIME NOT NULL COMMENT '有効期限日',
secret_cd TINYINT(2) NOT NULL DEFAULT 1 COMMENT '秘密の質問CD',
cold_flg TINYINT(1) NOT NULL DEFAULT 0 COMMENT '会員情報凍結フラグ',
limit_flg TINYINT(1) NOT NULL DEFAULT 0 COMMENT '有効期限フラグ',
delete_flg TINYINT(1) NOT NULL DEFAULT 0 COMMENT '削除フラグ',
create_user VARCHAR(16) COMMENT '作成者',
create_date DATETIME COMMENT '作成日',
update_user VARCHAR(16) COMMENT '更新者',
update_date  DATETIME COMMENT '更新日') COMMENT='会員情報';

-- 権限マスタテーブルの削除
DROP TABLE m_auth;

-- 権限マスタテーブルの作成
CREATE TABLE m_auth(
auth_id TINYINT(1) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '権限ID',
auth_name VARCHAR(255) NOT NULL COMMENT '権限名',
delete_flg TINYINT(1) NOT NULL DEFAULT 0 COMMENT '削除フラグ',
create_user VARCHAR(16) COMMENT '作成者',
create_date DATETIME COMMENT '作成日',
update_user VARCHAR(16) COMMENT '更新者',
update_date  DATETIME COMMENT '更新日') COMMENT='権限マスタ';

-- 会員権限テーブルの削除
DROP TABLE t_member_auth;

-- 会員権限テーブルの作成
CREATE TABLE t_member_auth(
member_id  TINYINT(8) ZEROFILL NOT NULL COMMENT '会員ID',
auth_id TINYINT(1) NOT NULL COMMENT '権限ID',
create_user VARCHAR(16) COMMENT '作成者',
create_date DATETIME COMMENT '作成日',
update_user VARCHAR(16) COMMENT '更新者',
update_date  DATETIME COMMENT '更新日',
PRIMARY KEY(member_id, auth_id)) COMMENT='会員権限';

-- ## データ作成用 ##

-- マップテーブルデータ追加(本来なら会員登録処理に盛り込むべき)
INSERT INTO `betelgeuse`.`t_member_auth`
(`member_id`,
`auth_id`,
`create_user`,
`create_date`)
VALUES
(00000001,
1,
'システム管理者',
now());
INSERT INTO `betelgeuse`.`t_member_auth`
(`member_id`,
`auth_id`,
`create_user`,
`create_date`)
VALUES
(00000001,
2,
'システム管理者',
now());
-- 権限マスタデータ追加
INSERT INTO `betelgeuse`.`m_auth`
(`auth_name`,
`create_user`,
`create_date`)
VALUES
('管理者',
'システム管理者',
now()
);
INSERT INTO `betelgeuse`.`m_auth`
(`auth_name`,
`create_user`,
`create_date`)
VALUES
('一般会員',
'システム管理者',
now()
);