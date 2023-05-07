package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginSuccessTest() throws IOException {
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

        Assert.assertTrue((response.isSuccessful())); // проверка доступа - до сервера путь есть
        Assert.assertEquals(response.code(), 200); // сравнение кода ответа

        AuthResponseDto responseDto = gson.fromJson(response.body().string(), AuthResponseDto.class);
        System.out.println(responseDto);
    }

    @Test
    public void loginWithWrongLoginTest() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder()
                .logName("asd")
                .password("qwe123")
                .station("chirurgisch")
                .build(); // готовим данные для авторизации

        RequestBody body = RequestBody.create(gson.toJson(auth), JSON);
        Request request = new Request.Builder()
                .url("http://localhost:3001/api/auth/login")
                .post(body).build(); // составляем запрос

        Response response = client.newCall(request).execute(); // делает запрос

        Assert.assertFalse((response.isSuccessful()));
        Assert.assertEquals(response.code(), 500);

        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);

//        Assert.assertEquals(errorDto.getError(),"Unauthorized");
        Assert.assertEquals(errorDto.getMessage(),"Cannot read properties of null (reading 'password')");
    }
}
