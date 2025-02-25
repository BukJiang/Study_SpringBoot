package com.buk.mail.manager;

import com.buk.mail.pojo.dto.MailDTO;
import com.buk.mail.constant.MailStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * TODO: qq邮箱管理测试
 */
@SpringBootTest
class QqMailManagerImplTest {

    @Autowired
    private MailManager mailManager;

    @Test
    void send() throws IOException, MessagingException {
        String mail = "1171971708@qq.com";

        MailDTO mailDTO = new MailDTO();
        mailDTO.setId(UUID.randomUUID().toString());
        mailDTO.setTo(new String[]{mail});
        mailDTO.setCc(new String[]{mail});
        mailDTO.setBcc(new String[]{mail});
        mailDTO.setSubject("主题");

        // 1. 普通本文
        mailDTO.setIsHtml(false);
        mailDTO.setText("内容");
//        // 2. html文本
//        mailDTO.setIsHtml(true);
//        mailDTO.setText("<html><body><img src=\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1130375654,423359614&fm=26&gp=0.jpg\" ></body></html>");

//        // 附件
//        File file = new File("./logs/SpringBoot.log");
//        String fileName = file.getName();
//        String contentType = "multipart/form-data";
//        FileInputStream fileInputStream = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, fileInputStream);
//        MultipartFile[] multipartFiles = new MultipartFile[]{multipartFile};
//        mailDTO.setMultipartFiles(multipartFiles);

        mailDTO.setSentTime(new Date());
        mailDTO.setStatus(MailStatusEnum.UNTREATED);
        mailDTO.setErrorInfo("");

        mailManager.send(mailDTO);
    }
}