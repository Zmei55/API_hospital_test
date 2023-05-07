package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class PatientResponseDto {
    private String name;
    private Date birthDate;
    private int cardNumber;
}