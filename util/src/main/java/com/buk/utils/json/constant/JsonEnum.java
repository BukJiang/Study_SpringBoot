package com.buk.utils.json.constant;

/**
 * TODO: Json
 *
 * @author BuK
 * @since 2020/08/19
 */
public enum JsonEnum {

    //
    ONE(1, "一"),
    TWO(2, "二"),
    THREE(3, "三");

    public final int value;

    public final String message;

    JsonEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
