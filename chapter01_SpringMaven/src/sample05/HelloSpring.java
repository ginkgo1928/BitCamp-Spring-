package sample05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SungJuk sungjuk=context.getBean("sungJukImpl",SungJuk.class);
		sungjuk.calc();
		sungjuk.display();
		((AbstractApplicationContext)context).close();
		

	}

}
