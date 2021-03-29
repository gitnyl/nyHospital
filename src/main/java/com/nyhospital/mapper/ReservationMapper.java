package com.nyhospital.mapper;

import java.util.List;

import com.nyhospital.domain.Criteria;
import com.nyhospital.domain.DoctorVO;



public interface ReservationMapper {
	
	//총 의사 수
	public int getTotalCount(DoctorVO doctor);
	//전체 의사 목록
	public List<DoctorVO> getList();
	//전체 의사 목록(페이징)
	public List<DoctorVO> getListWithPaging(Criteria cri);
	//의사 정보 상세보기
	public DoctorVO readDoctor(Long did);
	//의사 신규 등록
	public Integer insertSelectKey(DoctorVO doctor);
	//의사 정보 수정
	public int updateDoctor(DoctorVO doctor);
	//의사 삭제
	public int deleteDoctor(Long did);
	
}
