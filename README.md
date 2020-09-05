# 一个在24小时内写成的酒店客房管理系统

## 启动配置

- 依赖安装
  - 使用 Maven 严格按照 pom.xml 安装依赖
  - 手动添加 `/lib/IKAnalyzer2012FF_u1.jar` 到构建
- 配置 MySQL
  - 将 `applicationExample.yml` 重命名为 `application.yml`
  - 修改其中的 MySQL 地址
  - MySQL 表结构请参考 `/sql/hotel.sql`
- 配置 Hadoop
  - Hadoop 集群规划策略请参考 `/hadoop_etc/hadoop集群规划.txt`
  - Hadoop 配置文件请参考 `/hadoop_etc/etc`
  - 修改 `src/main/resources/config/hadoop.properties` 中的 HDFS 和 namenode 配置
  - 修改 `CommentServiceImpl` 类 `analyzeComment` 方法下的日志输出路径、Hadoop Example 路径
- 访问 `http://localhost:8080`

## 主要功能

- 员工管理模块
  - 登录
  - 员工新增、删除等
  - 退出登录
- 预定管理模块
  - 新增房间预订
  - 预定列表
- 房间管理模块
  - 新增房间
  - 房间列表
- 入住/退房管理模块
  - 入住办理
  - 退房办理
- 评论管理模块
  - 评论新增
  - 评论列表
  - 基于 Hadoop + Wordcount 的顾客评价分析系统

## 可供测试的帐号密码

如果你拥有 `/sql/hotel_with_data.sql` 或可以连接我们提供的远端数据库，则可以使用如下账密测试：

- 普通员工：帐123，密321
- Admin帐户：帐admin，密admin

## 联系

https://github.com/z0gSh1u/hotel-24h