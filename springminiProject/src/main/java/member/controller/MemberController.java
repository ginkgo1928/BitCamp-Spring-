package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;
import member.service.MemberService;


@Controller
@RequestMapping(value="member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String id, @RequestParam String pwd,HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		MemberDTO memberDTO = memberService.login(map);
		if (memberDTO != null) {
			String hyunID="hyun3761";
			String hyunpwd="3761";
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
			session.setAttribute("hyunID", hyunID);
			session.setAttribute("hyunpwd", hyunpwd);
			return "login_ok";
		} else {
			return "login_fail";
		}
	}
	@RequestMapping(value = "logout",method =RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/main/index");
		
	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="write",method = RequestMethod.POST)
	public String write(@ModelAttribute MemberDTO memberDTO,Model model) {
		memberService.write(memberDTO);
		
		model.addAttribute("display", "/member/write.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="checkId", method=RequestMethod.POST)
	@ResponseBody
	public String checkId(@RequestParam String id, Model model) {
		MemberDTO memberDTO = memberService.checkId(id);
		
		if(memberDTO==null)
			return "not_exist";
		else
			return "exist";
	}
	
	@RequestMapping(value="checkPost", method=RequestMethod.GET)
	public String checkPost() {
		return "/member/checkPost";
	}
	
	@RequestMapping(value="postSearch", method=RequestMethod.POST)
	public ModelAndView postSearch(@RequestParam Map<String, String> map) {
		List<ZipcodeDTO>list=memberService.postSearch(map);
		ModelAndView mav=new ModelAndView();
		mav.addObject("list",list);
		mav.setViewName("jsonView");
		return mav;
	}
}



