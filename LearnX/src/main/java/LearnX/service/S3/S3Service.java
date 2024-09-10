package LearnX.service.S3;

import LearnX.DTO.FileDTO;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements IS3Service {
    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;


    @Override
    public FileDTO uploadFile(MultipartFile file) {

        try {
            String fileName = generateFileName(file);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            s3.putObject(bucketName, fileName, file.getInputStream(), objectMetadata);

            String url = s3.getUrl(bucketName, fileName).toString();

            return FileDTO.builder()
                    .fileName(fileName)
                    .url(url)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Resource getFileResource(String filename) {

        try {
            InputStream inputStream = s3.getObject(new GetObjectRequest(bucketName, filename)).getObjectContent();
            return new InputStreamResource(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteFile(String fileName) {
        try {
            s3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file from S3: " + fileName, e);
        }
    }


    private String generateFileName(MultipartFile file) {
        // Generate a unique file name to prevent overwriting
        return UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replaceAll(" ", "_");
    }
}
