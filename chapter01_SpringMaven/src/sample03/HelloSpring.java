package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {
	public static void main(String[] args) {
		//스프링 설정 파일(applicationContext.xml:가장 많이 쓴다.)
		//ApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		//싱글톤을 잡아서 num=3,  xml-scope 할 경우 각 각 클래스 메모리에 잡는다
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageBean bean  =(MessageBean)context.getBean("messageBean");
		bean.sayHello("Spring");
		System.out.println();
		
		MessageBean bean2  = (MessageBean)context.getBean("messageBean");
		bean2.sayHello("Spring");
		System.out.println();
		
		MessageBean bean3  = (MessageBean)context.getBean("messageBean");
		bean3.sayHello("Spring");
		System.out.println();
	
	
	}

	
}
