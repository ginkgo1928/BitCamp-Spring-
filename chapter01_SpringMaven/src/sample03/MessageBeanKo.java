package sample03;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageBean") //-->bean
@Scope("prototype") //-->scope
//스트링 메이븐 :lib에서 빌드패스 한 게 아니라 xml에 미리 환경 설정 다 했음!!
public class MessageBeanKo implements MessageBean {
	private int num; //0 기본 값
	
	public MessageBeanKo() {
		System.out.println("MessageBeanKo 기본 생성자");
	}
	
	@Override
	public void sayHello(String name) {
		 num++;
		 System.out.println("num=" + num);
		System.out.println("안녕하세요 "+name +" !!");
		
	}
	

}
