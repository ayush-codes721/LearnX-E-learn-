package LearnX.service.S3;

import LearnX.DTO.FileDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {

    FileDTO uploadFile(MultipartFile file);
     Resource getFileResource(String filename);
    void deleteFile(String fileName);

}
