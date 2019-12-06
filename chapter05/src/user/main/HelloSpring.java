package user.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import user.service.UserService;

public class HelloSpring {
	public void menu(ApplicationContext context) {
		
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
		int meun;
		while (true) {
			System.out.println("-------memu--------");
			System.out.println("------1.입력-------");
			System.out.println("------2.출력-------");
			System.out.println("------3.수정-------");
			System.out.println("------4.삭제-------");
			System.out.println("------5.검색-------");
			System.out.println("------6.종료-------");
			System.out.println("-------------------");
			System.out.println("-번호를 눌러주세요-");
			meun = scan.nextInt();
			if (meun == 6)break;
			
			if (meun == 1)
			userService = (UserService) context.getBean("userInsertService", UserService.class);
			else if (meun == 2)
			userService = (UserService) context.getBean("userSelectService", UserService.class);
			else if (meun == 3)
			userService = (UserService) context.getBean("userUpdateService", UserService.class);
			else if (meun == 4)
			userService = (UserService) context.getBean("userDeleteService", UserService.class);
			else if(meun==5)
			userService=(UserService)context.getBean("userSerchService",UserService.class);
			
			userService.execute();
		} // while
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		HelloSpring helloSpring = (HelloSpring) context.getBean("helloSpring");
		helloSpring.menu(context);
		System.out.println("프로그램이 종료되었습니다.");

	}
}
