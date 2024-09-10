package LearnX.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {

    String url;
    String fileName;
}
