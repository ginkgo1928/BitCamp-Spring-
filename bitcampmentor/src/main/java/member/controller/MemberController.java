package member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeNicknamecheck(@RequestParam String member_nickname, Model model) {
		MemberDTO memberDTO = memberService.writeNicknamecheck(member_nickname);
		if (memberDTO == null) 
			return "exist";
		else
		return "not_exist";
	}
	@RequestMapping(value = "writeEmailCheck",method=RequestMethod.POST)
	@ResponseBody
	public String writeEmailCheck(@RequestParam String member_email,Model model) {
		MemberDTO memberDTO=memberService.writeEmailCheck(member_email);
		if(memberDTO==null)
			return "email_ok";
		else
			return "email_fail";
	}
	
	@RequestMapping(value ="memberwirte",method=RequestMethod.POST)
	public String memberwrite(@ModelAttribute MemberDTO memberDTO,Model model) {
		memberService.memberwirte(memberDTO);
		System.out.println(memberDTO+"eee");
		model.addAttribute("display", "/member/write.jsp");
		return "/main/index";
		
		}
		
	}


