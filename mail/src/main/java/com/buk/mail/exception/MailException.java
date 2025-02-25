package com.buk.mail.exception;

/**
 * TODO: 邮件异常
 *
 * @author BuK
 * @since 2020/08/21
 */
public class MailException extends RuntimeException {

    public MailException(String message) {
        super(message);
    }

    public MailException(Throwable cause) {
        super(cause);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
