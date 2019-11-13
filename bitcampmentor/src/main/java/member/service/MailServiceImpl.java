package member.service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @Title : 이메일 발송 Service.
 * @author : ginkgo1928
 * @date : 2019. 11. 12.
 */
@Service
public class MailServiceImpl implements MailService {
	//private Log log = LogFactory.getLog(MailServiceImpl.class);

	//Email 발송
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	

	@Override
	public boolean send(String subject, String text, String from, String to, String filePath) {

		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
			helper.setSubject(subject);
			helper.setText(text, true);
			helper.setFrom(from);
			helper.setTo(to);
			if (filePath != null) {
				File file = new File(filePath);
				if (file.exists()) {
					helper.addAttachment(file.getName(), new File(filePath));
				}
			}
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
