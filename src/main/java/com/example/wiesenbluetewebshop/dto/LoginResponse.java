package com.example.wiesenbluetewebshop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String accessToken;
    private final boolean ok;
}
