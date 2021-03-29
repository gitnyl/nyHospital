package com.nyhospital.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.nyhospital.domain.Criteria;
import com.nyhospital.domain.DoctorVO;
import com.nyhospital.mapper.CommonMapper;
import com.nyhospital.mapper.ReservationMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	//묵시적 자동 주입
	private ReservationMapper rsvMapper;
	
	
	//총 의사 수
	@Override
	public int getTotalCount(DoctorVO doctor) {
		return rsvMapper.getTotalCount(doctor);
	}
	//전체 의사 목록(페이징)
	@Override
	public List<DoctorVO> getListWithPaging(Criteria cri) {
		return rsvMapper.getListWithPaging(cri);
	}
	//의사 정보 상세보기
	@Override
	public DoctorVO readDoctor(Long did) {
		return rsvMapper.readDoctor(did);
	}
	//의사 신규 등록
	@Override
	public Integer insertSelectKey(DoctorVO doctor) {
		return rsvMapper.insertSelectKey(doctor);
	}
	//의사 정보 수정
	@Override
	public boolean updateDoctor(DoctorVO doctor) {
		boolean modifyResult = rsvMapper.updateDoctor(doctor)==1;
		return modifyResult;
	}
	//의사 삭제
	@Override
	public boolean deleteDoctor(Long did) {
		return rsvMapper.deleteDoctor(did)==1 ;
	}
	//전체 의사 목록
	@Override
	public List<DoctorVO> getList() {
		return null;
	}
	

	
	
}