package com.buk.mail.pojo.dto;

import com.buk.mail.constant.MailStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * TODO: 邮件DTO
 *
 * @author BuK
 * @since 2020/08/20
 */
@Data
public class MailDTO {

    /**
     * ID
     */
    private String id;

    /**
     * 发送人
     */
    private String from;

    /**
     * 接收人
     */
    private String[] to;

    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 密送
     */
    private String[] bcc;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 是否是html
     */
    private Boolean isHtml;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * 邮件附件
     * {@link JsonIgnore} json序列化时忽略该属性
     */
    @JsonIgnore
    private MultipartFile[] multipartFiles;

    /**
     * 发送时间
     */
    private Date sentTime;

    /**
     * 状态
     */
    private MailStatusEnum status;

    /**
     * 报错信息
     */
    private String errorInfo;
}
