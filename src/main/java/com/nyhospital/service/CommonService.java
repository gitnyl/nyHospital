package com.nyhospital.service;

import org.springframework.stereotype.Service;

import com.nyhospital.domain.MemberDTO;

@Service
public interface CommonService {
	
	// 회원가입(회원정보 입력+회원권한 입력)
	public void register(MemberDTO vo);
	// 아이디 중복체크
	public boolean isUniqueID(String userId);
	// 이메일 중복체크
	public boolean isUniqueEmail(String email);
	// 이메일로 회원 아이디 찾기
	public String findIdByEmail(String email);
	// 회원의 비밀번호를 임시 비밀번호로 변경
	public String changePassword(String email, String userPw);
	// 자유게시판 좋아요
	public int likeBoard(int bno);
	// 자유게시판 싫어요
	public int unlikeBoard(int bno);
		
}
