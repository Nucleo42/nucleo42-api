package com.nucleo42.infrastruture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDTO(@JsonProperty("access_token") String accessToken) {
}
