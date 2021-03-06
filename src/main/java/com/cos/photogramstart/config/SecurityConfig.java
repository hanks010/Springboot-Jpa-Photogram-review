package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encode() { 
		return new BCryptPasswordEncoder(); //리턴값을 Bean에 띄워주기 때문에 다른 곳에서 DI해서 사용할 수 있다.
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/auth/signin")// GET
				.loginProcessingUrl("/auth/signin") // POST
				.defaultSuccessUrl("/"); // 로그인 성공 시

	}
}
