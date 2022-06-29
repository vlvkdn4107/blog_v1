package com.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthResponseDto {
	
	private String tokenType;
	private String accessToken;
	private String expiresIn;
	private String refreshToken;
	private String refreshTokenExpiresIn;
	private String scope;
	
}
