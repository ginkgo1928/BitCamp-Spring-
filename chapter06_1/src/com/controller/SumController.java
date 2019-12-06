package com.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bean.SumDTO;

@Controller
public class SumController {
	/* ModelAndView - 하는 일 창 띄워주기
	 * @RequestMapping(value = "/input.do", method = RequestMethod.GET) 
	 * public ModelAndView input() { 
	 * ModelAndView mav = new ModelAndView();
	 * mav.setViewName("/sum/input"); 
	 * return mav;}
	 * 
	 * @RequestMapping(value = "/result.do", method = RequestMethod.GET)
	 *  public ModelAndView result() { 
	 *  ModelAndView mav = new ModelAndView();
	 *  mav.setViewName("/sum/result"); 
	 *  return mav; }
	 *  
	 *  x,y값 Controller가 받고 싶을 경우 - @RequestParam 사용
	 * @RequestMapping(value = "/result.do", method = RequestMethod.GET)
	 *  public ModelAndView result(@RequestParam int x, @RequestParam int y)  {
	 *  ModelAndView mav = new ModelAndView();
	 *   mav.addObject("x",x);
	 *   mav.addObject("y",y);
	 *   mav.setViewName("/sum/result"); 
	 *  return mav; }
	 *  
	 *  x,y가 값을 입력 안했을 경우 int 보다 타입을 String 타입을 권한다.
	 *  @RequestMapping(value = "/result.do", method = RequestMethod.GET) 
	 *  public ModelAndView result(@RequestParam (required =false,defaultValue = "0") String x,
	 *  @RequestParam (required =false,defaultValue = "0") String y) { 
	 *  ModelAndView mav = new ModelAndView(); 
	 *  mav.addObject("x",x); 
	 *  mav.addObject("y",y);
	 *  mav.setViewName("/sum/result"); 
	 *  return mav; }
	 *  
	 *  map으로 해서 넘겨주는데 ModelAndView 안해도 된다 - ModelMap사용(HttpServletRequest,HttpSession-내장)
	 *  @RequestMapping(value = "/result.do", method = RequestMethod.GET) 
	 *  public String result(@RequestParam Map<String,String>map,ModelMap modelMap){
	 *  modelMap.put("x",map.get("x")); modelMap.put("y",map.get("y")); 
	 *  return /sum/result"; }
	 *  
	 *  
	 */
	
	 
	
	
	@RequestMapping(value = "/input.do", method = RequestMethod.GET)
	//view를 리턴 하고 싶을 경우 - Spring에서 리턴 타입이 String 이면 view 이름으로 인식한다.
	//view 이름 아닐 경우 String으로 리턴 하고 싶은 경우 - @ResponseBody 사용
	public String input() {
		return "/sum/input";

	}

	

	 
	
	@RequestMapping(value = "/result.do", method = RequestMethod.GET) 
	public String result(@ModelAttribute SumDTO sumDTO,Model model){
	  model.addAttribute("sumDTO",sumDTO); 
	  return"/sum/result"; 
	  }

}
