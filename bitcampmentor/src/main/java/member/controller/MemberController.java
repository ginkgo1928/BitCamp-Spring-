
package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

	/**
	 * @Title : 닉네임 중복확인.
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 */
	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.POST)
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
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 */
	@RequestMapping(value = "writeEmailCheck", method = RequestMethod.POST)
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
	 * @author : ginkgo1928
	 * @date : 2019. 11. 7.
	 */
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_prople, Model model) {
		//회원 이메일 폴더가 자동생성으로 생성된게 아니라 회원이메일 폴더 만들어주고 넣어야 한다.
		String filePath="C:\\Git_Ginkgo-work\\bitcampmentor\\src\\main\\webapp\\storage"+map.get("member_email");
		String fileName=member_prople.getOriginalFilename();
		File file = new File(filePath, fileName);
		map.put("member_prople", fileName);
		memberService.write(map);
		try {
			FileCopyUtils.copy(member_prople.getInputStream(), new FileOutputStream(file));
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
	/**
	 * @Title : 로그인 처리.
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String member_email, String member_pwd, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", member_email);
		map.put("member_pwd", member_pwd);
		memberDTO = memberService.login(map);
		memberDTO.setMember_pwd("");
		if (memberDTO != null) {	
			session.setAttribute("memDTO", memberDTO);
			return "login_ok";
		} else { 
			return "login_fail";
		}
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		System.out.println("로그아웃");
		session.invalidate();
		return new ModelAndView("redirect:/main/index");
	}
}

