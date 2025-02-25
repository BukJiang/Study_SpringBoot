package com.buk.utils.util;

import com.buk.utils.constant.HttpStatusEnum;
import lombok.Data;

/**
 * TODO: 响应工具
 *
 * @author BuK
 * @see com.buk.utils.util.ResponseUtilTest
 * @since 2020/08/19
 */
@Data
public class ResponseUtil {

    /**
     * 状态码
     */
    private HttpStatusEnum code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 页信息
     */
    private PageInfo pageInfo;

    /**
     * 页信息
     */
    @Data
    public static class PageInfo {

        /**
         * 当前页
         */
        private Long pageNo;

        /**
         * 页记录数
         */
        private Long pageSize;

        /**
         * 总页数
         */
        private Long pageCount;

        /**
         * 总计数
         */
        private Long totalCount;

        /**
         * @param pageNo
         * @param pageSize
         * @param totalCount
         */
        public PageInfo(Long pageNo, Long pageSize, Long totalCount) {
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.totalCount = totalCount;
            setPageCount();
        }

        /**
         * 设置总页数
         */
        private void setPageCount() {
            double ceil = Math.ceil(((double) totalCount / (double) pageSize));
            this.setPageCount((long) ceil);
        }
    }

    /**
     * 初始化
     *
     * @return
     */
    public static ResponseUtil init() {
        return new ResponseUtil();
    }

    /**
     * HttpStatusEnum.OK
     *
     * @return
     */
    public static ResponseUtil ok() {
        return init().code(HttpStatusEnum.OK).message(HttpStatusEnum.OK.message);
    }

    /**
     * HttpStatusEnum.BAD_REQUEST
     *
     * @return
     */
    public static ResponseUtil badRequest() {
        return init().code(HttpStatusEnum.BAD_REQUEST).message(HttpStatusEnum.BAD_REQUEST.message);
    }

    /**
     * HttpStatusEnum.UNAUTHORIZED
     *
     * @return
     */
    public static ResponseUtil unauthorized() {
        return init().code(HttpStatusEnum.UNAUTHORIZED).message(HttpStatusEnum.UNAUTHORIZED.message);
    }

    /**
     * HttpStatusEnum.INTERNAL_SERVER_ERROR
     *
     * @return
     */
    public static ResponseUtil InternalServerError() {
        return init().code(HttpStatusEnum.INTERNAL_SERVER_ERROR).message(HttpStatusEnum.INTERNAL_SERVER_ERROR.message);
    }

    /**
     * 状态码
     *
     * @param code
     * @return
     */
    public ResponseUtil code(HttpStatusEnum code) {
        this.setCode(code);
        return this;
    }

    /**
     * 响应消息
     *
     * @param message
     * @return
     */
    public ResponseUtil message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 响应数据
     *
     * @param data
     * @return
     */
    public ResponseUtil data(Object data) {
        this.setData(data);
        return this;
    }

    /**
     * 页信息
     *
     * @return
     */
    public ResponseUtil pageInfo(Long pageNo, Long pageSize, Long totalCount) {
        this.setPageInfo(new PageInfo(pageNo, pageSize, totalCount));
        return this;
    }

    /**
     * 页信息
     *
     * @param pageInfo
     * @return
     */
    public ResponseUtil pageInfo(PageInfo pageInfo) {
        this.setPageInfo(pageInfo);
        return this;
    }

    private ResponseUtil() {
    }
}
