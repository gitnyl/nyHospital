package com.nyhospital.service;

import java.util.List;

import com.nyhospital.domain.DoctorVO;
import com.nyhospital.domain.Criteria;

public interface MedicalService {
	
	// 의사 수
	public int getTotal(Criteria cri);
	// 의사 목록
	public List<DoctorVO> getList(Criteria cri);
	// 의사 상세보기
	public DoctorVO get(Long dno);
	// 의사 등록
	public void register(DoctorVO doctor); 
	// 의사 수정
	public boolean modify(DoctorVO doctor);
	// 의사 삭제
	public boolean remove(Long dno); 
  	
}
