package com.nsu.resturantqr.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageHelper {

    private static final String
            UPLOAD_DIR="D:/springboot/resturantqr/src/main/resources/static/img";

    public static String uploadFile(MultipartFile file)throws IOException {
        if (file==null || file.isEmpty()){
            return null;
        }
        Path uploadPath= Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)){
            Files.createDirectory(uploadPath);
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);

        String random = UUID.randomUUID().toString();

        String uniqueName=random+fileExtension;

        Path targetPath=uploadPath.resolve(uniqueName);
        file.transferTo(targetPath.toFile());
        return "img/"+uniqueName;
    }

    private  static String getFileExtension(String fileName){
        if (fileName==null || fileName.isEmpty()){
            return null;
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
