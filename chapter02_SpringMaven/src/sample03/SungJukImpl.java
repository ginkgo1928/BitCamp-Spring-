package sample03;


import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;


import sample03.SungJuk;

//@Component
//환경설정 com.conf 패키지에서 설정 했기 때문에 더 이상 Component는 사용하지 않는다.
@ComponentScan("com.conf") 
public class SungJukImpl implements SungJuk {
	
	@Autowired 
	private SungJukDTO sungJukDTO;
	
	
	@Override
	public void calcTot() {
		sungJukDTO.setTot(sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath());
	}
	
	
	@Override
	public void calcAvg() {
		sungJukDTO.setAvg(sungJukDTO.getTot() / 3.0);
		

	}

	@Override
	public void display() {
		System.out.println(sungJukDTO);
		
	}

	@Override
	public void modify() {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.print("이름:");
		sungJukDTO.setName(scan.next());
		System.out.print("국어:");
		sungJukDTO.setKor(scan.nextInt());
		System.out.print("영어:");
		sungJukDTO.setEng(scan.nextInt());
		System.out.print("수학:");
		sungJukDTO.setMath(scan.nextInt());

	}

}
