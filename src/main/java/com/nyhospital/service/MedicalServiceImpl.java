package com.nyhospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nyhospital.domain.Criteria;
import com.nyhospital.domain.DoctorVO;
import com.nyhospital.mapper.MedicalMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MedicalServiceImpl implements MedicalService {
	
	//묵시적 자동 주입
	private MedicalMapper mapper;

	
	// 의사 수
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	// 의사 목록
	@Override
	public List<DoctorVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}
	// 의사 상세보기
	@Override
	public DoctorVO get(Long dno) {
		return mapper.read(dno);
	}
	// 의사 등록(채번)
	@Override 
	public void register(DoctorVO doctor) {
		mapper.insertSelectKey(doctor); 
	}
	// 의사 수정
	@Override
	public boolean modify(DoctorVO doctor) {
		return mapper.update(doctor)==1;
	}
	// 의사 삭제
	@Override
	public boolean remove(Long dno) {
		mapper.delete(dno);
		return mapper.delete(dno)==1;
	}
	
}
