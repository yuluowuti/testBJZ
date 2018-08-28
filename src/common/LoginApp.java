package common;

import io.restassured.response.ValidatableResponse;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class LoginApp {
    public static void getToken() throws IOException {
        String url = GlobalVal.HOST + "/v1/authorizations";
        HashMap params =new HashMap();
        params.put("brushed","1");
        params.put("deviceModel","iPhone 6 Plus");
        params.put("deviceName","祝材的 iPhone");
        params.put("deviceToken","b8ffd6d8f3637f977afc6d3b5be168e69d94557914157f2bc1dc322970d3ea4c");
        params.put("memorySize","16");
        params.put("openudid","ca2a12f736b826763de87e7b1f960acc5376f846");
        params.put("password","qwe123");
        params.put("phone","13572489850");
        params.put("systemVersion","10.3.3");
        AddSignT.sign(params);

        ValidatableResponse response = given().headers(GlobalVal.HEADERS).queryParams(params).post(url).then();

        System.out.println(response.extract().body().asString());
    }
    //    测试
    public static void main(String []args) throws IOException {
        LoginApp.getToken();
    }
}
