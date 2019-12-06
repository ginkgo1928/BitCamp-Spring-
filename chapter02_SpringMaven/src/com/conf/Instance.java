package com.conf;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample05.SungJukDTO2;
import sample05.SungJukOutput;
import sample03.SungJukDTO;
import sample03.SungJukImpl;


//환경설정
@Configuration
public class Instance {
	
	//메모리에 할당한다. 그리고 돌려 받음
	//samole03
	@Bean(name="sungJukImpl")
	public SungJukImpl getSungJukImpl() {
		return new SungJukImpl();
	}
	@Bean(name ="sungJukDTO")
	public SungJukDTO getSungJukDTO() {
		return new SungJukDTO();
	}
	
	/*
	 * sample05 List가 안되어있던 이유 
	 * :어떤 거와 정확하게 매핑 할지 명시해야된다.
	 * :@Qualifier("자식"),@Autowired-부모
	 */
	@Bean(name ="list")
	public List<SungJukDTO2> getArrayList(){
		return new ArrayList<SungJukDTO2>();	
	}
	
	//객체명이 메서드가 된다.
	//방법1.메서드를 소문자로 바꿈
	//방법2.Bean의 name을 정한다.
	@Bean
	public SungJukOutput sungJukOutput() {
		return new SungJukOutput();
	}

}
