package com.tencoding.blog.dto;

import org.springframework.web.multipart.MultipartFile;

import com.tencoding.blog.model.Image;
import com.tencoding.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 멤버변수 전체를 사용하는
@Builder
public class RequestFileDto {
	
	// private MutipartFile[] file;
	private MultipartFile file;
	private String uuid; // 고유한 파일 이름을 만들기 위한 속성
	private String storyText;
	
	public Image toEntity(String storyImageUrl, User user) {
		return Image.builder()
				.storyText(storyText)
				.storyImageUrl(storyImageUrl)
				.originFileName(file.getOriginalFilename())
				.user(user)
				.build();
		
	}
	
	
	
}
