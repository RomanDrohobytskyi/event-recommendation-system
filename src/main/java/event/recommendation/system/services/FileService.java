package event.recommendation.system.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file){
        try {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                createMKDIRIfNotExist();
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                return resultFileName;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public void createMKDIRIfNotExist() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
    }

}
