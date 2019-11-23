
package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import member.service.MemberMailService;
import member.service.MemberService;

/**
 * @Title : 회원가입 Controller.
 * @author : ginkgo1928
 * @date : 2019. 11. 1.
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberMailService mailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// WriteForm 화면
	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}

	/**
	 * @Title : 닉네임 중복확인.
	 * @author : ginkgo1928 @date : 2019. 11. 1
	 */
	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.GET)
	@ResponseBody
	public String writeNicknamecheck(@RequestParam String member_nickname, Model model) {
		memberDTO = memberService.writeNicknamecheck(member_nickname);
		if (memberDTO == null)
			return "exist";
		else
			return "not_exist";
	}

	/**
	 * @Title : 이메일 중복확인.
	 * @author : ginkgo1928 @date : 2019. 11. 1.
	 */
	@RequestMapping(value = "writeEmailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String writeEmailCheck(@RequestParam String member_email, Model model) {
		memberDTO = memberService.writeEmailCheck(member_email);
		if (memberDTO == null)
			return "email_ok";
		else
			return "email_fail";
	}

	/**
	 * @Title : 회원가입 완료 & 프로필 이미지 storage 연결.
	 * @author : ginkgo1928 @date : 2019. 11. 7.
	 */
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_profile,
			@RequestParam String member_pwd, Model model) {
		member_pwd = passwordEncoder.encode(member_pwd);
		System.out.println(member_pwd);
		String filePath = "C:\\Git_Ginkgo-work\\bitcampmentor\\src\\main\\webapp\\storage\\" + map.get("member_email");
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
	public ModelAndView loginForm(Model model, @RequestParam(required = false) String status) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("status", status);
		mav.addObject("display", "/member/loginForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	/**
	 * @Title : 계정설정 화면.
	 * @author : ginkgo1928 @date : 2019. 11. 10.
	 */
	@RequestMapping(value = "modifyForm", method = RequestMethod.GET)
	public String modifyForm(Model model) {
		model.addAttribute("display", "/member/modifyForm.jsp");
		return "/main/index";

	}

	/**
	 * @Title : 비밀번호 재설정.
	 * @author : ginkgo1928 @date : 2019. 11. 12.
	 */
	@RequestMapping(value = "setpwdForm", method = RequestMethod.GET)
	public String setpwdForm(Model model) {
		model.addAttribute("display", "/member/setpwdForm.jsp");
		return "/main/index";
	}

	/**
	 * @Title : 비밀번호 재설정(회원정보 입력 후 회원여부 확인하고 메일 발송)
	 * @author : ginkgo1928 @date : 2019. 11. 12.
	 *//*
		 * @RequestMapping(value = "setmemberpwd", method = RequestMethod.POST)
		 * 
		 * @ResponseBody public String setmemberpwd(@RequestParam String member_name,
		 * String member_email, HttpServletRequest request, HttpServletResponse
		 * response,Model model) { Map<String, String> map = new HashMap<String,
		 * String>(); map.put("member_name", member_name); map.put("member_email",
		 * member_email); memberDTO = memberService.setmemberpwd(map); if (memberDTO !=
		 * null) { Cookie cookie = new Cookie("Cookie_Email", auauthKey);
		 * cookie.setMaxAge(60 * 3); cookie.setPath("/"); response.addCookie(cookie);
		 * System.out.println(cookie.getName() + cookie.getValue()); return
		 * "get_member"; } else { return "not_member"; } }
		 */
	
	/**
	 * @Title : 메일을 발송한 인증값과 맞는지 확인.
	 * @author : ginkgo1928 @date : 2019. 11. 13.
	 */
	@RequestMapping(value = "setmemberpwdrandom", method = RequestMethod.POST)
	@ResponseBody
	public String setmemberpwdrandom(@RequestParam int set_pwdrandom, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Cookie_Email")) {
					if (Integer.parseInt(cookie.getValue()) == set_pwdrandom) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						return "set_randomOk";
					}
				}
			}
		} else {

		}
		return "set_randomFail";
	}

	/**
	 * @Title : 새로운 비밀번호 화면을 show 활성화 후 비밀번호 변경
	 * @author : ginkgo1928 @date : 2019. 11. 13.
	 */
	@RequestMapping(value = "newPwdCommit", method = RequestMethod.POST)
	@ResponseBody
	public void newPwdCommit(@RequestParam String member_name, String member_email, String member_pwd, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", member_email);
		map.put("member_name", member_name);
		map.put("member_pwd", member_pwd);
		memberService.newPwdCommit(map);
	}
}
