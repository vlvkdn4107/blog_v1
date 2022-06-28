package com.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthToken {
	
	// 넘어 오는 방식으로 똑같이 하는게 더 좋다
	private String accessToken;
    private String tokenType; 
    private String refreshToken;
    private int expiresIn;
    private String scope; 
    private String refreshTokenExpiresIn;
}
