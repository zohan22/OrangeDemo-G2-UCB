package listeners;

import helper.ScreenShotHelper;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ReportManager;

import java.lang.reflect.Field;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.getInstance().startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ReportManager.getInstance().getTest();
        if (test != null) {
            test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ReportManager.getInstance().getTest();
        if (test != null) {
            WebDriver driver = null;
            Object testInstance = result.getInstance();
            Class<?> currentClass = testInstance.getClass();

            // Búsqueda recursiva del driver
            while (currentClass != null && driver == null) {
                try {
                    Field driverField = currentClass.getDeclaredField("driver");
                    driverField.setAccessible(true);
                    driver = (WebDriver) driverField.get(testInstance);
                } catch (NoSuchFieldException e) {
                    currentClass = currentClass.getSuperclass();
                } catch (Exception e) {
                    break;
                }
            }

            if (driver != null) {
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(
                        driver, 
                        Status.FAIL, 
                        "Test Failed: " + result.getThrowable().getMessage()
                );
            } else {
                test.log(Status.FAIL, "Test Failed (Driver not found): " + result.getThrowable().getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ReportManager.getInstance().getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getInstance().flush();
    }
}