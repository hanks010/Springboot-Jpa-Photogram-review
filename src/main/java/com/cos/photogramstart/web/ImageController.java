package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@GetMapping({ "/", "image/story" })
	public String story() {
		return "image/story";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		imageService.upload(imageUploadDto, principalDetails);
		
		if(imageUploadDto.getFile().isEmpty()) { //MultipartFile 타입은 @Valid로 유효성 검사가 불가능하다
			throw new CustomValidationException("이미지가 첨부되지 않았습니다", null);
		}
		return "redirect:/user/"+principalDetails.getUser().getId();
	}

}
