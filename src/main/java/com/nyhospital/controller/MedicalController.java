package com.nyhospital.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/medical/*") 
@AllArgsConstructor
public class MedicalController {

	// 진료시간표 페이지로 이동
	@GetMapping("/schedule")
	public void schedule() { }
	
	// 진료예약 페이지로 이동
	@GetMapping("/reservation")
	public void reservation() { }
	
}