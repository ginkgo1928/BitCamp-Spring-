package sample05;


import java.util.List;
import lombok.Setter;
@Setter
public class SungJukOutput implements SungJuk{
	
	private List<SungJukDTO> list;
	
	@Override
	public void execute() {
		System.out.println("이름\t 국어\t 영어\t 수학\t 영어\t 총점\t 평균");
		for(SungJukDTO sungJukDTO : list) {
			System.out.println(sungJukDTO);		
		}
	}
}
