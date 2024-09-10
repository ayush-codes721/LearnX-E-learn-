package LearnX.DTO;

import LearnX.model.Video;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDTO {

    private Long id;

    private String title;
    private String description;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private List<VideoDTO> videos=new ArrayList<>();
}
