<div align=center><img src="./doc/logo.png"></div>

#  <center> 见微 (JianWei) - 用户行为分析系统

[cn 简体中文](#)

![GitHub Release](https://img.shields.io/github/v/release/Foleyzhao/UBA)
![GitHub Repo stars](https://img.shields.io/github/stars/Foleyzhao/UBA)
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/Foleyzhao/UBA/total?color=orange)
![GitHub License](https://img.shields.io/github/license/Foleyzhao/UBA?color=green)
[![Static Badge](https://img.shields.io/badge/Author-HappyNewYear-white)](https://github.com/Foleyzhao)


<details>
<summary>📁 目录</summary>

- [📦 系统概述](#-系统概述)
- [💡 核心特性](#-核心特性)
- [🔩 技术架构](#-技术架构)
- [⚡ 快速开始](#-快速开始)
- [⚙️ 使用指南](#-使用指南)
- [📇 API文档](#-API文档)
- [📋 许可证](#-许可证)
- [📜 获取帮助](#-获取帮助)
- [🙌 感谢名单](#-感谢名单)

</details>

---

## 📦 系统概述

&emsp;&emsp;"**见微**"（JianWei）是一款专业的企业级用户行为分析系统，取名自成语"见微知著"，寓意通过细微的用户行为洞察宏观趋势和深层原因。

&emsp;&emsp;系统可无缝接入现有业务系统，从采集用户数据到基于海量用户行为数据进行数据过滤、增强、清洗、分析及结构化，帮助企业完成用户运营指标体系从设计到落地的全过程。

---

## 💡 核心特性

### 🔍 全方位数据采集

多端支持: Web、APP、小程序、H5等多端用户行为自动采集

无侵入集成: 提供agent、SDK、API等多种接入方式，不影响现有业务系统

自定义事件: 支持业务自定义事件和属性采集

### 🧩 智能数据处理

实时数据管道: 基于Vector+Kafka+ClickHouse的实时数据处理流水线

数据增强: 自动补全用户属性、设备信息和地理位置

数据清洗: 智能识别和过滤无效数据与异常流量

### 📊 深度分析洞察

用户分群: 基于行为特征的用户细分与画像构建

路径分析: 可视化用户行为路径与转化漏斗

留存分析: 多维度用户活跃与留存情况追踪

事件分析: 自定义指标与多维下钻分析

### 🎯 运营体系支撑

指标体系: 可视化配置业务关键指标(KPI)

预警机制: 关键指标异常波动实时告警

报表中心: 自动化多维度数据报表生成

API开放: 全面数据接口支持二次开发

---

## 🔩 技术架构

TODO

---

## ⚡ 快速开始

### 环境要求

* Java 17+
* MySQL 8.0+
* Redis 7.0+
* Kafka 3.6.0+
* ClickHouse 25.7.0.0+
* Vector 0.49.0 (用于网关访问日志采集)

### 安装部署

1. 克隆项目

   ```bash
   git clone https://github.com/Foleyzhao/UBA.git
   
   cd UBA
   ```

2. Mysql 数据库初始化

   ```bash
   mysql -u root -p < web-app/src/main/resources/_sql/sys.sql
   
   mysql -u root -p < web-app/src/main/resources/_sql/uba.sql
   ```
   
3. ClickHouse 数据库初始化

   ```bash
   clickhouse-client --user 用户名 --password 密码 -d uba --multiquery < web-app/src/main/resources/_sql/clickhouse.sql
   ```

4. 配置修改

   修改配置文件 web-app/src/main/resources/application.yaml 设置 mysql/clickhouse/redis/kafka 等连接信息：
   ```yaml
   spring:
      datasource:
         dynamic:
            datasource:
               master:
                  driver-class-name: com.mysql.cj.jdbc.Driver
                  url: jdbc:mysql://【mysql地址】:【mysql端口】/【mysql数据库】?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true&useInformationSchema=true&rewriteBatchedStatements=true
                  username: 【mysql账号】
                  password: 【mysql密码】
               clickhouse:
                  driver-class-name: com.clickhouse.jdbc.ClickHouseDriver
                  url: jdbc:ch://【clickhouse地址】:【clickhouse地址】/【clickhouse数据库】?use_timezone=Asia/Shanghai
                  username: 【clickhouse账号】
                  password: 【clickhouse密码】
   data:
      redis:
         database: 【redis数据库】
         host: 【redis地址】
         port: 【redis端口】
         password: 【redis密码】
   kafka:
      bootstrap-servers: 【kafka地址】
   ```

5. 启动后端服务

   启动类：web-app/src/main/java/com/huanniankj/Application.java

6. 启动前端服务 

   ```bash
   cd uba-admin-front
   
   npm install
   
   npm run dev
   ```

7. 访问系统

   打开浏览器访问：http://localhost:81/

   默认管理员账号: superAdmin

   默认密码: superAdmin

###  数据接入

TODO

---

## ⚙️ 使用指南

TODO

| 内容             | 说明文档   | 备注   |
|-----------------|--------|------|
| `Nginx配置`      |        |      |
| `Vector配置`     |        |      |
| `ClickHouse配置` |        |      |
| `Kafka配置`      |        |      |

---

## 📇 API文档

启动项目后访问：http://localhost:82/doc.html

---

## 📋 许可证

见微用户行为分析系统基于 Apache License 2.0 开源协议发布。

---

## 📜 获取帮助

📟 官网： https://www.huanniankj.com

📌 知识库: https://ocn7i6tb5e7q.feishu.cn/wiki/LaU2wBbhnie7egkHvMscauPNnSn?from=from_copylink

📧 商务合作: foleyzhao@163.com

---

## 🙌 感谢名单

感谢所有为见微项目做出贡献的开发者们

<a href="https://github.com/Foleyzhao/UBA/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Foleyzhao/UBA" />
</a>

