package LearnX.service.Video;

import LearnX.DTO.VideoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVideoService {

    VideoDTO uploadVideo(MultipartFile videoFile, Long courseId,String title);

    // Get video by ID
    VideoDTO getVideoById(Long id);

    // Get all videos for a course by course ID
    List<VideoDTO> getVideosByCourseId(Long courseId);

    // Update video metadata (not the file itself)

    // Delete video (from DB and S3)
    void deleteVideo(Long id);

}
