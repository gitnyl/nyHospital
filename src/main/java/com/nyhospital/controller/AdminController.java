package com.nyhospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyhospital.service.AdminService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
@RequestMapping("/admin/*")
public class AdminController {

	//묵시적 자동 주입
	private AdminService adminService;
	

	// 의사 관리 페이지로 이동
	@GetMapping("/mngDoctor")
	public void mngDoctor(Model model) {
		model.addAttribute("list", adminService.printAlldoctor());
	}
	// 고객 관리
	@GetMapping("/mngMember")
	public void mngMember(Model model) {
		model.addAttribute("list", adminService.printAllMember());
	}
	// 예약 관리
	@GetMapping("/mngReservation")
	public void mngReservation(Model model) {
		model.addAttribute("list", adminService.printAllMember());
	}
	
	
}