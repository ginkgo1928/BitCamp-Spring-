package sample05;

import java.util.List;
import java.util.Scanner;
import lombok.Setter;

@Setter
public class SungJukInput implements SungJuk {
	private List<SungJukDTO> list;
	private SungJukDTO sungJukDTO;
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력: ");
		sungJukDTO.setName(scan.next());
		System.out.print("국어 점수 입력: ");
		sungJukDTO.setKor(scan.nextInt());
		System.out.print("영어 점수 입력: ");
		sungJukDTO.setEng(scan.nextInt());
		System.out.print("수학 점수 입력: ");
		sungJukDTO.setMath(scan.nextInt());
		
		sungJukDTO.setTot(sungJukDTO.getKor()+sungJukDTO.getEng()+sungJukDTO.getMath());
		sungJukDTO.setAvg(sungJukDTO.getTot()/3.0);
		list.add(sungJukDTO);
		
		System.out.println("총" + list.size() +"명 의 점수가 저장 되었습니다.");

	}

}
