package LearnX.request;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class AddCourseRequest {


    private String title;
    private String description;


}
