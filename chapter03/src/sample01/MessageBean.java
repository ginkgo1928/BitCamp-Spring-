package sample01;

public interface MessageBean {
	//모든 메서드 : 조인 포인트
	//삽입 : 포인트컷(show,view)
	public String showPrint();
	public void viewPrint();
	
	public void showPrintBefore();
	public void viewPrintBefore();
	
	public void showPrintAfter();
	public void viewPrintAfter();
	
	//삽입X : 
	public void display();

}
