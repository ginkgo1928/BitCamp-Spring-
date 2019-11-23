package member.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;

/** @Title  : DB에서 유저 정보를 직접 가져옴.
 *  @author : gingko1928
 *  @date   : 2019. 11. 18.*/
@Service(value = "memberAuthentServiceImpl")
public class MemberAuthentServiceImpl implements UserDetailsService  {
	
	@Autowired
	private MemberService memberService;

	
	@Override
	public UserDetails loadUserByUsername(String member_email) throws UsernameNotFoundException {
		Map<String, String>map=new HashMap<String, String>();
		map.put("key", "member_email");
		map.put("value", member_email);
		MemberDTO memberDTO=memberService.getMember(map);
		
		return memberDTO;
	}
	
}
