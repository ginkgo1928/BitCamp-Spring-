package user.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
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
@Repository("userDAO")
public class UserDAOImpl extends NamedParameterJdbcDaoSupport implements UserDAO {
	//NamedParameterJdbcDaoSupport - 상속를 받으면 jdbcTemplate,NamedParameterJdbcTemplate 둘다 있기 쓸 수 있기 떄문에
	//xml에서도 따로 jdbcTemplate을 쓸 필요가 없다.
	//String sql ="insert into usertable values(?,?,?)";
	//getJdbcTemplate().update(sql,userDTO.getName(), userDTO.getId(),
	//userDTO.getPwd());
	
	//@Autowired-생성자 오토와이어드 쓸 필요 없음
	public UserDAOImpl(DataSource dataSource) {
		setDataSource(dataSource);
		
	}
	
	/*
	 * @Autowired public void setDS(DataSource dataSource) {
	 * setDataSource(dataSource); }
	 */
	
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