package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;

	public User toEntity() {
		return User.builder()
		.name(name)			//만약 기재를 안했다면 db에 공백인 채로 들어가게됨, validation 체크 해야함
		.password(password) //만약 기재를 안했다면 db에 공백인 채로 들어가게됨, validation 체크 해야함
		.website(website)
		.bio(bio)
		.phone(phone)
		.gender(gender)
		.build();
	}

}
