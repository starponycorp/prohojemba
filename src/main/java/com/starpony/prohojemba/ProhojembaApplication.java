package com.starpony.prohojemba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPooled;

@SpringBootApplication
public class ProhojembaApplication {
	/*
        Настройка пула подключений к Redis
     */
	@Bean
	public JedisPooled jedisPooledConnections(@Value("${redis.host}") String host, @Value("${redis.port}") int port) {
		return new JedisPooled(host, port);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProhojembaApplication.class, args);
	}
}
