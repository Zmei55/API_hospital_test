package okhttp;

import com.google.gson.Gson;
import dto.AllPatientsDto;
import dto.PatientDto;
import dto.PatientResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPatientTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzZDNjYWUzNGQ5ODMyODZlZWNkOTY2MSIsImlhdCI6MTY4MzQ1OTI5OSwiZXhwIjoxNjgzNDYyODk5fQ.o19BVb_XUkBvnIHeVCYxURn1LTW3AS-D_PNsqNra66M";

    @Test
    public void getAllPatientsTest() throws IOException {
        PatientResponseDto patientResponseDto = PatientResponseDto.builder()
                .name("")
                .birthDate(null)
                .cardNumber(0)
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientResponseDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        AllPatientsDto resDto = gson.fromJson(response.body().string(), AllPatientsDto.class);
        System.out.println(resDto.getPatients());
        Assert.assertTrue(resDto.getPatients().contains(""));
    }

    @Test
    public void findPatientByNameSuccessTest() throws IOException {
        PatientResponseDto patientResponseDto = PatientResponseDto.builder()
                .name("Emma Weber")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientResponseDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response);
//        Assert.assertTrue(response.isSuccessful());
//        Assert.assertEquals(response.code(), 200);

        PatientDto resDto = gson.fromJson(response.body().string(), PatientDto.class);
        System.out.println(resDto.getName());
        Assert.assertTrue(resDto.getName().contains("Emma Weber"));
    }
}
