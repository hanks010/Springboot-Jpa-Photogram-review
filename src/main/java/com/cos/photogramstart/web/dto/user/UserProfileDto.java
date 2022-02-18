package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserProfileDto {

	private boolean PageOwnerState;
	private int imageCount;
	private User user;
}
