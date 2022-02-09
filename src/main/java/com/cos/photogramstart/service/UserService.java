package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public User userUpdate(int id, User user) {
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new CustomValidationApiException("찾을 수 없는 id입니다");
		});

		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);

		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());

		return userEntity;
	}

	public User profile(int userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지에 없는 페이지입니다.");
		});
		return userEntity;
	}
}
