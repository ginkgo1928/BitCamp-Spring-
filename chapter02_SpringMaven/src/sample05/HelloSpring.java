package sample05;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloSpring {
	
	public void menu(ApplicationContext context) {
		Scanner scan = new Scanner(System.in);
		SungJuk sungJuk=null;
		int meun;
		while (true) {
			System.out.println("-------memu--------");
			System.out.println("------1.입력-------");
			System.out.println("------2.출력-------");
			System.out.println("----3.총점 정렬----");
			System.out.println("------3.수정-------");
			System.out.println("------4.삭제-------");
			System.out.println("------5.종료-------");
			
			System.out.println("-------------------");
			System.out.println("-번호를 눌러주세요-");
			meun = scan.nextInt();
			
			if(meun==5) break;
			if (meun == 1) 
				// 부모 = 자식
				sungJuk = context.getBean("sungJukInput", SungJuk.class);
			 else if (meun == 2) 
				sungJuk = context.getBean("sungJukOutput", SungJuk.class);
			 else if(meun==3)
				 sungJuk=context.getBean("sungJukSort",SungJuk.class);
			 else if (meun == 4) 
				sungJuk = context.getBean("sungJukModify", SungJuk.class);
			 else if (meun == 5) 
				sungJuk = context.getBean("sungJukDelete", SungJuk.class);
			 	
				sungJuk.execute();
			
		} // -->while
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring",HelloSpring.class);
		helloSpring.menu(context);
		System.out.println("프로그램이 종료되었습니다.");
	}
}
