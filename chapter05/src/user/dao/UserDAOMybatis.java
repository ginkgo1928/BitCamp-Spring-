package user.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import lombok.Setter;
import user.bean.UserDTO;

@Transactional //close
public class UserDAOMybatis implements UserDAO {
	
	@Setter
	private SqlSession sqlSession;
	
	@Override
	public void userWrite(UserDTO userDTO) {
		sqlSession.insert("userSQL.userWrite",userDTO);
		
	}

	@Override
	public List<UserDTO> getUserList() {
		return sqlSession.selectList("userSQL.getUserList");
	}

	@Override
	public UserDTO getUser(String id) {
		return sqlSession.selectOne("userSQL.getUser",id);
	}


	@Override
	public void userModify(Map<String, String> map) {
		
		sqlSession.update("userSQL.userModify",map);
	}
	@Override
	public void userDelete(String id) {
		sqlSession.delete("userSQL.userDelete",id);
		
	}

	@Override
	public List<UserDTO> userSerch(Map<String, String> map) {
		return sqlSession.selectList("userSQL.userSearch", map);
	}

}
