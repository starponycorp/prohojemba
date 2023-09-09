package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;


@Repository
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
        String resp = jedis.set(refreshToken, String.valueOf(accountId), params);
        if (!resp.equals("OK"))
            throw new ServerException(String.format("Save refresh token error. Redis response: %s", resp));
    }

    @Override
    public int get(String refreshToken) {
        try {
            return Integer.parseInt(jedis.get(refreshToken));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    @Override
    public void delete(String refreshToken) {
        jedis.del(refreshToken);
    }
}
