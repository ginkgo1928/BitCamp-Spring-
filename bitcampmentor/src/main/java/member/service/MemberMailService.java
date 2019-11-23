package member.service;

public interface MemberMailService {

	public String getpwdEmail(String member_email, String member_name);

	public void writesendmailkey(String member_email, String member_name);

}
