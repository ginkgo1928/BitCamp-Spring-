package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.bean.SungJukDTO;


@Controller
public class SungJukController {
	@RequestMapping(value = "/sungJuk/input.do")
	public String sungJukinput() {
		return "/sungJuk/input";	
	}
	
	@RequestMapping(value="/sungJuk/result.do")
	public String result(@ModelAttribute SungJukDTO sungJukDTO, Model model) {
		sungJukDTO.setTot(sungJukDTO.getKor()+sungJukDTO.getMath()+sungJukDTO.getEng());
		sungJukDTO.setAvg(sungJukDTO.getTot()/3.0);
		return "/sungJuk/result";
		
	}


}
