package sample03;




import java.util.Scanner;

import lombok.AllArgsConstructor;
import sample03.SungJuk;


@AllArgsConstructor
public class SungJukImpl implements SungJuk {
	
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
