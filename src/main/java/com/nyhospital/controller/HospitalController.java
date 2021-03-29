package com.nyhospital.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyhospital.service.CommonService;
import com.nyhospital.service.HospitalService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/hospital/*") 
@AllArgsConstructor
public class HospitalController {

	// 오시는 길 페이지로 이동
	@GetMapping("/location")
	public void location() { }	
	
	// 약국찾기 페이지로 이동
	@GetMapping("/pharmacy")
	public void pharmacy() { }
	
	
}