package com.nucleo42.infrastruture.adapter;

import com.nucleo42.application.protocol.HasheGenerator;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements HasheGenerator {
    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }
}
