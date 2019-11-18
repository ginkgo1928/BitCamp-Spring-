package member.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired private MemberDAO memberDAO;
	
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return memberDAO.writeNicknamecheck(member_nickname);
	}
	
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return memberDAO.writeEmailCheck(member_email);
	}

	@Override
	public void write(Map<String, String> map) {
		memberDAO.write(map);
	}
	
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	

	@Override
	public MemberDTO setmemberpwd(Map<String, String> map) {
		return memberDAO.setsetmemberpwd(map);
	}
	

	@Override
	public MemberDTO newPwdCommit(Map<String, String> map) {
		return memberDAO.newPwdCommit(map);
	}

	@Override
	public MemberDTO getMember(Map<String, String> map) {
		return memberDAO.getMember(map);
	}

	
}

