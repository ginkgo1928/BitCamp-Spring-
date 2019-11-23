package member.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import member.bean.MemberDTO;

/** @Title : 회원인증을 하면 세션 생성.
 * @author : ginko1928
 * @Memo : clearAuthenticationAttributes(=로그인 실패시 error 제거)
 * @date : 2019. 11. 20.*/

public class MemberLoginHandlenr implements AuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("memDTO", memberDTO);
		session.setMaxInactiveInterval(60 * 60 * 24);
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			// 인증성공
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			// 인증실패
			redirectStrategy.sendRedirect(request, response, "/main/index");
		}

		clearAuthenticationAttributes(request);
	}

	public void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
