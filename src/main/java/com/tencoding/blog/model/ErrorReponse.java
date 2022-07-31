package com.tencoding.blog.model;

import java.util.List;

import com.tencoding.blog.dto.CustomError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorReponse {
	private String statusCode;
	private String requestUrl;
	private int code;
	private String message;
	private List<CustomError> errorList;
}
