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
public class PatientDto {
    private String id;
    private String name;
    private Date birthDate;
    private String email;
    private int cardNumber;
    private String gender;
    private String phoneNumber;
    private String identityDocument;
    private Object residenceAddress;
    private Object services;
    private Object containers;
}
