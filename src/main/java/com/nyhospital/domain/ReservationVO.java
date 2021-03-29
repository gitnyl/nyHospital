package com.nyhospital.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReservationVO {
	
	private String rno;		//[PK]예약번호 		//seq_reservation 사용
	private String ruserid;	//[FK]예약고객ID
	private Long rdid;		//[FK]의사번호
	private Date date;		//진료 예정일자
	private Date stTime;	//진료 시작시간
	private Date edTime;	//진료 종료시간  => 시작시간 +30분 
	private Date rsvDate;	//예약일
	private char rStatus;	//예약상태	(Y:예약중, N:예약없음, C:예약취소)

}
