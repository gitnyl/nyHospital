package com.nyhospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nyhospital.service.ReservationService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class IndexController {
	
	// 메인 페이지로 이동
	@GetMapping("/")
	public String moveIndex() {
		return "index";
	}


}