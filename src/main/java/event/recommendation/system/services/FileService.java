package event.recommendation.system.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {
    public final static String SYSTEM_SEPARATOR = System.getProperty("file.separator");

    @Value("${upload.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file){
        try {
            if (hasOriginalName(file)) {
                createMKDIRIfNotExist();
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + SYSTEM_SEPARATOR + resultFileName));
                return resultFileName;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private boolean hasOriginalName(MultipartFile file) {
        return Objects.nonNull(file) && StringUtils.isNotBlank(file.getOriginalFilename());
    }

    public void createMKDIRIfNotExist() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
    }

}
