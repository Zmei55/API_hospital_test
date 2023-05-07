package dto;

import com.model.FindPatientResponseData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PatientResponseDto {
    private String status;
    private String code;
    private FindPatientResponseData data;
}
