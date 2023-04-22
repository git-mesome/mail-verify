package mailverify.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;
    private String authCode;


    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void createAuthCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) ((int) random.nextInt(26) + 97));
                case 1 -> key.append((char) ((int) random.nextInt(26) + 65));
                case 2 -> key.append(random.nextInt(9));
            }
        }
        authCode = key.toString();
    }

//    //메일 양식 세팅
//    public void setMailForm(String subject, String body, String email) throws MessagingException {
//
//        createAuthCode();
//
//        MimeMessage message = mailSender.createMimeMessage();
//
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"UTF-8");
//        mimeMessageHelper.setTo(email); //수신자
//        mimeMessageHelper.setSubject(subject); //메일 제목
//        mimeMessageHelper.setText(body,true);//메일 내용
//        sendMail(mimeMessageHelper.getMimeMessage());
//
//    }

    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createAuthCode(); //인증 코드 생성

        String setFrom = "rlaalstj8359@naver.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "Test Email Verify"; //제목

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setContext(authCode), "UTF-8", "html");

        return message;
    }

    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        //실제 메일 전송
        mailSender.send(emailForm);

        return authCode; //인증 코드 반환
    }


    //타임리프를 이용한 context 설정
    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);

        //email.html
        return templateEngine.process("email", context);
    }

}
