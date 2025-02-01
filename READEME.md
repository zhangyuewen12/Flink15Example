## deltaStream开发工具
##  启动kafka
```shell
brew services start zookeeper
# To start kafka now and restart at login:
  brew services start kafka
# Or, if you don't want/need a background service you can just run:
  /usr/local/opt/kafka/bin/kafka-server-start /usr/local/etc/kafka/server.properties
```


## 启动kafdrop
```shell
/Users/zyw/software/java17/Contents/Home/bin/java \
 --add-opens=java.base/sun.nio.ch=ALL-UNNAMED \ 
 -jar kafdrop-4.1.0.jar --kafka.brokerConnect=localhost:9092
```
## 启动redis
```shell
# To start redis now and restart at login:
  brew services start redis
# Or, if you don't want/need a background service you can just run:
  /usr/local/opt/redis/bin/redis-server /usr/local/etc/redis.conf
```

## influxdb
```shell
# ==> Pouring influxdb-2.7.11.sonoma.bottle.tar.gz
# ==> Caveats
# This formula does not contain command-line interface; to install it, run:
  brew install influxdb-cli

# To start influxdb now and restart at login:
  brew services start influxdb
# Or, if you don't want/need a background service you can just run:
  INFLUXD_CONFIG_PATH="/usr/local/etc/influxdb2/config.yml"/usr/local/opt/influxdb/bin/influxd
  
```
## 启动flink命令
```shell
./bin/flink run-application -t yarn-application ./examples/streaming/TopSpeedWindowing.jar
```