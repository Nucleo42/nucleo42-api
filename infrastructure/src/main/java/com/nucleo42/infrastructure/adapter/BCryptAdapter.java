package com.nucleo42.infrastructure.adapter;

import com.nucleo42.application.protocol.HashCompare;
import com.nucleo42.application.protocol.HashGenerator;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements HashGenerator, HashCompare {
    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }

    @Override
    public boolean compare(String rawValue, String hash) {
        return BCrypt.checkpw(rawValue, hash);
    }
}
