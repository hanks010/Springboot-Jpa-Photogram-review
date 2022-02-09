package com.cos.photogramstart.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.ImageRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;

	@Value("${file.path}")
	private String uploadFolder; // yml 파일에서 지정한 경로값이 변수에 담긴다.

	public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지 파일 이름: " + imageFileName);

		Path imageFilePath = Paths.get(uploadFolder + imageFileName); // 경로 + 파일 이름

		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);

		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
