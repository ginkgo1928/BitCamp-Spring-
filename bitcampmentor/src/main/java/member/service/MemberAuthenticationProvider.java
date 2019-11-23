package member.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import member.bean.MemberDTO;
/**@Title : Security Custom
 * @Memo : 1. Token을 사용한 인증
 * @author : gingko1928
 * @date : 2019. 11. 18.*/
public class MemberAuthenticationProvider implements AuthenticationProvider {

	// UserDetailsService를 구현하면 존재하는 메서드loadUserByUsername(=DB에서 사용자 정보를 가져온다)
	@Autowired
	private UserDetailsService memberAuthentServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

		MemberDTO memberDTO = (MemberDTO) memberAuthentServiceImpl.loadUserByUsername(token.getName());
		
		if (memberDTO == null) {
			throw new UsernameNotFoundException("can not find member_email" + token.getName());
		}

		if (!passwordEncoder.matches(token.getCredentials() + "", memberDTO.getMember_pwd())) {
			throw new BadCredentialsException("cannot match password");

		}

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) memberDTO.getAuthorities();
		return new UsernamePasswordAuthenticationToken(memberDTO, memberDTO.getMember_pwd(), authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
