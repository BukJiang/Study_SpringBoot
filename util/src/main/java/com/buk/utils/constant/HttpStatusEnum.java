package com.buk.utils.constant;

/**
 * TODO: HTTP状态码
 *
 * @author BuK
 * @since 2020/08/19
 */
public enum HttpStatusEnum {

    /**
     * 1xx Informational
     * 代表请求已被接受，需要继续处理，这类响应是临时响应，只包含状态行和某些可选的响应头信息，并以空行结束。
     */
    CONTINUE(100, "继续发送请求"),
    SWITCHING_PROTOCOLS(101, "变换协议"),

    /**
     * 2xx Success
     * 代表请求已成功被服务器接收、理解、并接受
     */
    OK(200, "成功"),
    CREATED(201, "已创建"),
    ACCEPTED(202, "已接受，但未处理"),
    NON_AUTHORITATIVE_INFORMATION(203, "非授权信息"),
    NO_CONTENT(204, "成功，无返回内容"),
    RESET_CONTENT(205, "成功，重置内容"),

    /**
     * 3xx Redirection
     * 代表需要客户端采取进一步的操作才能完成请求
     */
    MULTIPLE_CHOICES(300, "多项选择"),
    MOVED_PERMANENTLY(301, "永久移动"),
    FOUND(302, "临时重定向"),
    SEE_OTHER(303, "见其他"),
    NOT_MODIFIED(304, "未修改"),
    USE_PROXY(305, "使用代理"),
    TEMPORARY_REDIRECT(307, "临时重定向"),
    PERMANENT_REDIRECT(308, "永久重定向"),

    /**
     * 4xx Client Error
     * 代表了客户端看起来可能发生了错误，妨碍了服务器的处理
     */
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未经授权"),
    PAYMENT_REQUIRED(402, "需付款"),
    FORBIDDEN(403, "被禁止"),
    NOT_FOUND(404, "未发现"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    NOT_ACCEPTABLE(406, "不接受"),
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理身份验证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "冲突"),
    PAYLOAD_TOO_LARGE(413, "有效载荷过大"),
    URI_TOO_LONG(414, "URI太长"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "请求的范围不满足"),
    LOCKED(423, "资源已锁定"),
    FAILED_DEPENDENCY(424, "依赖失败"),
    TOO_EARLY(425, "请求过早"),
    UPGRADE_REQUIRED(426, "需要升级"),
    PRECONDITION_REQUIRED(428, "前提条件"),
    TOO_MANY_REQUESTS(429, "请求过多"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求头字段过大"),

    /**
     * 5xx Server Error
     * 表示服务器无法完成明显有效的请求
     */
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    NOT_IMPLEMENTED(501, "未实现"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不支持"),
    INSUFFICIENT_STORAGE(507, "存储空间不足"),
    LOOP_DETECTED(508, "检测到循环"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "超出带宽限制"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "需要网络身份验证");

    public final int code;

    public final String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
