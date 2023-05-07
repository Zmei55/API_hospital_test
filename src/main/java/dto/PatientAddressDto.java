package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PatientAddressDto {
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;
}
