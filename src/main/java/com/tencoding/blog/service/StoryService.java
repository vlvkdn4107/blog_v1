package com.tencoding.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.StoryRepository;

@Service
public class StoryService {
	
	// 저장 경로   .yml에서 설정한 C:/springimageDir/tencoBlog/upload/
	@Value("${file.path}")
	private String uploadFolder;
	
	
	
	
//	storyService.imageUpload(fileDto,principalDetail);
	public void ImageUpload(RequestFileDto fileDto, User user) {
		// 파일 업로드 기능 이기 때문에 해당 서버에 바이너리를 받아서 파일을 생성하고 성공하면 DB에 저장할거다.
		// 중복 로직 짜기. (개발자 성향에 따라 다르다)
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + fileDto.getFile().getOriginalFilename();
		System.out.println("이미지 파일명 : " + imageFileName);
		
		// 서버 컴퓨터의 Path를 가지고 와야한다.(저장 경로)
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		System.out.println("전체 파일 경로 + 파밀명 : " + imageFilePath);
		
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Filse 오류!!");
		}
		
		
	}
	
}
