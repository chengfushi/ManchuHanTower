# ManchuHanTower
## 需求分析
满汉楼项目主要分为两大模块，用户和管理员
### 用户模块
1. 用户首次使用项目需要进行注册账号和密码。
2. 用户能使用账号和密码进行登录。
3. 用户能够查看菜单。
4. 用户能够进行点单。
5. 用户能够查看自己的订单。
### 管理员模块
1. 管理员需要使用账号和密码进行登录。
2. 管理员能够修改用户的账号和密码。
3. 管理员能够修改菜单信息。
4. 管理员能够修改订单信息。

## 数据库设计
### 满汉楼主要信息表
满汉楼数据库设计主要有四张表
1. **用户信息表（user）**
	* id：自增
	* username：string
	* password：string
2. **管理员信息表（manager）**
	* id：自增
	* username：string
	* password
3. **菜品信息表（menu）**
	* id：自增
	* dishname：string
	* price：double
4. **订单信息表（order）**
	* id:自增
	* username：string
	* dishcount：int
	* price：double
	* time：time
```sql
-- 创建用户信息表
CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID，自增',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 创建管理员信息表
CREATE TABLE `manager` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '管理员ID，自增',
    `username` VARCHAR(50) NOT NULL COMMENT '管理员用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息表';

-- 创建菜品信息表
CREATE TABLE `menu` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '菜品ID，自增',
    `dishname` VARCHAR(100) NOT NULL COMMENT '菜品名称',
    `price` DECIMAL(10,2) NOT NULL COMMENT '菜品价格',
    PRIMARY KEY (`id`),
    KEY `idx_dishname` (`dishname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品信息表';

-- 创建订单信息表
CREATE TABLE `order` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '订单ID，自增',
    `username` VARCHAR(50) NOT NULL COMMENT '下单用户名',
    `dishcount` INT NOT NULL COMMENT '菜品数量',
    `price` DECIMAL(10,2) NOT NULL COMMENT '订单总价',
    `time` DATETIME NOT NULL COMMENT '下单时间',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`),
    KEY `idx_time` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';
```

### 数据库配置文件(mysql.properties)
```properties
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/mhl?serverTimezone=PRC
username=root
password=5200000scf
initialSize=10
minIdle=1
maxActive=10
maxWait=10000
timeBetweenEvictionRunsMillis=6000
minEvictableIdleTimeMillis=300000
testWhileIdle=true
testOnBorrow=true
testOnReturn=true
poolPreparedStatements=true
maxPoolPreparedStatementPerConnectionSize=20
validationQuery=select 1
filters=stat
```

## 项目环境配置
### maven
使用druid数据库连接池，使用dbutils数据库连接工具
### JDK
使用jdk24进行项目开发
