package com.revshop.productservice.Util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
    private static final String UPLOAD_DIR = "C:/Users/dell/git/microservices-architecture/clientapp/src/main/webapp/Images";

    public static String saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = UPLOAD_DIR + File.separator + fileName;
        File dest = new File(filePath);
        multipartFile.transferTo(dest);
        return fileName; 
    }
}