package jp.co.nttdata.myapp.service.mail;

import jp.co.nttdata.myapp.utils.thread.ThreadPoolUtilmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements  MailService {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 用来发送模版邮件
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendMail(String to, String subject, String text) {
        boolean sendResult =true;
        Context context = new Context();
        context.setVariable("project", "MedicialBBS");
        context.setVariable("author", "DavidLei08");
        context.setVariable("code", text);
        String emailContent = templateEngine.process("mail", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (Throwable e) {
            e.printStackTrace();
            sendResult =false;
        }
        return sendResult;
    }
}
