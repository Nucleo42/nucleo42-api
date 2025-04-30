package com.nucleo42.infrastructure.adapter;

import com.nucleo42.application.protocol.Hasher;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements Hasher {
    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }
}
