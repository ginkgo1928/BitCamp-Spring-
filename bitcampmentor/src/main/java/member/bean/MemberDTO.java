package member.bean;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Component
//UserDetails 사용자를 담는 인터페이스.
public class MemberDTO implements UserDetails{
	private String member_name;
	private String member_nickname;
	private int member_flag;
	private String member_pwd;
	private String member_email;
	private String member_profile;
	private String member_seq;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-DD")
	private Date logtime;
	
	//계정이 갖고 있는 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth=new ArrayList<GrantedAuthority>();
		if(this.member_email.equals("qws507@naver.com")){
			auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return auth;
	}
	
	@Override
	public String getPassword() {
		return this.member_pwd;
	}
	@Override
	public String getUsername() {
		return this.member_pwd;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
