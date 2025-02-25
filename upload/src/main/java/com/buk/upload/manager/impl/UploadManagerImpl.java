package com.buk.upload.manager.impl;

import com.buk.upload.manager.UploadManager;
import com.buk.utils.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO: 上传管理实现类
 *
 * @author BuK
 * @since 2020/08/21
 */
@Component
public class UploadManagerImpl implements UploadManager {

    /**
     * 上传路径
     */
    private final Path uploadPath;

    @Autowired
    public UploadManagerImpl() {
        uploadPath = Paths.get("./upload").toAbsolutePath().normalize();
    }

    @Override
    public void execute(MultipartFile multipartFile, String fileType) throws UploadUtil.UploadException {
        if (!UploadUtil.check(multipartFile, fileType)) {
            throw new UploadUtil.UploadException("文件检测未通过！");
        }
        try {
            UploadUtil.execute(uploadPath, multipartFile);
        } catch (IOException e) {
            throw new UploadUtil.UploadException("[上传管理]: 上传失败！", e);
        }
    }
}
