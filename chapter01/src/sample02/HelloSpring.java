package sample02;

public class HelloSpring {
	public static void main(String[] args) {
		//MessageBeanKo aa=new MessageBeanKo();1:1관계
		
		//부모는 자식 클래스를 참조 할 수 있다(다형성)
		MessageBean messageBean;
		messageBean = new MessageBeanKo();
		//messageBean = new MessageBeanEn();
		
		messageBean.sayHello("Spring");
	}

}
