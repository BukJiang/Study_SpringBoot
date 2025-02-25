package com.buk.mail.manager.impl;

import com.buk.mail.pojo.dto.MailDTO;
import com.buk.mail.constant.MailStatusEnum;
import com.buk.mail.exception.MailException;
import com.buk.mail.manager.MailManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

/**
 * TODO: qq邮件管理实现类
 *
 * @author BuK
 * @see com.buk.example.manager.impl.QqMailManagerImplTest
 * @since 2020/08/20
 */
@Slf4j
@Component
public class QqMailManagerImpl implements MailManager {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(MailDTO mailDTO) throws MailException {
        // 1. 发送人从配置项读取
        mailDTO.setFrom(from);
        try {
            // 2. 检测邮件信息
            checkMailDTO(mailDTO);
            // 3. 发送邮件信息
            sendMailDTO(mailDTO);
        } catch (MailException e) {
            throw new MailException("[qq邮件管理]: 发送失败！", e);
        }
    }

    /**
     * 检测邮件信息
     *
     * @param mailDTO
     */
    private void checkMailDTO(MailDTO mailDTO) throws MailException {
        if (ArrayUtils.isEmpty(mailDTO.getTo())) {
            throw new MailException("MailDTO.to is null");
        }
        if (ArrayUtils.isEmpty(mailDTO.getCc())) {
            throw new MailException("MailDTO.cc is null");
        }
        if (ArrayUtils.isEmpty(mailDTO.getBcc())) {
            throw new MailException("MailDTO.bcc is null");
        }
    }

    /**
     * 发送邮件信息
     *
     * @param mailDTO
     * @throws MailException
     */
    private void sendMailDTO(MailDTO mailDTO) throws MailException {
        try {
            // true表示支持复杂类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    javaMailSender.createMimeMessage(),
                    true
            );

            mimeMessageHelper.setFrom(mailDTO.getFrom());
            mimeMessageHelper.setTo(mailDTO.getTo());
            // 抄送
            if (ArrayUtils.isNotEmpty(mailDTO.getCc())) {
                mimeMessageHelper.setCc(mailDTO.getCc());
            }
            // 密送
            if (ArrayUtils.isNotEmpty(mailDTO.getBcc())) {
                mimeMessageHelper.setCc(mailDTO.getBcc());
            }
            // 附件
            mimeMessageHelper.setSubject(mailDTO.getSubject());
            mimeMessageHelper.setText(mailDTO.getText(), mailDTO.getIsHtml());
            if (mailDTO.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mailDTO.getMultipartFiles()) {
                    String attachmentFilename;
                    if (StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
                        attachmentFilename = multipartFile.getOriginalFilename();
                    } else if (StringUtils.isNotBlank(multipartFile.getName())) {
                        attachmentFilename = multipartFile.getName();
                    } else {
                        log.info("[qq邮件管理]: MultipartFile.attachmentFilename-> is blank");
                        attachmentFilename = "";
                    }
                    log.info("[qq邮件管理]: MultipartFile.attachmentFilename->{}", attachmentFilename);
                    mimeMessageHelper.addAttachment(attachmentFilename, multipartFile);
                }
            }
            mimeMessageHelper.setSentDate(mailDTO.getSentTime());

            // 发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            // 发送成功
            mailDTO.setStatus(MailStatusEnum.PROCESSED);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            // 发送失败
            mailDTO.setStatus(MailStatusEnum.FAIL);
            mailDTO.setErrorInfo(ex.getMessage());
            throw new MailException(ex);
        }
    }
}
