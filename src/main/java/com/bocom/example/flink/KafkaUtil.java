package com.bocom.example.flink;

/**
 * KafkaTranstionDataProducerUtil
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;
import java.util.Random;

public class KafkaUtil {

    public static void main(String[] args) throws InterruptedException {
        // Kafka 配置
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", StringSerializer.class.getName());
        kafkaProps.put("value.serializer", StringSerializer.class.getName());

        // 创建 Kafka 生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        // 生成 100 条测试数据并发送到 Kafka
        for (int i = 0; i < 1000; i++) {
            TransactionRecord transaction = generateRandomTransaction();
            String jsonTransaction = serializeTransactionToJson(transaction);

            // 发送消息到 Kafka
            ProducerRecord<String, String> record = new ProducerRecord<>("transaction-topic", jsonTransaction);
            producer.send(record);
            Thread.sleep(3000);
            System.out.println("Sent transaction: " + jsonTransaction);
        }

        // 关闭 Kafka 生产者
        producer.close();
    }

    // 生成随机的 TransactionRecord
    private static TransactionRecord generateRandomTransaction() {
        Random random = new Random();
        TransactionRecord transaction = new TransactionRecord();
        transaction.setCardAccount("card-" + random.nextInt(100)); // 随机生成卡账号
        transaction.setAmount(random.nextDouble() * 1000 - 500); // 随机生成交易金额（可能为负数）
        return transaction;
    }

    // 将 TransactionRecord 序列化为 JSON 字符串
    private static String serializeTransactionToJson(TransactionRecord transaction) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize transaction to JSON", e);
        }
    }
}
