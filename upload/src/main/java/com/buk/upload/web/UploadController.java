package com.buk.upload.web;

import com.buk.upload.manager.UploadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO: 上传
 *
 * @author BuK
 * @since 2020/08/21
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadManager uploadManager;

    @PostMapping("/log")
    public void log(@RequestParam("file") MultipartFile multipartFile) {
        uploadManager.execute(multipartFile, ".jpeg");
    }
}
