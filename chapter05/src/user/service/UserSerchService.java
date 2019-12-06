package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSerchService implements UserService {
	@Setter
	private UserDAO userDAO;

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1.이름 검색");
		System.out.println("2. 아이디 검색");
		System.out.print("번호 입력 :");
		int num = scan.nextInt();
		
		String key = null;
		String value = null;
		
		if (num == 1) {
			System.out.print("검색할 이름을 입려:");
			value = scan.next();
			key = "name";
		} else if (num == 2) {
			System.out.println("검색할 아이디 입력:");
			value = scan.next();
			key = "id";

		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("value", value);
		
		List<UserDTO>list=userDAO.userSerch(map);
		for(UserDTO userDTO:list) {
			System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		}
	}

}
