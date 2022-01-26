package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	
	private User user;

	// 권한정보를 받아온다. 권한정보는 여러 개일 수 있기에 Collection 타입
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collector = new ArrayList<GrantedAuthority>();

//		collector.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return user.getRole();
//			}
//		});
// 아래의 람다식으로 표현
		collector.add(() -> {
			return user.getRole();
		});
		return collector;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	//계정이 만료되지 않았는지 리턴
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지는 않은지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//계정의 패스워드가 만료되지 않았는지 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 사용가능한지 리턴
	@Override
	public boolean isEnabled() {
		return true;
	}


}
