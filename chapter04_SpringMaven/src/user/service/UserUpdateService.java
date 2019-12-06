package user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import user.bean.UserDTO;
import user.dao.UserDAO;
@Service
public class UserUpdateService implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void execute() {
		// 데이터
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 아이디 입력: ");
		String id = scan.next();
		//DB(DAO에서 주는 걸 받기 때문에 DTO를 new 할 필요 없다.)
		UserDTO userDTO=userDAO.getUser(id);
		
		if(userDTO==null) {
			System.out.println("입력하신 아이디가 존재하지 않습니다.");
			return;
		}
		System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
		
		System.out.println();
		System.out.print("수정할 이름 입력:");
		String name=scan.next();
		System.out.print("수정할 비밀번호 입력:");
		String pwd=scan.next();
		Map<String, String>map=new HashMap<String, String>();
		map.put("name",name);
		map.put("id",id);
		map.put("pwd",pwd);
		userDAO.userModify(map);
		System.out.println("정보를 수정하였습니다.");
		//응답
		
	}
}
