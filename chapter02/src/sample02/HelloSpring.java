package sample02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Calc clac=(Calc)context.getBean("calcAdd");
		clac.calculate();
		
		clac=(Calc)context.getBean("calcMul");
		clac.calculate();

	}

}
