package APITest;

import common.AddSignT;
import common.GlobalVal;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Reporter;
import org.testng.annotations.*;
import ztest.ZTestReport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

@Listeners({ZTestReport.class})
public class bzjInit {

    @BeforeSuite(description="测试方法前初始化")
    public void BeforeSuite(){
        RestAssured.baseURI = GlobalVal.HOST;
    }

    @Test(description="关于我们")
    public void testGetAbout(){
        HashMap params =new HashMap();
        AddSignT.sign(params);
        ValidatableResponse response = get("/v1/about").then();

        response.statusCode(200);
        Reporter.log(response.extract().body().asString());
    }

    @Test(description="GUI配置接口:未登录，参数未传")
    public void testGetGui_01(){
        HashMap params =new HashMap();
        AddSignT.sign(params);
        ValidatableResponse response = given().headers(GlobalVal.HEADERS).queryParams(params).get("/v1/init/gui").then();
        response.statusCode(200);
        Reporter.log(response.extract().body().asString());
    }

    @Test(description="GUI配置接口:未登录，参数")
    public void testGetGui_02(){
        HashMap params =new HashMap();
        params.put("version","v2");
        params.put("module","homepage");
        AddSignT.sign(params);

        ValidatableResponse response = given().headers(GlobalVal.HEADERS).queryParams(params).get("/v1/init/gui").then();
        response.statusCode(200);
        Reporter.log(response.extract().body().asString());
    }

    @Test(description="图形验证码生成")
    public void testGetCaptcha(){
        HashMap params =new HashMap();
        params.put("pictureToken","1533621653000472632");
        AddSignT.sign(params);

        ValidatableResponse response = given().headers(GlobalVal.HEADERS).queryParams(params).get("/v1/captcha/create").then();
        Response response1 = given().headers(GlobalVal.HEADERS).queryParams(params).get("/v1/captcha/create");
        response.statusCode(200);

        try {
            File file = new File("picture.jpg");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.append(response1.getBody().toString());// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        Reporter.log(response.extract().body().asString());
    }

    @DataProvider(name="test")
    public Object[][] dataProvider(){
        return new Object[][]{{1},{2}};
    }
}
