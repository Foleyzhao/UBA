# Vector
## 简介
Vector 是一个高性能的可观测性数据管道工具，比 Filebeat 更灵活且性能更高，适合采集和处理 Nginx 日志等。

* 官网：https://vector.dev/

## 安装

###  添加 Vector 官方仓库
```bash
bash -c "$(curl -L https://setup.vector.dev)"
```

### 安装Vector
```bash
# unbuntu 安装
apt-get install vector

# centos 安装
yum install vector
```

### 验证安装
```bash
# 查看 vector 版本
vector --version
```

## 配置

* Vector 采集 Nginx 日志

### 编辑配置文件
<details>
<summary>编辑 /etc/vector/vector.yaml 文件</summary>
<pre><code>
   sources:
     agent_os:
       type: exec
       command: ["/bin/sh", "-c", "echo -n $(uname -s -r -m); echo \",$(lsb_release -ds 2>/dev/null || cat /etc/*release 2>/dev/null | head -n1 || echo 'Unknown OS')\""]
       mode: scheduled # oneshot
       scheduled:
         exec_interval_secs: 2592000
     agent_host_ip:
       type: exec
       command: ["/bin/sh", "-c", "hostname -I"]
       mode: scheduled
       scheduled:
         exec_interval_secs: 2592000
     agent_public_ip:
       type: exec
       command: ["/bin/sh", "-c", "curl -s --connect-timeout 2 https://ifconfig.me/ip"]
       mode: scheduled
       scheduled:
         exec_interval_secs: 2592000
     heartbeat:
       type: static_metrics
       interval_secs: 60
       metrics:
         - name: heartbeat
           kind: absolute
           value:
             gauge:
               value: 1
           tags:
             agent_id: "【uuid】"
     access_log:
       type: file
       include: 
         - "【log_dir】"
       read_from: end
       ignore_older: 86400 # 忽略超过1天的文件
       fingerprint:
         strategy: device_and_inode
   transforms:
     agent_os_add_tags:
       type: remap
       inputs: 
         - agent_os
       source: |
         .tags.agent_id = "【uuid】"
         .tags.config_name = "os"
     agent_host_ip_add_tags:
       type: remap
       inputs: 
         - agent_host_ip
       source: |
         .tags.agent_id = "【uuid】"
         .tags.config_name = "host_ip"
     agent_public_ip_add_tags:
       type: remap
       inputs: 
         - agent_public_ip
       source: |
         .tags.agent_id = "【uuid】"
         .tags.config_name = "public_ip"
     access_log_parser:
       type: remap
       inputs:
         - access_log
       source: |
         . = parse_json!(.message)
          .timestamp = to_unix_timestamp(parse_timestamp!(.timestamp, format: "%+")) * 1000
          .type = "raw"
   sinks:
     print:
       type: "console"
       inputs:
         - agent_os_add_tags
         - agent_host_ip_add_tags
         - agent_public_ip_add_tags
       encoding:
         codec: "json"
         json:
           pretty: true
     report_http:
       type: http
       inputs: 
         - agent_os_add_tags
         - agent_host_ip_add_tags
         - agent_public_ip_add_tags
       uri: "http://【backend-addr】/uba/agent/report"
       encoding:
         codec: json
       request:
         timeout_secs: 100
         retry_attempts: 3
     heartbeat_http:
       type: http
       inputs: 
         - heartbeat
       uri: "http://【backend-addr】/uba/agent/heartbeat"
       encoding:
         codec: json
       request:
         timeout_secs: 10
         retry_attempts: 3
     access_log_kafka:
       type: "kafka"
       inputs:
         - access_log_parser
       bootstrap_servers: "【kafka-addr】"
       topic: "raw_log"
       encoding:
         codec: json
   api:
     enabled: true
     address: "0.0.0.0:8686"
</code></pre>
</details>

其中需要替换变量

* 【uuid】：agent编号
* 【log_dir】：采集日志路径
* 【backend-addr】：见微UBA后端服务地址
* 【kafka-addr】：Kafka地址

## 验证配置文件

### 校验/测试 vector 配置文件

```bash
vector validate --config-yaml /etc/vector/vector.yaml
vector test --config-yaml /etc/vector/vector.yaml
```

## 启动

```bash
# 启动 Vector 服务
systemctl start vector

# 设置 Vector 开机启动
systemctl enable vector

# 查看 Vector 服务状态
systemctl status vector
```

## 调试

```bash
# 查看日志（如有问题）或调试使用
journalctl -u vector -f
```

## 其他（可选）

### 性能调优

```yaml
# 在 sources 部分添加缓冲和性能配置
sources:
  nginx_access:
    type: file
    # ...其他配置...
    max_line_bytes: 102400 # 最大行大小
    buffer:
      type: memory
      max_events: 1000
      when_full: drop_newest

# 全局性能配置
api:
  enabled: true
  address: 127.0.0.1:8686

buffer:
  type: disk
  max_size: 104900000 # 100MB
  when_full: block
```
