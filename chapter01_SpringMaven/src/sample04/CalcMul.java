package sample04;

import org.springframework.stereotype.Component;

@Component //클래스명과  bean 이름이 같은 경우 Component에 bean이름을 안 정해두됨
public class CalcMul implements Calc {
	@Override
	public void calculate(int x, int y) {
		System.out.println(x +" * " + y + "=" + (x * y));

	}

}
