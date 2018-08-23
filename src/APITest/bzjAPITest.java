package APITest;

import common.AddSign;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class bzjAPITest {
    public static String Host="http://api.xjd.51lianqian.net:7035";
    public static HashMap header = new HashMap();
    public bzjAPITest(){
        header.put("platform","ios");
    }

    @Test
    public void test1() throws IOException {
        String url = Host + "/v1/init/gui";
        HashMap params =new HashMap();
        params.put("version","v2");
        params.put("module","homepage");
        AddSign.sign(params);

        ValidatableResponse response = given().headers(header).queryParams(params).and().get(url).then();
        System.out.println(response.extract().body().asString());
    }

    @Test
    public void test2() throws IOException {
        String url = Host + "/v1/init/gui";
        HashMap params =new HashMap();
        params.put("version","v2");
        params.put("module","homepage");
        AddSign.sign(params);

        ValidatableResponse response = given().headers(header).queryParams(params).and().get(url).then();
        System.out.println(response.extract().body().asString());
    }
}
