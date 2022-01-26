package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignUpDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "/auth/signin";
	}

	@PostMapping("/auth/signin")
	public String signin() {
		return "";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "/auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignUpDto signUpDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) { //에러가 있으면
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) { //해쉬맵에 하나씩 담고
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("==================");
				System.out.println(error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		}else {
			System.out.println(signUpDto.toString());
		User user = signUpDto.toEntity(); // 통신으로 사용자가 입력한 데이터 받아옴
		log.info(user.toString());
		User userEntity = authService.signUp(user); // DB에 저장함
		return "/auth/signin";
		}
	}
}
