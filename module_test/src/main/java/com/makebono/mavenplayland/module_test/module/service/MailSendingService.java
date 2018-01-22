package com.makebono.mavenplayland.module_test.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: MailSendingService 
 * @Description: Sends mails.
 * @author makebono
 * @date 2018年1月19日 下午5:07:38 
 *  
 */
@Service
public class MailSendingService {
    @Autowired
    private MailSender sender;

    public void sendMail(final String from, final String to, final String subject, final String body) {
        // Do not forget 'from' parameter. At least for hotmail, this is mandatory.
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
    }
}
