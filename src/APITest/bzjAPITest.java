package APITest;

import common.AddSign;
import common.Global;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class bzjAPITest {
    @Test
    public void test1() throws IOException {
        String url = Global.HOST + "/v1/init/gui";
        HashMap params =new HashMap();
        params.put("version","v2");
        params.put("module","homepage");
        AddSign.sign(params);

        ValidatableResponse response = given().headers(Global.HEADERS).queryParams(params).and().get(url).then();
        System.out.println(response.extract().body().asString());
    }

    @Test
    public void test2() throws IOException {
    }
}
