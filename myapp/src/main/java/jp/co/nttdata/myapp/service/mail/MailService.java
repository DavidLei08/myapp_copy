package jp.co.nttdata.myapp.service.mail;

public interface MailService {
    public boolean sendMail(String to, String subject, String text);
}
