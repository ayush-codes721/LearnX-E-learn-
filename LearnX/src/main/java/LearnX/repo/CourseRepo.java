package LearnX.repo;

import LearnX.model.Course;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    List<Course> findByTitleContainingIgnoreCase(String title);

}
