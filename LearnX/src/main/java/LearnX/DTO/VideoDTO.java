package LearnX.DTO;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
public class VideoDTO {

    private Long id;

    private String title;
    private String url;
    private String filename;
    private String type;
    private LocalDate uploadDate;
}
