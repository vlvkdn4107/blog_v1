package com.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAccount {
	private boolean profileNicknameNeedsAgreement;
	private boolean profileImageNeedsAgreement;
	private Profile profile;
	private boolean hasEmail;
	private boolean emailNeedsAgreement;
	private boolean is_emailValid;
	private boolean is_emailVerified;
	private String email;
}
