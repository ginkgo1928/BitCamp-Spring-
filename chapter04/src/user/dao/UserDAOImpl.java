package user.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import user.bean.UserDTO;

/*
 * public class UserDAOImpl implements UserDAO { 
 * @Setter 
 * private JdbcTemplate jdbcTemplate;
 * @Override public void userWrite(UserDTO userDTO) { 
 * String sql ="insert into usertable values(?,?,?)";
 *  jdbcTemplate.update(sql,userDTO.getName(), userDTO.getId(), userDTO.getPwd()); 
 *  }
 * }
 */
//@Component와 같은 개념 @Repository-DB연결 할때 쓴다.

public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	//NamedParameterJdbcDaoSupport - 상속를 받으면 jdbcTemplate,NamedParameterJdbcTemplate 둘다 있기 쓸 수 있기 떄문에
	//xml에서도 따로 jdbcTemplate을 쓸 필요가 없다.
	//String sql ="insert into usertable values(?,?,?)";
	//getJdbcTemplate().update(sql,userDTO.getName(), userDTO.getId(),
	//userDTO.getPwd());
	 
	@Override
	public void userWrite(UserDTO userDTO) {
		String sql ="insert into usertable values(:name,:id,:pwd)";
		Map<String, String>map=new HashMap<String, String>();
		map.put("name", userDTO.getName());
		map.put("id", userDTO.getId());
		map.put("pwd", userDTO.getPwd());
		getNamedParameterJdbcTemplate().update(sql, map);
	}

	@Override
	public List<UserDTO> getUserList() {
		String sql="select * from usertable";
		return getJdbcTemplate().query
			(sql, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
	
	}
	
	@Override
	public UserDTO getUser(String id) {
		String sql="select * from  usertable where id=:id";
		try {
			return getJdbcTemplate().queryForObject(sql, 
					new BeanPropertyRowMapper<UserDTO>(UserDTO.class),id);
		} catch (EmptyResultDataAccessException  e) {
			return null;
		}		
	}

	@Override
	public void userModify(Map<String, String> map) {
		String sql="update usertable set name=:name,pwd=:pwd where id=:id";
		getNamedParameterJdbcTemplate().update(sql, map);
		
	}

	@Override
	public void userDelete(String id) {
		String sql="delete from usertable where id=:id";
		getJdbcTemplate().update(sql,id);
		
	}
	
}