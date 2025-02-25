package com.buk.mail.constant;

/**
 * TODO: Mail状态码
 *
 * @author BuK
 * @since 2020/08/20
 */
public enum MailStatusEnum {

    //
    UNTREATED(0, "未处理"),
    PROCESSED(1, "已处理"),
    FAIL(2, "失败");

    public final int value;

    public final String reasonPhrase;

    MailStatusEnum(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
