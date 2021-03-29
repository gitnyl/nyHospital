package com.nyhospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nyhospital.domain.BoardVO;
import com.nyhospital.domain.DoctorVO;
import com.nyhospital.domain.InOutMemberVO;
import com.nyhospital.domain.MemberDTO;
import com.nyhospital.mapper.AdminMapper;
import com.nyhospital.mapper.CommonMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService { 
	
	//묵시적 자동 주입
	private AdminMapper adminMapper;
	private CommonMapper commonMapper; 
	
	
	// 의사관리-전체 의사 목록
	@Override
	public List<DoctorVO> printAlldoctor() {
		return adminMapper.printAlldoctor();
	}
	// 전체 고객 목록 
	@Override
	public List<MemberDTO> printAllMember() {
		return adminMapper.printAllMember();
	}
	// 1주일 간 가입한 회원 수 
	@Override
	public InOutMemberVO readInMember() {
		return adminMapper.readInMember();
	}
	// 1주일 간 탈퇴한 회원 수 
	@Override
	public InOutMemberVO readOutMember() {
		return adminMapper.readOutMember();
	}
	// 게시판 신고된 글 출력
	@Override
	public List<BoardVO> getList() {
		return adminMapper.getList();
	}
	// 이메일로 회원 아이디 찾기
	@Override
	public String findIdByEmail(String email) {
		return commonMapper.findID(email);
	}
	
	
}