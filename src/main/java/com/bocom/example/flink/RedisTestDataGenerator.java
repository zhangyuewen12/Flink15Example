package com.bocom.example.flink;

/**
 * RedisTestDataGenerator
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
import redis.clients.jedis.Jedis;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RedisTestDataGenerator {

    public static void main(String[] args) {
        // Redis 连接配置
        String redisHost = "localhost";
        int redisPort = 6379;

        // 创建 Redis 连接
        Jedis jedis = new Jedis(redisHost, redisPort);

        // 生成 100 条用户信息并存储到 Redis
        for (int i = 0; i < 100; i++) {
            String cardAccount = "card-" + i; // 卡号
            UserInfo userInfo = generateRandomUserInfo(); // 随机生成用户信息
            String jsonUserInfo = serializeUserInfoToJson(userInfo); // 序列化为 JSON

            // 存储到 Redis
            jedis.set(cardAccount, jsonUserInfo);
            System.out.println("Stored user info for card: " + cardAccount + " -> " + jsonUserInfo);
        }

        // 关闭 Redis 连接
        jedis.close();
    }

    // 生成随机的用户信息
    private static UserInfo generateRandomUserInfo() {
        Random random = new Random();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("user" + random.nextInt(1000)); // 随机用户名
        userInfo.setPhoneNumber("1" + String.format("%010d", random.nextInt(1000000000))); // 随机电话
        userInfo.setAddress(generateRandomAddress()); // 随机地址
        return userInfo;
    }

    // 生成随机地址
    private static String generateRandomAddress() {
        String[] streets = {"Main St", "First Ave", "Second St", "Park Ave", "Elm St"};
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        Random random = new Random();
        return random.nextInt(1000) + " " + streets[random.nextInt(streets.length)] + ", " +
                cities[random.nextInt(cities.length)] + ", " +
                "USA";
    }

    // 将 UserInfo 序列化为 JSON 字符串
    private static String serializeUserInfoToJson(UserInfo userInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(userInfo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize user info to JSON", e);
        }
    }

}
