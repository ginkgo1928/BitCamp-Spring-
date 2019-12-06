package sample04;

public class CalcMul implements Calc {
	//곱셈
	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" * " + y + "=" + (x * y));

	}

}
