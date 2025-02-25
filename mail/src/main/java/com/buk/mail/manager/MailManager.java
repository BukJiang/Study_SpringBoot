package com.buk.mail.manager;

import com.buk.mail.pojo.dto.MailDTO;

import javax.mail.MessagingException;

/**
 * TODO: 邮件管理
 *
 * @author BuK
 * @since 2020/08/20
 */
public interface MailManager {

    /**
     * 发送
     *
     * @param mailDTO
     * @throws MessagingException
     */
    void send(MailDTO mailDTO) throws MessagingException;
}
