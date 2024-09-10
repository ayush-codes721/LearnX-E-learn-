package LearnX.service.Course;

import LearnX.DTO.CourseDTO;
import LearnX.DTO.VideoDTO;
import LearnX.exception.ResourceNoFoundException;
import LearnX.model.Course;
import LearnX.repo.CourseRepo;
import LearnX.repo.VideoRepo;
import LearnX.request.AddCourseRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {

    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;
    private final VideoRepo videoRepo;


    @Override
    public CourseDTO getCourseById(Long id) {

        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNoFoundException("course not found"));

        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO createCourse(AddCourseRequest courseRequest) {

        Course course = modelMapper.map(courseRequest, Course.class);

        Course savedCourse = courseRepo.save(course);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .toList();
    }

    @Override
    public CourseDTO updateCourse(Long id, Map<String, Object> updates) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("course not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> course.setTitle((String) value);
                case "description" -> course.setDescription((String) value);
            }
        });

        Course updatedCourse = courseRepo.save(course);
        return modelMapper.map(updatedCourse, CourseDTO.class);
    }

    @Override
    public void deleteCourse(Long id) {

        courseRepo.findById(id).ifPresentOrElse(courseRepo::delete, () -> {
            throw new ResourceNoFoundException("course not found");
        });

    }

    @Override
    public List<CourseDTO> findCoursesByTitle(String title) {
        return courseRepo.findByTitleContainingIgnoreCase(title)
                .stream().map(course -> modelMapper.map(course, CourseDTO.class))
                .toList();
    }

    @Override
    public List<VideoDTO> getVideosByCourseId(Long courseId) {
         return videoRepo.findByCourseId(courseId)
                .stream()
                .map(video -> modelMapper.map(video, VideoDTO.class))
                .toList();
    }
}
