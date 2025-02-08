package com.nucleo42.infrastruture.adapter;

import com.nucleo42.application.protocol.HashGenerator;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements HashGenerator {
    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }
}
