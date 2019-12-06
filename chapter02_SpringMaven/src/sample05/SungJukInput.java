package sample05;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Scope("prototype")
@Component
public class SungJukInput implements SungJuk {
	
	@Qualifier("list") 
	@Autowired private List<SungJukDTO2> list;
	
	@Autowired private SungJukDTO2 sungJukDTO2;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력: ");
		sungJukDTO2.setName(scan.next());
		System.out.print("국어 점수 입력: ");
		sungJukDTO2.setKor(scan.nextInt());
		System.out.print("영어 점수 입력: ");
		sungJukDTO2.setEng(scan.nextInt());
		System.out.print("수학 점수 입력: ");
		sungJukDTO2.setMath(scan.nextInt());
		
		sungJukDTO2.setTot(sungJukDTO2.getKor()+sungJukDTO2.getEng()+sungJukDTO2.getMath());
		sungJukDTO2.setAvg(sungJukDTO2.getTot()/3.0);
		list.add(sungJukDTO2);
		
		System.out.println("총" + list.size() +"명 의 점수가 저장 되었습니다.");

	}

}
