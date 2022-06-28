package com.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Profile {
	private String nickname;
	private String thumbnailImageUrl;
	private String profileImageUrl;
	private boolean isDefaultImage;
}
