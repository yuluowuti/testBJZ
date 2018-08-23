package APITest;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ztest.ZTestReport;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import java.lang.reflect.Method;
import static org.hamcrest.Matchers.*;

@Listeners({ZTestReport.class})
public class bzjInit {

    @BeforeMethod(description="测试方法前初始化")
    public void beforeMethod(Method m){
        RestAssured.baseURI = "https://bjzapi.51mingyao.com";
    }

    @Test(description="测试DEMO")
    public void testDemo(){
        Reporter.log("this is demo!");
        System.out.println("测试DEMO");
    }

    @Test(description="关于我们")
    public void testGetAbout(){
        Reporter.log("关于我们");
        get("/v1/about").then().body("status", equalTo("10000"));
    }

    @DataProvider(name="test")
    public Object[][] dataProvider(){
        return new Object[][]{{1},{2}};
    }
}
