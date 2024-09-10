package LearnX.controller;

import LearnX.DTO.CourseDTO;
import LearnX.DTO.VideoDTO;
import LearnX.request.AddCourseRequest;
import LearnX.response.ApiResponse;
import LearnX.service.Course.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final ICourseService courseService;

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody AddCourseRequest courseRequest) {
        CourseDTO newCourse = courseService.createCourse(courseRequest);
        return ResponseEntity.ok(newCourse);
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Update course by ID
    @PatchMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        CourseDTO updatedCourse = courseService.updateCourse(id, updates);
        return ResponseEntity.ok(updatedCourse);
    }

    // Delete course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Course deleted successfully").success(true).build());
    }

    // Search courses by title
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String title) {
        List<CourseDTO> courses = courseService.findCoursesByTitle(title);
        return ResponseEntity.ok(courses);
    }

    // Get videos by course ID
    @GetMapping("/{courseId}/videos")
    public ResponseEntity<List<VideoDTO>> getVideosByCourseId(@PathVariable Long courseId) {
        List<VideoDTO> videos = courseService.getVideosByCourseId(courseId);
        return ResponseEntity.ok(videos);
    }
}
