package com.buk.utils.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * TODO: 上传工具
 *
 * @author BuK
 * @since 2020/08/21
 */
@Slf4j
public class UploadUtil {

    /**
     * 检测包含
     */
    private final static String CHECK_CONTAINS = ".";

    /**
     * 检测不包含
     */
    private final static String CHECK_NOT_CONTAINS = "..";

    /**
     * 检测
     *
     * @param multipartFile
     * @return
     */
    public static boolean check(MultipartFile multipartFile) {
        // 文件检测
        if (multipartFile == null || multipartFile.isEmpty()) {
            log.info("[上传工具]: 检测-文件为空");
            return false;
        }
        // 文件名检测
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename) || originalFilename.contains(CHECK_NOT_CONTAINS)) {
            log.info("[上传工具]: 检测-originalFilename异常");
            return false;
        }
        return true;
    }

    /**
     * 检测
     *
     * @param multipartFile
     * @param fileType
     * @return
     */
    public static boolean check(MultipartFile multipartFile, String fileType) {
        if (!check(multipartFile)) {
            return false;
        }
        // 文件类型检测
        fileType = fileType.contains(CHECK_CONTAINS) ? fileType : CHECK_CONTAINS + fileType;
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename) || !originalFilename.endsWith(fileType)
        ) {
            log.info("[上传工具]: 检测-fileType异常");
            return false;
        }
        return true;
    }

    /**
     * 上传
     *
     * @param path
     * @param multipartFile
     * @throws IOException
     */
    public static void execute(Path path, MultipartFile multipartFile) throws IOException {
        // 获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("[上传工具]: 文件名->" + originalFilename);
        // 文件上传路径
        String filePath = path.toString();
        // 文件唯一性命名
        originalFilename = UUID.randomUUID() + "_" + originalFilename;
        // 生成空文件
        String pathname = filePath + "/" + originalFilename;
        log.info("[上传工具]: 资源绝对路径->" + pathname);
        File file = new File(pathname);
        // 检测目录是否存在
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                log.info("[上传工具]: 创建目录->" + path.toString());
            } catch (IOException e) {
                throw new IOException("[上传工具]: 创建目录失败！", e);
            }
        }
        // 传输文件
        try {
            multipartFile.transferTo(file);
            log.info("[上传工具]: 上传成功！");
        } catch (IOException e) {
            throw new IOException("[上传工具]: 上传失败！", e);
        }
    }

    /**
     * 上传异常
     */
    public static class UploadException extends RuntimeException {

        public UploadException(String message) {
            super(message);
        }

        public UploadException(Throwable cause) {
            super(cause);
        }

        public UploadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private UploadUtil() {
    }
}