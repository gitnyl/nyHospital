package com.nyhospital.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nyhospital.domain.MemberDTO;
import com.nyhospital.service.CommonService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@AllArgsConstructor
@Log4j
public class CommonController {
	
	// 묵시적 자동 주입
	private CommonService service;
	private JavaMailSender mailSender;	// 메일 서비스를 사용하기 위해 의존성 주입
    private BCryptPasswordEncoder pwdEncoder;
    
    
	// 로그인 페이지로 이동
	@GetMapping("/login")
	public void loginInput(String error, String logout, Model model) {
		log.info("error: " + "로그인에 실패했습니다.");
		log.info("logout: " + "로그아웃 되었습니다.");
		
		if(error!=null)  {
			model.addAttribute("error", "로그인에 실패했습니다.");
		}
		if(logout!=null) {
			model.addAttribute("logout", "로그아웃 되었습니다.");
		}
	}
    
	// 로그아웃 처리
	@PostMapping("/logout")
	public void logoutPost() { }
	
	// ID/PW 찾기 페이지로 이동
	@GetMapping("/forgotIDPW")
	public String moveForgotIdPw() {
		return "forgotIDPW";
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("/join")
	public String moveRegister() {
		return "join";
	}
	
	// 관리자 페이지
	@GetMapping("/admin/adminpage")
	public void openAdminpage() { }
	// 마이 페이지 
	@GetMapping("/user/mypage")
	public void openMypage() { }
	
	// 회원가입 
	@PostMapping("/join")
	public String register(MemberDTO vo, BindingResult result, RedirectAttributes rttr) {
		System.out.println(vo);
		
		try {
			String encodedPassword = pwdEncoder.encode(vo.getUserPw());
			vo.setUserPw(encodedPassword);
			service.register(vo);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		rttr.addFlashAttribute("registerSuccess", "회원가입 되었습니다.");
		return "redirect:/login";
	}
	
	// ID 중복 체크
	@GetMapping(value="/checkId/{userId}", produces={MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	public ResponseEntity<String> checkUserId(@PathVariable("userId") String userId) {
		
		if(service.isUniqueID(userId)) {
			return new ResponseEntity<String>("new",HttpStatus.OK);
		}
		return new ResponseEntity<String>("duplicated",HttpStatus.OK);
	}
	
	// 이메일 중복체크
	@RequestMapping(value="/checkEmail", method=RequestMethod.GET, produces="application/text; charset=utf-8")
	public @ResponseBody ResponseEntity<String> checkEmail(@RequestParam("email") String email) throws UnsupportedEncodingException {
		String responseMsg;
		
		if(service.isUniqueEmail(email.trim())) {
			responseMsg = "new";
		} 
		else{
			responseMsg = "duplicated";
		}
		
		return new ResponseEntity<String>(responseMsg, HttpStatus.OK);
	}
	
	// 회원가입 시 이메일로 인증번호 전송
	@GetMapping(value="/sendAuthMail", produces="application/text; charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> sendEmail(@RequestParam("email") String email) {
		
		// 이메일로 받는 인증코드(난수)
		int randNumber = (int)(Math.random()*10000); 	//난수 범위 0~9999
			
		String setfrom = "nyhospitalcenter@gmail.com"; 	//발신인
		String tomail = email; 							//수신인
		String title = "[NYhospital-Center] 회원가입을(를) 위한 이메일 인증번호 입니다."; 	//메일 제목
		String content = 								//메일 내용
				System.getProperty("line.separator") 	//줄 간격 두기
				+ System.getProperty("line.separator")
				+ "안녕하세요! 고객님  [NYhospital]를 찾아주셔서 감사합니다."
				+ System.getProperty("line.separator")
				+ "[NYhospital] 회원가입을(를) 위한 위한 이메일 인증번호를 알려드립니다."
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "▶ 인증번호는 " + String.format("%04d", randNumber) + " 입니다."
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "회원가입을 계속 진행 하시려면, "
				+ "인증번호 입력창에 위 번호를 입력해 주세요."
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator");
		try {
			 MimeMessage message = mailSender.createMimeMessage();
			 MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			 
			 messageHelper.setFrom(setfrom);  //발신인
			 messageHelper.setTo(tomail);     //수신인 
			 messageHelper.setSubject(title); //메일 제목
			 messageHelper.setText(content);  //메일 내용
			 
			 mailSender.send(message);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(content, HttpStatus.OK);
	}
	
	// '이메일로 보낸 인증번호'와 '사용자가 입력한 인증번호' 비교
	@PostMapping(value="/compareEmailAuth", consumes="application/json", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> compareEmailAuth(@RequestBody HashMap<String, Object> map) {
			
		String userNumber = (String) map.get("userNumber"); 	//유저가 입력한 번호
		String originNumber = (String) map.get("originNumber"); //서버에서 만들어서 유저에게 보낸 번호
			
		if(originNumber.equals(userNumber)) {
			return new ResponseEntity<String>("same", HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<String>("different", HttpStatus.OK);
		}
	}
	
	// 회원의 비밀번호를 임시 비밀번호로 초기화해서 전송
	@PostMapping("/findidpw")
	public String findidpw(String email, RedirectAttributes rttr) {
			
		// 아이디 찾기
		String username = service.findIdByEmail(email);
		// 임시 비밀번호 생성
        String new_password = "";
        
        for (int i=0; i<12; i++) {
           new_password += (char) ((Math.random()*26) +97);
        }
        String encoded_pw = pwdEncoder.encode(new_password);
        
        // 발신 메일 정보 설정
        String setfrom = "nyhospital123456@gmail.com";
        String tomail = email;
        String title = "[NYhospital-Center] 임시 비밀번호 발급 안내 ";	//메일 제목
		String content = 										//메일 내용
				System.getProperty("line.separator") 			//줄 간격 두기
				+ System.getProperty("line.separator")
				+ "안녕하세요! 고객님, "
				+ System.getProperty("line.separator")
				+ username + " 님의 임시 비밀번호 발급 내역을 안내해 드립니다. "
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "▶ ID :  " + username 
				+ System.getProperty("line.separator")
				+ "▶" + username + " 임시 비밀번호 :  " + new_password
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "로그인 후 비밀번호를 변경하시면, 아이디를 보다 안전하게 지킬 수 있습니다. "
				+ System.getProperty("line.separator");
		
        // 임시 비밀번호를 메일로 보낸 후, 메일 발송이 성공하면 회원의 비밀번호 변경
        try {
        	MimeMessage message = mailSender.createMimeMessage();
        	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);  //발신인
			messageHelper.setTo(tomail);     //수신인
			messageHelper.setSubject(title); //제목
			messageHelper.setText(content);  //내용
			
		    mailSender.send(message);
		    service.changePassword(email, encoded_pw);
		} 
        catch(Exception e) {
        	e.printStackTrace();
		}
		
        rttr.addFlashAttribute("findidpwSuccess", "고객님의 이메일로 [임시 비밀번호]가 발송되었습니다.");
		return "redirect:/login";
	}
	
	
}