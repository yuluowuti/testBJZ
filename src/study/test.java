package study;
import java.util.HashMap;

import common.AddSign;
import common.GlobalVal;
import io.restassured.response.ValidatableResponse;
import org.testng.Reporter;

import static io.restassured.RestAssured.given;

public class test {
    public static void main(String []args){
        HashMap map = new HashMap();
        map.put("phone","13572489850");
        map.put("timestamp","1535357127");

        System.out.println(AddSign.getSing(map));
    }
}
