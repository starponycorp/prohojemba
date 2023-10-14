package com.starpony.prohojemba.verification.repositories.impl;

import com.starpony.prohojemba.verification.models.Verification;
import com.starpony.prohojemba.verification.models.VerificationType;
import com.starpony.prohojemba.verification.repositories.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.Optional;


@Repository
public class VerificationRepositoryRedisImpl implements VerificationRepository {
    private final JedisPooled jedis;

    @Autowired
    public VerificationRepositoryRedisImpl(JedisPooled jedis) {
        this.jedis = jedis;
    }

    @Override
    public Optional<Verification> get(VerificationType verificationType, String email) {
        return null;
    }

    @Override
    public void create(Verification verification) {

    }

    @Override
    public void delete(VerificationType verificationType, String email) {

    }
}
