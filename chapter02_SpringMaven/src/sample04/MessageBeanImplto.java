package sample04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
public class MessageBeanImplto implements MessageBean {
	//기본 값=null(Outputter는 new X-->인터페이스!!)
	@Autowired private @Value("이송현") String name;
	@Autowired private @Value("010-3445-5789") String phone;
	@Autowired private Outputter outputter;
	
	
	/*
	 * public MessageBeanImplto(String name) {
	 * System.out.println("MessageBeanImplto 생성자");
	 *  this.name = name; 
	 *  }
	 * 
	 * public void setPhone(String phone) { 
	 * System.out.println("setPhone 메소드");
	 * this.phone = phone; 
	 * }
	 */
	public void setOutputter(Outputter outputter) {
		System.out.println("setOutputter 메소드");
		this.outputter = outputter;
	}
	
	@Override
	public void helloCall() {
		//outputter 인터페이스에서 FileOutputter 클래스에 있는 output 메서드를 호출
		System.out.println("helloCall 메서드");
		outputter.output("이름="+name+"\t전화번호="+phone);	
	}

}
