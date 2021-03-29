package com.nyhospital.mapper;

import java.util.List;

import com.nyhospital.domain.BoardVO;
import com.nyhospital.domain.DoctorVO;
import com.nyhospital.domain.InOutMemberVO;
import com.nyhospital.domain.MemberDTO;

public interface AdminMapper {
	
	// 의사 관리 (전체 의사 목록)  
	List<DoctorVO> printAlldoctor();
	// 전체 회원 목록
	List<MemberDTO> printAllMember();
	// 게시판 신고된 글 출력
	public List<BoardVO> getList();
	// 이메일로 회원 아이디 찾기
	public String findID(String email);
	

}