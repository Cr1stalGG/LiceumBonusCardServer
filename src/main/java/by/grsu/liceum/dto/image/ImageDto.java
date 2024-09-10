package by.grsu.liceum.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto implements Serializable {
    private UUID id;
    private String objectName;
    private String bucketName;
    private UUID institutionId;
}
