package LearnX.repo;

import LearnX.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video,Long> {

    List<Video> findByCourseId(Long courseId);

}
