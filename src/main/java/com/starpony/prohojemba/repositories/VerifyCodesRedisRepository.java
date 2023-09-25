package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.enums.VerifyType;
import com.starpony.prohojemba.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;


@Repository
public class VerifyCodesRedisRepository implements VerifyCodesRepository{
    private final JedisPooled jedis;
    private final SetParams setParams;

    @Autowired
    public VerifyCodesRedisRepository(JedisPooled jedis,
                                      @Value("${email.verification.expiration}") long codeExpiration) {
        this.jedis = jedis;
        this.setParams = new SetParams();
        this.setParams.ex(codeExpiration);
    }


    @Override
    public void create(VerifyType verifyType, String email, String code) {
        String resp = jedis.set(
                String.format("%s:%s", verifyType, email),
                code, setParams);
        if (!resp.equals("OK"))
            throw new ServerException(String.format("Save verification code error. Redis response: %s", resp));
    }

    @Override
    public Optional<String> get(VerifyType verifyType, String email) {
        return Optional.ofNullable(jedis.get(String.format("%s:%s", verifyType, email)));
    }

    @Override
    public void delete(VerifyType verifyType, String email) {
        jedis.del(String.format("%s:%s", verifyType, email));
    }
}
