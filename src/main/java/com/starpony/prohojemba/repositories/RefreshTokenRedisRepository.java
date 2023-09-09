package com.starpony.prohojemba.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.params.SetParams;

public class RefreshTokenRedisRepository implements RefreshTokenRepository{
    private final JedisPooled jedis;
    private final SetParams params;

    @Autowired
    public RefreshTokenRedisRepository(JedisPooled jedis,
                                       @Value("${jwt.refresh-token.expiration}") long tokenExpiration) {
        this.jedis = jedis;
        this.params = new SetParams();
        this.params.ex(tokenExpiration);
    }

    @Override
    public void create(String refreshToken, int accountId) {
        jedis.set(refreshToken, String.valueOf(accountId), params);
    }

    @Override
    public int get(String refreshToken) {
        return Integer.parseInt(jedis.get(refreshToken));
    }

    @Override
    public void delete(String refreshToken) {
        jedis.del(refreshToken);
        System.out.printf("1");
    }
}
