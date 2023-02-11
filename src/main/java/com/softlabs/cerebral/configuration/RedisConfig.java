package com.softlabs.cerebral.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Postgres is a relational database management system and has its own client-server architecture where the client can directly connect to the database server using a JDBC connection.
 * When you add the dependency for Postgres in your project, it automatically takes care of connecting to the database server using the
 * configuration specified in the application.properties file.
 *
 * On the other hand, Redis is an in-memory data structure store and does not have a client-server architecture.
 * It uses a publish-subscribe mechanism to communicate with clients.
 * To connect to Redis from a Spring Boot application, we need to define a RedisTemplate bean in a configuration class.
 * The RedisTemplate bean is used to communicate with Redis and to store and retrieve data from it.
 *
 * Hence, to use Redis in a Spring Boot application, you need to add the required configuration in the application.properties
 * file and also define a RedisTemplate bean in a configuration class to connect to Redis.
 *
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}

