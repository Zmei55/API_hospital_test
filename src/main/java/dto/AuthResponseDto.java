package dto;

import com.model.Data;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class AuthResponseDto {
    private String status;
    private String code;
    private Data data;

//    public AuthResponseDto(Data data, String token) {
//        this.data = data;
//        this.token = token;
//    }
}
