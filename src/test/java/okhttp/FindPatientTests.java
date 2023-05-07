package okhttp;

import com.google.gson.Gson;
import dto.*;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPatientTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @BeforeMethod
    public void precondition() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder()
                .logName("qwe")
                .password("qwe123")
                .station("chirurgisch")
                .build(); // готовим данные для авторизации

        RequestBody body = RequestBody.create(gson.toJson(auth), JSON);
        Request request = new Request.Builder()
                .url("http://localhost:3001/api/auth/login")
                .post(body).build(); // составляем запрос

        Response response = client.newCall(request).execute(); // делает запрос

        Assert.assertTrue(response.isSuccessful()); // проверка доступа - до сервера путь есть
        Assert.assertEquals(response.code(), 200); // сравнение кода ответа

        AuthResponseDto responseDto = gson.fromJson(response.body().string(), AuthResponseDto.class);
        token = "Bearer " + responseDto.getData().getToken();
//        System.out.println(token);
    }

    @Test
    public void getAllPatientsTest() throws IOException {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .name("")
                .birthDate("")
                .cardNumber("")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientRequestDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        PatientResponseDto resDto = gson.fromJson(response.body().string(), PatientResponseDto.class);
        Assert.assertTrue(resDto.getData().getResult().get(0).getName().contains("Emma Weber"));
    }

    @Test
    public void findPatientByNameSuccessTest() throws IOException {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .name("Sofia Wagner")
                .birthDate("")
                .cardNumber("")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientRequestDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        PatientResponseDto resDto = gson.fromJson(response.body().string(), PatientResponseDto.class);
        Assert.assertTrue(resDto.getData().getResult().get(0).getName().contains("Sofia Wagner"));
    }

    @Test
    public void findPatientByDateOfBirthSuccessTest() throws IOException {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .name("")
                .birthDate("1966-09-12T00:00:00.000Z")
                .cardNumber("")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientRequestDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        PatientResponseDto resDto = gson.fromJson(response.body().string(), PatientResponseDto.class);
        Assert.assertTrue(resDto.getData().getResult().get(0).getName().contains("Ben Weber"));
    }

    @Test
    public void findPatientByCardNumberSuccessTest() throws IOException {
        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
                .name("")
                .birthDate("")
                .cardNumber("516981227")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(patientRequestDto), JSON);

        Request request = new Request.Builder()
                .url("http://localhost:3001/api/patients/")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        PatientResponseDto resDto = gson.fromJson(response.body().string(), PatientResponseDto.class);
        Assert.assertTrue(resDto.getData().getResult().get(0).getName().contains("Emilia Schmidt"));
    }

//    @Test
//    public void findPatientErrorTest() throws IOException {
//        PatientRequestDto patientRequestDto = PatientRequestDto.builder()
//                .name("Boba Fett")
//                .birthDate("")
//                .cardNumber("")
//                .build();
//
//        RequestBody body = RequestBody.create(gson.toJson(patientRequestDto), JSON);
//
//        Request request = new Request.Builder()
//                .url("http://localhost:3001/api/patients/")
//                .addHeader("Authorization", token)
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());

//        Assert.assertFalse(response.isSuccessful());
//        Assert.assertEquals(response.code(), 200);

//        PatientResponseDto resDto = gson.fromJson(response.body().string(), PatientResponseDto.class);
//        Assert.assertTrue(resDto.getData().getResult().get(0).getName().contains("Emilia Schmidt"));
//    }
}
