package sample04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		//FileOutPutter Bean은 XML에서 읽기 때문에 여기서 읽을 필요가 없다.
		//XML의 정의 순서는 상관 없다.
		//(XML은 순서대로 읽는 대로 읽는게 한 번에 읽고 XML에서 자체적으로 순서를 정한다) 
		//라이브 사이클
		MessageBean messageBean =(MessageBean)context.getBean("messageBeanImplto");
		messageBean.helloCall();
	
	}

}
