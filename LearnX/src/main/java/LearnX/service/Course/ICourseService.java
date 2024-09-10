package LearnX.service.Course;

import LearnX.DTO.CourseDTO;
import LearnX.DTO.VideoDTO;
import LearnX.model.Course;
import LearnX.request.AddCourseRequest;
import LearnX.response.ApiResponse;

import java.util.List;
import java.util.Map;

public interface ICourseService {

    CourseDTO getCourseById(Long id);

    CourseDTO createCourse(AddCourseRequest courseRequest);

    List<CourseDTO> getAllCourses();

    CourseDTO updateCourse(Long id, Map<String, Object> updates);

    void deleteCourse(Long id);

    List<CourseDTO> findCoursesByTitle(String title);

    List<VideoDTO> getVideosByCourseId(Long courseId);





}
