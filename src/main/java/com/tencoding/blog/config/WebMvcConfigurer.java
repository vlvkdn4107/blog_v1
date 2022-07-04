package com.tencoding.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //  = new ObjectMapper(); 의 역활을 한다
public class WebMvcConfigurer {
	
	 private final ObjectMapper objectMapper;
	
}
