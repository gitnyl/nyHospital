package com.nyhospital.mapper;

import java.util.List;

import com.nyhospital.domain.Criteria;
import com.nyhospital.domain.DoctorVO;


public interface MedicalMapper {
	
	// 의사 목록 (페이징)
	public List<DoctorVO> getListWithPaging(Criteria cri);
	// 의사 상세보기
	public DoctorVO read(Long dno);
	// 의사 등록
	public Integer insertSelectKey(DoctorVO doctor); 
	// 의사 수정
	public int update(DoctorVO doctor);
	// 의사 삭제
	public int delete(Long dno);
	// 의사 수 
	public int getTotalCount(Criteria cri);
    
}
