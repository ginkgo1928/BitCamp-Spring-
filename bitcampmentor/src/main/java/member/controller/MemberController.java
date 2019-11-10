
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
import member.service.MemberService;

/**
 * @Title : 회원가입 컨트롤.
 * @author : ginkgo1928
 * @date : 2019. 11. 1.
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;

	// WriteForm 화면
	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}

	/** @Title : 닉네임 중복확인.
	 * @author : ginkgo1928  @date : 2019. 11. 1.*/
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
	   @author : ginkgo1928 @date : 2019. 11. 7*/
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_profile,
			Model model) {
		String filePath = "C:\\Git_Ginkgo-work\\bitcampmentor\\src\\main\\webapp\\storage\\";
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

	/** @Title : 로그인 처리,아이디 저장(쿠키를 생성한다).
	 * @author : ginkgo1928 @date : 2019. 11. 09. */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String member_email, String member_pwd, String cheboxid,
			HttpServletResponse response, HttpServletRequest request, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", member_email);
		map.put("member_pwd", member_pwd);
		memberDTO = memberService.login(map);
		// memberDTO.setMember_pwd("");
		if (memberDTO != null) {
			if (cheboxid.equals("true")) {
				Cookie cookie = new Cookie("Cookie_Email", memberDTO.getMember_email());
				cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			session.setAttribute("memDTO", memberDTO);
			return "login_ok";
		} else if (cheboxid.equals("false")) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("Cookie_Email")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}
		} else {

		}
		return "login_fail";
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
}

