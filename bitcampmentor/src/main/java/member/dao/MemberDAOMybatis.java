package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository("memberDAO")
@Transactional
/**
 * @Title : MemberDAO클래스
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	/**
	 * @Title : 닉네임 중복확인
	 * @author : ginkgo1928
	 * @date : 2019. 11. 5.
	 */
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return sqlSession.selectOne("memberSQL.writeNicknamecheck", member_nickname);
	}
	/**
	 * @Title : 이름 중복확인
	 * @author : ginkgo1928
	 * @date : 2019. 11. 5.
	 */
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return sqlSession.selectOne("memberSQL.writeEmailCheck", member_email);
	}
	/**
	 * @Title : 회원가입 처리
	 * @author : ginkgo1928
	 * @date : 2019. 11. 5.
	 */
	@Override
	public void write(Map<String, String> map) {
		System.out.println("멤버 DAO");
		 sqlSession.insert("memberSQL.write",map);
	}
	/**
	 * @Title : 로그인 처리
	 * @author : ginkgo1928
	 * @date : 2019. 11. 5.
	 */
	@Override
	public MemberDTO login(Map<String, String> map) {
		MemberDTO memberDTO=sqlSession.selectOne("memberSQL.login",map);
		return  memberDTO;
	}


}
