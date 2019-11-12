
package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import member.bean.MemberDTO;
import member.service.MailService;
import member.service.MemberService;

/**
 * @Title : 회원가입 컨트롤.
 * @author : ginkgo1928
 * @date : 2019. 11. 1.
 */
@Controller
@RequestMapping(value="member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private MailService mailService; 



	// WriteForm 화면
	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}

	/** @Title : 닉네임 중복확인.
	 * @author : ginkgo1928  @date : 2019. 11. 1*/
	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeNicknamecheck(@RequestParam String member_nickname, Model model) {
		memberDTO = memberService.writeNicknamecheck(member_nickname);
		if (memberDTO == null)
			return "exist";
		else
			return "not_exist";
	}

	/** @Title : 이메일 중복확인.
	 * @author : ginkgo1928  @date : 2019. 11. 1.*/
	@RequestMapping(value = "writeEmailCheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeEmailCheck(@RequestParam String member_email, Model model) {
		memberDTO = memberService.writeEmailCheck(member_email);
		if (memberDTO == null)
			return "email_ok";
		else
			return "email_fail";
	}

	/** @Title : 회원가입 완료 & 프로필 이미지 storage 연결.
	   @author : ginkgo1928 @date : 2019. 11. 7.*/
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_profile,
			Model model) {
		String filePath = "C:\\Git_Ginkgo-work\\bitcampmentor\\src\\main\\webapp\\storage\\"+map.get("member_email");
		String fileName = member_profile.getOriginalFilename();
		File file = new File(filePath, fileName);
		map.put("member_profile", fileName);
		memberService.write(map);
		try {
			FileCopyUtils.copy(member_profile.getInputStream(), new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("member_email", map.get("member_email"));
		model.addAttribute("display", "/member/write.jsp");
		return "/main/index";
	}

	// LoginForm
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("display", "/member/loginForm.jsp");
		return "/main/index";
	}

	/** @Title : 로그인 처리,세션 기간 설정.
	 * @author : ginkgo1928 @date : 2019. 11. 09. */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String member_email, String member_pwd, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", member_email);
		map.put("member_pwd", member_pwd);
		memberDTO = memberService.login(map);
		memberDTO.setMember_pwd("");
		if (memberDTO != null) {
		    session.setMaxInactiveInterval(60*60*24); 
			session.setAttribute("memDTO", memberDTO);
			return "login_ok";
		} else {
			return "login_fail";
		}
	}
	// 로그아웃 처리
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/main/index");
	}
	
	/** @Title : 계정설정 화면.
	 * @author : ginkgo1928  @date : 2019. 11. 10.*/
	@RequestMapping(value = "modifyForm", method = RequestMethod.GET)
 	public String modifyForm(Model model) {
		model.addAttribute("display", "/member/modifyForm.jsp");
		return "/main/index";
 	
 	}
	/** @Title : 비밀번호 재설정.
	 * @author : ginkgo1928  @date : 2019. 11. 12.*/
	@RequestMapping(value ="setpwdForm", method = RequestMethod.GET)
	public String setpwdForm(Model model) {
		model.addAttribute("display","/member/setpwdForm.jsp");
		return "/main/index";	
	}
	/** @Title : 비밀번호 (이름,메일을 가지고 회원이 존재하는지 여부 확인).
	 * @author : ginkgo1928  @date : 2019. 11. 12.*/
	@RequestMapping(value = "setmemberpwd",method =RequestMethod.POST)
	@ResponseBody
	public String setmemberpwd(@RequestParam String member_name,String member_email,HttpServletRequest request) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("member_name", member_name);
		map.put("member_email",member_email);
		memberDTO=memberService.setmemberpwd(map);
		System.out.println(memberDTO);
		if(memberDTO!=null) {
			System.out.println("이메일 발송");
			ModelAndView mav=new ModelAndView();
			int ran=new Random().nextInt(900000)+100000;
			HttpSession session=request.getSession(true);
			System.out.println(ran+"난수를 보여줘");
			String authCode=String.valueOf(ran);
			session.setAttribute("authCode", authCode);
			session.setAttribute("random", ran);
			String subject="비밀번호 재설정 인증 이메일 입니다.";
			StringBuilder sb=new StringBuilder();
			sb.append(member_name+"님의 인증코드는"+authCode+"입니다.");
			mailService.send(subject, sb.toString(), "ginkgo1928@gmail.com", member_email, null);
			System.out.println(mailService.send(subject, sb.toString(), "ginkgo1928@gmail.com", member_email, null)+"발송");

			
			return "get_member";
		}else { 
			return "not_member";
		}			
	}	
}

