package com.nyhospital.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Component
public class MemberDTO implements UserDetails {

//【tbl_member】 userid, userpw, name, email, regdate, outdate, onoff, myroom, birth, phone
	
	private String userId; 		//아이디
	private String userPw; 		//비밀번호
	private String name; 		//이름
	private String email; 		//이메일
	private String phone;		//전화번호
	private Date regDate; 		//회원가입일
	private Date outDate; 		//회원탈퇴일
	private char onOff; 		//현재 접속여부 (0:접속X,1:접속O)
	private String myRoom; 		//참여중인 채팅방 목록
	
	private List<AuthVO> authList;
	private String memberAuthKey; 	// 이메일 인증키
	private int memberAuthStatus; 	// 이메일 인증상태
	private String memberAuthority; // 멤버 권한
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
 
		if (this.name.equals("admin")) {
			auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} 
		else {
			auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return auth;
	}

	@Override
	public String getPassword() {
		return "{noop}" + userPw;
	}

	@Override
	public String getUsername() { // Username = 아이디 (userid)
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
