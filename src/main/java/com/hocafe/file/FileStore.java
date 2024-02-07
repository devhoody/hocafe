package com.hocafe.file;

import com.hocafe.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    // 파일 경로
    @Value("${file.dir}")
    private String path;

    // 전체 파일이름
    public String getFullPath(String filename){
        return path + filename;
    }

    // 여러 파일 일때
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                files.add(storeFile(multipartFile));
            }
        }
        return files;
    }

    // 파일명 구하고 저장하기(확장명, uuid이용)
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String uploadFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(uploadFilename);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(uploadFilename, storeFilename);
    }

    private static String createStoreFilename(String originalFilename) {
        String ext = getExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String getExt(String originalFilename) {
        int cos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(cos + 1);
        return ext;
    }

}
