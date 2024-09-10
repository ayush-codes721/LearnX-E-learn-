package LearnX.service.Video;

import LearnX.DTO.FileDTO;
import LearnX.DTO.VideoDTO;
import LearnX.exception.ResourceNoFoundException;
import LearnX.model.Course;
import LearnX.model.Video;
import LearnX.repo.CourseRepo;
import LearnX.repo.VideoRepo;
import LearnX.service.S3.S3Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {

    private final VideoRepo videoRepo;
    private final S3Service s3Service;
    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;

    @Override
    public VideoDTO uploadVideo(MultipartFile videoFile, Long courseId, String title) {


        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNoFoundException("course not found"));

        if (videoFile == null || videoFile.isEmpty()) {
            throw new IllegalArgumentException("Video file is empty");
        }

        FileDTO fileDTO = s3Service.uploadFile(videoFile);
        Video video = new Video();
        video.setCourse(course);
        video.setType(videoFile.getContentType());
        video.setTitle(title);
        video.setUrl(fileDTO.getUrl());
        video.setFilename(fileDTO.getFileName());

        Video savedVideo = videoRepo.save(video);


        return modelMapper.map(savedVideo, VideoDTO.class);
    }

    @Override
    public VideoDTO getVideoById(Long id) {

        Video video = videoRepo.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("video not found"));
        return modelMapper.map(video, VideoDTO.class);
    }

    @Override
    public List<VideoDTO> getVideosByCourseId(Long courseId) {
        return videoRepo.findByCourseId(courseId)
                .stream()
                .map(video -> modelMapper.map(video, VideoDTO.class))
                .toList();
    }


    @Override
    public void deleteVideo(Long id) {

        videoRepo.findById(id).ifPresentOrElse(video -> {
            s3Service.deleteFile(video.getFilename());
            videoRepo.delete(video);
        }, () -> {
            throw new ResourceNoFoundException("Video not found");
        });
    }

}
