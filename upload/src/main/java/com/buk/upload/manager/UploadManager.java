package com.buk.upload.manager;

import com.buk.utils.util.UploadUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO: 上传管理
 *
 * @author BuK
 * @since 2020/08/21
 */
public interface UploadManager {

    /**
     * 上传
     *
     * @param multipartFile
     * @param fileType
     * @throws UploadUtil.UploadException
     */
    void execute(MultipartFile multipartFile, String fileType) throws UploadUtil.UploadException;
}
