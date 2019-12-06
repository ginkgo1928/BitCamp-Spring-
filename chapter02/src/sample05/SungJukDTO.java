package sample05;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SungJukDTO {
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int tot;
	private double avg;
	
	
	/*
	 * @Override public String toString() { return
	 * name+"\t"+kor+"\t"+eng+"\t"+math+"\t"+tot+"\t"+String.format("%.3f", avg); }
	 */


}
