package com.nyhospital.domain;

import lombok.Data;

@Data
public class DoctorVO {
	
	private Long did;		//[PK]의사번호 	//seq_doctor 사용
	private String dname;	//의사이름
	private String dept;	//진료과
	
}
