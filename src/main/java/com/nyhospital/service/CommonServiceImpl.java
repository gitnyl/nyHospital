package com.nyhospital.service;

import org.springframework.stereotype.Service;

import com.nyhospital.domain.MemberDTO;
import com.nyhospital.mapper.BoardMapper;
import com.nyhospital.mapper.CommonMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class CommonServiceImpl implements CommonService {
	
	// 묵시적 자동 주입
	private CommonMapper mapper; 
	private BoardMapper boardMapper;
	
	
	// 회원가입(회원정보 입력+ 회원권한 입력)
	@Override
	public void register(MemberDTO vo) {
		mapper.insertUser(vo);
		mapper.insertUserAuth(vo.getUserId());
	}
	// ID 중복체크
	@Override
	public boolean isUniqueID(String userId) {
		if(mapper.checkID(userId) == 0) {
			return true;
		}
		return false;
	}
	// 이메일 중복체크
	@Override
	public boolean isUniqueEmail(String email) {
		if(mapper.checkEmail(email) == 0) {
			return true;
		}
		return false;
	}
	// 이메일로 회원 아이디 찾기
	@Override
	public String findIdByEmail(String email) {
		return mapper.findID(email);
	}
	// 회원의 비밀번호를 임시 비밀번호로 변경
	@Override
	public String changePassword(String email, String userPw) {
		mapper.updateNewPassword(email, userPw);
		return "true";
	}
	// 자유게시판 좋아요
	@Override
	public int likeBoard(int bno) {
		boardMapper.like(bno);
	      return boardMapper.selectLikeCount(bno);
    }
	// 자유게시판 싫어요
	@Override
	public int unlikeBoard(int bno) {
		boardMapper.unlike(bno);
	      return boardMapper.selectUnlikeCount(bno);
	}


}
