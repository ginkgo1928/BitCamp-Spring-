package sample02;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
public class CalcAdd implements Calc {
	private int x,y;

	@Override
	public void calculate() {
		System.out.println(x + "+" +  y + "=" + (x+y));
		
	}

}
