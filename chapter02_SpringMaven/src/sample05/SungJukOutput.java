package sample05;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

//@Component
@ComponentScan("com.conf")
public class SungJukOutput implements SungJuk{
	@Qualifier("list") 
	@Autowired private List<SungJukDTO2> list;
	
	@Override
	public void execute() {
		System.out.println("이름\t 국어\t 영어\t 수학\t 영어\t 총점\t 평균");
		for(SungJukDTO2 sungJukDTO : list) {
			System.out.println(sungJukDTO.toString());		
		}
	}
}
