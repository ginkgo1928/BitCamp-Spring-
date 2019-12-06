package sample04;

public class CalcAdd implements Calc {
	
	//덧셈
	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" + " + y + "=" +(x+y));

	}

}
