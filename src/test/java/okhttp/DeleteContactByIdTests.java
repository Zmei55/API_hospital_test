package okhttp;

import com.google.gson.Gson;
import dto.PatientDto;
import dto.PatientResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DeleteContactByIdTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String token = "";

    String id;

    @BeforeMethod
    public void precondition() throws IOException {
        PatientResponseDto patientResponseDto = PatientResponseDto.builder()
                .name("Emma Weber")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientResponseDto),JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization",token)
                .post(body).build();

        Response response = client.newCall(request).execute();

        PatientDto resDto = gson.fromJson(response.body().string(), PatientDto.class);
        System.out.println(resDto.getName());

//        String message = resDto.getMessage();
//        String[] split = message.split(": ");
        id = resDto.getId();
    }

    @Test
    public void deleteContactByIdSuccessTest() throws IOException {

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/" + id)
                .addHeader("Authorization", token)
                .delete().build();

        Response response = client.newCall(request).execute();

        Assert.assertEquals(response.code(),200);

        PatientDto resDto = gson.fromJson(response.body().string(), PatientDto.class);
        Assert.assertEquals(resDto.getName(),"Emma Weber");
    }
}
