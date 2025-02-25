package com.buk.mail.web;

import com.buk.mail.pojo.dto.MailDTO;
import com.buk.mail.constant.MailStatusEnum;
import com.buk.mail.manager.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.UUID;

/**
 * TODO: 邮件
 *
 * @author BuK
 * @since 2020/08/21
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailManager mailManager;

    @RequestMapping("/sent")
    public void exceptionHandler() throws MessagingException {
        String mail = "1171971708@qq.com";

        MailDTO mailDTO = new MailDTO();
        mailDTO.setId(UUID.randomUUID().toString());
        mailDTO.setTo(new String[]{mail});
        mailDTO.setCc(new String[]{mail});
        mailDTO.setBcc(new String[]{mail});
        mailDTO.setSubject("主题");
        mailDTO.setIsHtml(false);
        mailDTO.setText("内容");
        mailDTO.setSentTime(new Date());
        mailDTO.setStatus(MailStatusEnum.UNTREATED);
        mailDTO.setErrorInfo("");

        mailManager.send(mailDTO);
    }
}
