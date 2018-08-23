package APITest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ztest.ZTestReport;

import java.lang.reflect.Method;

@Listeners({ZTestReport.class})
public class testReport {

    @BeforeMethod(description="测试方法前初始化")
    public void beforeMethod(Method m){
        System.out.println("测试方法前初始化");
    }

    @Test(description="测试DEMO")
    public void testDemo(){
        Reporter.log("this is demo!");
        System.out.println("测试DEMO");
    }

    @DataProvider(name="test")
    public Object[][] dataProvider(){
        return new Object[][]{{1},{2}};
    }
}
