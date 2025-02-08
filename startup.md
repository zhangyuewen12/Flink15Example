## hadoop

```
1. 启动hdfs、yarn服务
start-dfs.sh
start-yarn.sh


3.启动端口服务
nc -lk 9999



flink_taskmanager_job_latency_source_id_operator_id _operator_subtask_index_latency


influx config create --config-name test \
    --host-url "http://localhost:8086" \
    --org "test_organization" \
    --token "T61DfD72Lu-O5dQdW2I0m3vArbq06wSkWNMzDT1kfc_KXMCODqBB3-nzUBjMCvcyUSGJYcBq4CHA8myFGRvkhw==" \
    --active
```



# influxdb

> ./influxd 启动服务器
>
> 运行地址： localhost:8086

```
metrics.reporter.influxdb.factory.class: com.bocom.rdss.metrics.influxdbv2.InfluxdbReporterFactory
metrics.reporter.influxdb.scheme: http
metrics.reporter.influxdb.host: localhost
metrics.reporter.influxdb.port: 8086
metrics.reporter.influxdb.db: rdss
metrics.reporter.influxdb.org: rdss
metrics.reporter.influxdb.bucket: flink
metrics.reporter.influxdb.username: admin
metrics.reporter.influxdb.password: 12345678
metrics.reporter.influxdb.token: e2h44hYF5gqlLYsNB8EyJXnKe0hlyXxG0h40SNHe3mkpqfOwyCqIKCm2ogT_x3sDO47u4av3F3pcfZykzdH5kg==
metrics.reporter.influxdb.retentionPolicy: one_hour
metrics.reporter.influxdb.consistency: ANY
metrics.reporter.influxdb.connectTimeout: 60000
metrics.reporter.influxdb.writeTimeout: 60000
metrics.reporter.influxdb.interval: 60 SECONDS


admin 12345678
rdss 
flink
Token e2h44hYF5gqlLYsNB8EyJXnKe0hlyXxG0h40SNHe3mkpqfOwyCqIKCm2ogT_x3sDO47u4av3F3pcfZykzdH5kg==


```





```
1. 登录12.68.87.65
2. mkdir /tmp/flinklogs
3. source /home/prfuser/init.sh
4. yarn logs -applicationId application_1722943214915_158324  -out /tmp/flinklogs/
5. cd /tmp
6. tar -cf flinklogs.tar  flinklogs/
7. 上传到服务器 
sftp -P8000  zhangyuewen@12.68.66.151
Dhu2018@
ps: 注意磁盘空间 
```

# kafka服务

```
要在Mac上安装Apache Kafka，你可以通过Homebrew包管理器来安装。Homebrew是一个macOS上用于安装和管理软件的工具，类似于Linux上的包管理器。以下是安装Apache Kafka的步骤：

步骤 1: 安装Homebrew

如果你还没有安装Homebrew，你可以通过打开终端（Terminal）并输入以下命令来安装它：

/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
步骤 2: 使用Homebrew安装Kafka

一旦你安装了Homebrew，你可以通过以下命令来安装Kafka：

brew install kafka
步骤 3: 验证Kafka安装

安装完成后，你可以通过运行以下命令来检查Kafka是否正确安装：

kafka --version

🍺  /opt/homebrew/Cellar/zookeeper/3.9.3: 1,193 files, 87.0MB
==> Installing kafka
==> Pouring kafka--3.9.0.arm64_sonoma.bottle.tar.gz
==> Caveats
To start kafka now and restart at login:
  brew services start kafka
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/kafka/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties
==> Summary
🍺  /opt/homebrew/Cellar/kafka/3.9.0: 230 files, 117.6MB
==> Running `brew cleanup kafka`...
Disable this behaviour by setting HOMEBREW_NO_INSTALL_CLEANUP.
Hide these hints with HOMEBREW_NO_ENV_HINTS (see `man brew`).
==> Upgrading 5 dependents of upgraded formulae:
Disable this behaviour by setting HOMEBREW_NO_INSTALLED_DEPENDENTS_CHECK.
Hide these hints with HOMEBREW_NO_ENV_HINTS (see `man brew`).

这应该会显示你安装的Kafka版本。

步骤 4: 启动Kafka服务

Kafka通常需要Zookeeper来运行，所以你需要先启动Zookeeper：

brew services start zookeeper

然后，你可以启动Kafka服务：

brew services start kafka
步骤 5: 测试Kafka是否运行正常

你可以通过创建一个topic并发送一些消息来测试Kafka是否运行正常：

打开一个新的终端窗口。

启动Kafka的控制台生产者：

kafka-console-producer --broker-list localhost:9092 --topic test

在另一个终端窗口中，启动Kafka的控制台消费者：

kafka-console-consumer --bootstrap-server localhost:9092 --topic test-transaction-events --from-beginning

在生产者终端中输入一些消息，你应该能在消费者终端看到这些消息。

通过以上步骤，你应该能够在你的Mac上成功安装并运行Apache Kafka。如果你遇到任何问题，检查网络连接或查看相关错误信息可能会有所帮助。

Upgrading from MySQL <8.4 to MySQL >9.0 requires running MySQL 8.4 first:
 - brew services stop mysql
 - brew install mysql@8.4
 - brew services start mysql@8.4
 - brew services stop mysql@8.4
 - brew services start mysql

We've installed your MySQL database without a root password. To secure it run:
    mysql_secure_installation

MySQL is configured to only allow connections from localhost by default

To connect run:
    mysql -u root

To restart mysql after an upgrade:
  brew services restart mysql
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/mysql/bin/mysqld_safe --datadir\=/opt/homebrew/var/mysql
  

创建topic

kafka-topics --create --topic quickstart-events --bootstrap-server localhost:9092

kafka-topics --create --topic test-transaction-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
解释：

<topic_name>：你希望创建的 topic 的名称。
<kafka_broker>：Kafka broker 的地址（例如 localhost:9092）。
--partitions：指定 topic 的分区数，通常根据数据量和并发处理能力设置。
--replication-factor：设置副本因子，表示数据的副本数量。通常设置为 1（单机环境）或 3（生产环境）
```



# grafana服务

```
To start grafana now and restart at login:
  brew services start grafana
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/grafana/bin/grafana server --config /opt/homebrew/etc/grafana/grafana.ini --homepath /opt/homebrew/opt/grafana/share/grafana --packaging\=brew cfg:default.paths.logs\=/opt/homebrew/var/log/grafana cfg:default.paths.data\=/opt/homebrew/var/lib/grafana cfg:default.paths.plugins\=/opt/homebrew/var/lib/grafana/plugins
```

## 配置influxdb数据源

> 增加：header "Authorization: Token e2h44hYF5gqlLYsNB8EyJXnKe0hlyXxG0h40SNHe3mkpqfOwyCqIKCm2ogT_x3sDO47u4av3F3pcfZykzdH5kg=="
>

## redis 服务区
```shell
==> Caveats
To start redis now and restart at login:
  brew services start redis
Or, if you don't want/need a background service you can just run:
  /opt/homebrew/opt/redis/bin/redis-server /opt/homebrew/etc/redis.conf
```

## flink配置
```shell
metrics.reporter.promgateway.factory.class: org.apache.flink.metrics.prometheus.PrometheusPushGatewayReporterFactory
metrics.reporter.promgateway.hostUrl: http://localhost:9091
metrics.reporter.promgateway.jobName: myJob
metrics.reporter.promgateway.randomJobNameSuffix: true
metrics.reporter.promgateway.deleteOnShutdown: false
metrics.reporter.promgateway.groupingKey: k1=v1;k2=v2
metrics.reporter.promgateway.interval: 60 SECONDS

启动flink作业
./bin/flink run-application -t yarn-application -c com.bocom.example.flink.KafkaTransactionProcessor -Dyarn.provided.lib.dirs="hdfs://localhost:9000/dcpp/flink/lib/"   /Users/ywzhang/github_project/Flink15Example/target/Flink15Example-1.0-SNAPSHOT.jar

```

## pushgateway
```shell
cd hadoopadpp
./pushgateway
```

## prometheus
```shell
./prometheus --config.file="prometheus.yml" 
```


## grafana
