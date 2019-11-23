package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository("memberDAO")
@Transactional

public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private MemberDTO memberDTO;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return sqlSession.selectOne("memberSQL.writeNicknamecheck", member_nickname);
	}

	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return sqlSession.selectOne("memberSQL.writeEmailCheck", member_email);
	}
	

	@Override
	public void write(Map<String, String> map) {
		 sqlSession.insert("memberSQL.write",map);
	}

	@Override
	public MemberDTO login(Map<String, String> map) {
		memberDTO=sqlSession.selectOne("memberSQL.login",map);
		return  memberDTO;
	}
	

	@Override
	public MemberDTO setsetmemberpwd(Map<String, String> map) {
		memberDTO=sqlSession.selectOne("memberSQL.setsetmemberpwd",map);
		return memberDTO;
	}

	@Override
	public MemberDTO newPwdCommit(Map<String, String> map) {
		sqlSession.update("memberSQL.newPwdCommit", map);
		return memberDTO;
	}

	@Override
	public MemberDTO getMember(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getMember",map);
	}
	

}
