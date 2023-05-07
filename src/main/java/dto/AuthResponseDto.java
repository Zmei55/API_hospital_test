package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AuthResponseDto {
    private String status;
    private String code;
    private AuthResponseDataDto data;

//    public AuthResponseDto(Data data, String token) {
//        this.data = data;
//        this.token = token;
//    }
}
