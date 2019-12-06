package sample04;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String filePath,fileName;

	public FileOutputter() {
		System.out.println("FileOutputter 기본생성자");
	}
	
	public void setFilePath(String filePath) {
		System.out.println("setFilePath 메소드");
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		System.out.println("setFileName 메소드");
		this.fileName = fileName;
	}

	@Override
	public void output(String message) {
		//파일 한글(문자)처리
		System.out.println("output 메소드");
		try {
			FileWriter fileWriter=new FileWriter(filePath+fileName);
			fileWriter.write(message);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
