package com.nik.hpm.util.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nik.hpm.attachfile.entity.AttachFile;

/**
 * UploadFile
 */
@Component
public class UploadFileUtil {

    @Value("${uploads.filePath}")
    private String uploadFilePath;

    public AttachFile fileUpload(String originalName, byte[] fileData) throws Exception{

        return FileUtil.uploadFile(uploadFilePath, originalName,fileData);

    }
    
}