package sample01;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class MessageBeanImpl implements MessageBean {
	//fruit 생성자 Injectio
	@NonNull
	private String fruit;
	//cost,qty setter Injection
	@Setter
	private int cost, qty;
	
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
