package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cos.photogramstart.config.auth.PrincipalDetails;

//프로필 페이지와 업데이트 jsp 페이지로 보내는 컨트롤러
@Controller
public class UserController {
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId) {
		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		return "user/update";
	}
}
