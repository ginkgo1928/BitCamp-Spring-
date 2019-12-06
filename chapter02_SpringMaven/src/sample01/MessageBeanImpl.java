package sample01;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//롬북 어노테이션 이제 필요 없다.스프링 프레임 워크 쓴다.
@Component
public class MessageBeanImpl implements MessageBean {
	
	
	private@Value("딸기")  String fruit;
	
	
	private @Value("5000") int cost;
	private @Value("3") int qty;
	
	@Override
	public void sayHello() {
		System.out.println(fruit+"\t"+cost+"\t"+qty);
		
	}

	@Override
	public void sayHello(String fruit, int cost) {
		System.out.println(fruit+"\t"+cost+"\t"+qty);
		
	}

	@Override
	public void sayHello(String fruit, int cost, int qty) {
		System.out.println(fruit+"\t"+cost+"\t"+qty);
		
	}

}
