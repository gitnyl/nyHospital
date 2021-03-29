package com.nyhospital.mapper;

import org.apache.ibatis.annotations.Param;

import com.nyhospital.domain.MemberDTO;

public interface CommonMapper {
	
	// 회원가입 시 회원 정보 입력
	public void insertUser(MemberDTO vo);
	// 회원가입 시 회원 권한 입력
	public void insertUserAuth(String userId);
	// 중복된 아이디가 있는지 체크
	public int checkID(String userId);
	// 중복된 이메일이 있는지 체크
	public int checkEmail(String email);
	// 회원의 비밀번호를 임시 비밀번호로 변경
	public void updateNewPassword(@Param("email") String email, @Param("userPw") String userpw);
	// 회원정보,권한 조회
	public MemberDTO read(String userId); 
	// 이메일로 회원 아이디 찾기
	public String findID(String email);
	
	
	
}
