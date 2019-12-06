package sample05;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class SungJukSort implements SungJuk {
	
	@Qualifier("list")
	@Autowired private List<SungJukDTO2> list;
	
	@Override
	public void execute() {
		Collections.sort(list);
		System.out.println("성적으로 내림 차순");
		for(SungJukDTO2 sungJukDTO2 :list) {
		System.out.println(sungJukDTO2.toString());

		}

	}
}
