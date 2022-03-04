package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileMangerService {

	// CDN 서버 (이미지, css, js) 정적인 내용을 다른 서버로 분리할때 경로는 명시해야함 
	///Users/seoeunji/git/sns_211015
	///Users/seoeunji/Library/Containers/com.linearity.vn/Data/Documents/6.spring_project/sns-211015/workspace/images/
	//D:\\서은지_211015\\6_spring-project\\sns\\sns_workspace\\images/
		public final static String FILE_UPLOAD_PATH = "/Users/seoeunji/Library/Containers/com.linearity.vn/Data/Documents/6.spring_project/sns-211015/workspace/images/";
		//
		public String saveFile(String userLoginId, MultipartFile file) {
			// 파일 디렉토리 경로 예: toma1019_16456453342/sun.png
			// 파일명이 겹치지 않게 하기 위해 현재시간을 경로에 붙여준다.
			String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
			//D:\\서은지_211015\\6_spring-project\\memo\\workspace\\images/toma1019_16456453342/
			String filePath = FILE_UPLOAD_PATH + directoryName;
			
			// 디렉토리 만들기
			File directory = new File(filePath);
			if(directory.mkdir() == false) {
				return null;
			}
			
			// 파일 업로드 : byte 단위로 업로드 한다.
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(filePath + file.getOriginalFilename());
				Files.write(path, bytes);
			
				// 이미지 URL을 리턴한다.(WebMvcConfig에서 매핑한 이미지 path)
				// 예) http://localhost/images/toma1019_16456453342/sun.png
				
				return "/images/"+ directoryName + file.getOriginalFilename();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			return null;
		}
	}
