# 新建数据库

```sql
CREATE DATABASE uba;
```

# 新建 AccessLog 日志表，用于原始数据
```sql
CREATE TABLE uba.access_log
(
    `type` String,
    `request_id` String,
    `connection` UInt64,
    `timestamp` DateTime64(3),
    `server_name` LowCardinality(String),
    `remote_addr` String,
    `request` String,
    `status` UInt16,
    `body_bytes_sent` UInt64,
    `http_referer` String,
    `http_user_agent` String,
    `request_time` Float64,
    `upstream_response_time` Nullable(String),
    `scheme` LowCardinality(String),
    `server_protocol` LowCardinality(String),
    `request_method` LowCardinality(String),
    `request_uri` String,
    `query_string` String,
    `http_host` String,
    `http_accept_language` String,
    `http_x_forwarded_for` String,
    `http_x_request_id` String,
    `http_cookie` String,
    `cookie_session` String,
    `date` Date MATERIALIZED toDate(timestamp),
    INDEX idx_request_id http_x_request_id TYPE bloom_filter GRANULARITY 3,
    INDEX idx_cookie_session cookie_session TYPE bloom_filter GRANULARITY 3,
    INDEX idx_status status TYPE set(0) GRANULARITY 2,
    INDEX idx_ip remote_addr TYPE bloom_filter GRANULARITY 3,
    INDEX idx_user_agent http_user_agent TYPE ngrambf_v1(4,5120,2,0) GRANULARITY 4,
    INDEX idx_request_uri request_uri TYPE tokenbf_v1(32768,3,0) GRANULARITY 4,
    INDEX idx_timestamp timestamp TYPE minmax GRANULARITY 1
)
ENGINE =  MergeTree()
PARTITION BY toYYYYMM(timestamp)
ORDER BY (timestamp, server_name, status, request_method)
TTL timestamp + toIntervalMonth(3)
SETTINGS index_granularity = 8192,
 min_bytes_for_wide_part = 1073741824;
```
