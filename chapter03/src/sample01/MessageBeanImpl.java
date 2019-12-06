package sample01;

import lombok.Setter;

//타켓 클래스 : 모든 추상 메서드를 구현한 클래스
public class MessageBeanImpl implements MessageBean {
	@Setter
	private String str, endstr;
	
	@Override
	public  String showPrint() {
		
		System.out.println("showPrint 메세지="+str);
		return "KIN";
		
	}
	
	@Override
	public void viewPrint() {
		System.out.println("viewPrint 메세지="+str);
		
	}
	@Override
	public void showPrintBefore() { //핵심관심사항
		//System.out.println("before trace...");
		System.out.println("showPrintBefore 메세지="+str);
	}
	
	@Override
	public void viewPrintBefore() { //핵심관심사항
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("viewPrintBefore 메세지="+str);
	}

	@Override
	public void showPrintAfter() {
		System.out.println("showPrintAfter 메세지="+endstr);
	}
	
	@Override
	public void viewPrintAfter() {
		System.out.println("viewPrintAfter 메세지="+endstr);
		
	}

	@Override
	public void display() { //핵심관심사항
		System.out.println("display 메세지="+ str);
		
	}


}
