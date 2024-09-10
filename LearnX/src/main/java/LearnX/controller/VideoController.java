package LearnX.controller;

import LearnX.DTO.VideoDTO;
import LearnX.response.ApiResponse;
import LearnX.service.S3.S3Service;
import LearnX.service.Video.IVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin("*")
@RequiredArgsConstructor
public class VideoController {

    private final IVideoService videoService;
    private  final S3Service s3Service;

    // Upload a video to a specific course
    @PostMapping("/upload")
    public ResponseEntity<VideoDTO> uploadVideo(
            @RequestParam("file") MultipartFile videoFile,
            @RequestParam("courseId") Long courseId,
            @RequestParam("title") String title) {
        VideoDTO videoDTO = videoService.uploadVideo(videoFile, courseId, title);
        return ResponseEntity.ok(videoDTO);
    }

    // Get video by ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Long id) {
        VideoDTO video = videoService.getVideoById(id);
        return ResponseEntity.ok(video);
    }

    // Get all videos by course ID
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<VideoDTO>> getVideosByCourseId(@PathVariable Long courseId) {
        List<VideoDTO> videos = videoService.getVideosByCourseId(courseId);
        return ResponseEntity.ok(videos);
    }

    // Delete a video by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok(ApiResponse.builder().message("video deleted").success(true).build());
    }

    @GetMapping("stream/{filename}")
    public ResponseEntity<Resource> getFullVideo(@PathVariable String filename) {
        try {
            Resource videoResource = s3Service.getFileResource(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "video/mp4") // Update as necessary for different formats
                    .body(videoResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Handle error cases more gracefully
        }
    }

}
