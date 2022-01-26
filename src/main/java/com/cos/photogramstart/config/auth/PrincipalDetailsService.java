package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//IoC에 등록됨, 원래 IoC에 등록돼있던 UserDetailsService 대신 이 클래스를 IoC에 등록하고 로그인 처리
@Service 
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository UserRepository;

	//원래의 반환형인 UserDetails 인터페이스도 따로 상속해서 오버라이드 한 값을 반환케 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = UserRepository.findByUsername(username); //username만 확인하면 패스워드는 알아서 해준다. username 중복체크
		if(userEntity == null) 
			return null;
		return new PrincipalDetails(userEntity); //이게 리턴이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다
	}


}
