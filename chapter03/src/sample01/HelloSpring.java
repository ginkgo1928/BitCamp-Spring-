package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("acQuickStart.xml");
		MessageBean messageBean =(MessageBean)context.getBean("messageBeanImpl");
		//메인
		messageBean.showPrint();
		System.out.println("--------------------------------------");
		messageBean.viewPrint();
		System.out.println("--------------------------------------");
		
		 //메서드 전 messageBean.showPrintBefore();
		 System.out.println("--------------------------------------");
		 messageBean.viewPrintBefore();
		 System.out.println("--------------------------------------");
		 messageBean.display();
		 System.out.println("종료----------------------------------"); //메서드 후
		 messageBean.showPrintAfter();
		 System.out.println("--------------------------------------");
		 messageBean.viewPrintAfter();
		 System.out.println("종료----------------------------------");
		 
	}
}
