package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

//프로필 페이지와 업데이트 jsp 페이지로 보내는 컨트롤러
@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.profile(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		return "user/update";
	}
}
