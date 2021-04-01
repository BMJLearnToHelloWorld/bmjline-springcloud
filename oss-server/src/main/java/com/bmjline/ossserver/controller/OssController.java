package com.bmjline.ossserver.controller;

import com.bmjline.common.api.CommonResult;
import com.bmjline.common.util.UuidUtil;
import com.bmjline.ossserver.util.MinioTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @author bmj
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Value("${minio.endpoint}")
    private String endpoint;

    private final MinioTemplate minioTemplate;

    public OssController(MinioTemplate minioTemplate) {
        this.minioTemplate = minioTemplate;
    }

    /**
     * 上传文件
     *
     * @param obj
     * @return String
     * @throws Exception Exception
     */
    @PostMapping(value = "/upload")
    public CommonResult<String> uploadOne(@RequestParam("obj") MultipartFile obj) {
        if (null == obj || obj.isEmpty()) {
            return CommonResult.failed("上传文件不能为空");
        } else {
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            try {
                minioTemplate.bucketExists(String.valueOf(year));
            } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.failed("check bucket failed: " + e.getMessage());
            }

            String objNameWithUniqueId = obj.getOriginalFilename()
                    .substring(0, obj.getOriginalFilename().lastIndexOf(".")) +
                    UuidUtil.generateUuid() +
                    obj.getOriginalFilename().substring(obj.getOriginalFilename().lastIndexOf("."));

            String objPathName = (String.valueOf(month).length() == 1 ? "0" + month : String.valueOf(month))
                    + File.separator + objNameWithUniqueId;

            try {
                minioTemplate.uploadObjectByStream(String.valueOf(year), objPathName, obj.getInputStream());
                return CommonResult.success(endpoint + File.separator + String.valueOf(year) + File.separator + objPathName);
            } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.failed("upload failed: " + e.getMessage());
            }
        }
    }
}
