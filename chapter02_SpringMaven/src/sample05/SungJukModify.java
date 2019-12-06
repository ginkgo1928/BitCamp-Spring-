package sample05;


import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
public class SungJukModify implements SungJuk {
	
	@Qualifier("list")
	@Autowired private List<SungJukDTO2> list;
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 이름 입력: ");
		String name = scan.next();
		
		int sw=0;
		for (SungJukDTO2 sungJukDTO : list) {
			if (sungJukDTO.getName().equals(name)) {
				System.out.println(sungJukDTO);
				sw=1;
				System.out.print("국어 점수 입력: ");
				sungJukDTO.setKor(scan.nextInt());
				System.out.print("영어 점수 입력: ");
				sungJukDTO.setEng(scan.nextInt());
				System.out.print("수학 점수 입력: ");
				sungJukDTO.setMath(scan.nextInt());
				
				sungJukDTO.setTot(sungJukDTO.getKor() + sungJukDTO.getEng() + sungJukDTO.getMath());
				sungJukDTO.setAvg(sungJukDTO.getTot());
				System.out.println("입력 하신 정보가 수정 되었습니다.");
			}
		}
			if(sw==0) {
				System.out.println("찾고자 하는 이름이 없습니다.");
			}else {
				System.out.println(name+"의 정보가 수정되었습니다.");
				
			}

		}

	}

