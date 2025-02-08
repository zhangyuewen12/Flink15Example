package com.bocom.example.flink;

/**
 * KafkaTransactionProcessor
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
import com.bocom.example.flink.model.EnrichedTransaction;
import com.bocom.example.flink.model.TransactionRecord;
import com.bocom.example.flink.model.UserInfo;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import redis.clients.jedis.Jedis;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

public class KafkaTransactionProcessor {

    public static void main(String[] args) throws Exception {
        // 设置 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getCheckpointConfig().setCheckpointInterval(1000*60);
        env.getCheckpointConfig().setCheckpointTimeout(1000*60*10);
        env.getCheckpointConfig().setCheckpointStorage("hdfs://localhost:9000/flink/checkpoint/");

        // Kafka 消费者配置
        Properties kafkaConsumerProps = new Properties();
        kafkaConsumerProps.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaConsumerProps.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flink-consumer-group");
        kafkaConsumerProps.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaConsumerProps.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        // 创建 Kafka 消费者
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>("transaction-topic", new SimpleStringSchema(), kafkaConsumerProps);

        // 从 Kafka 中读取数据流
        DataStream<String> transactionStream = env.addSource(kafkaConsumer);

        // 将 JSON 字符串转换为 TransactionRecord 对象
        DataStream<TransactionRecord> transactionRecords = transactionStream.map(new MapFunction<String, TransactionRecord>() {
            @Override
            public TransactionRecord map(String value) throws Exception {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(value, TransactionRecord.class);
            }
        });

        // 过滤掉交易金额小于 0 的记录
        DataStream<TransactionRecord> filteredTransactions = transactionRecords.filter(new FilterFunction<TransactionRecord>() {
            @Override
            public boolean filter(TransactionRecord transaction) throws Exception {
                return transaction.getAmount() >= 0;
            }
        });


        // 根据卡账号查询 Redis 中的账户信息
        DataStream<String> enrichedTransactions = filteredTransactions.map(new RichMapFunction<TransactionRecord, String>() {
            Jedis jedis = null;

            @Override
            public void open(Configuration parameters) throws Exception {
                jedis = new Jedis("localhost", 6379);
            }

            @Override
            public String map(TransactionRecord transaction) throws Exception {
                String userInfoJson = jedis.get(transaction.getCardAccount());

                ObjectMapper objectMapper = new ObjectMapper();
                UserInfo userInfo = objectMapper.readValue(userInfoJson, UserInfo.class);

                // 将交易记录和账户信息合并为一个 JSON 字符串
                return objectMapper.writeValueAsString(new EnrichedTransaction(transaction, userInfo));
            }

            @Override
            public void close() throws Exception {
                jedis.close();
            }
        });

        // Kafka 生产者配置

        KafkaSink<String> sink = KafkaSink.<String>builder()
                .setBootstrapServers("localhost:9092")
                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
                        .setTopic("enriched-transaction-topic")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build()
                )
                .build();
        // 将结果写入 Kafka
//        enrichedTransactions.sinkTo(sink);
        enrichedTransactions.print();

        // 执行 Flink 程序
        env.execute("Kafka Transaction Processor");
    }
}
