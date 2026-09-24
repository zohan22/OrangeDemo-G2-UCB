package orange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EmployeePage;
import pages.LoginPage;
import pages.PIMPage;
import pages.SideMenuPage;

import java.util.HashMap;
import java.util.Map;

public class OrangeRunner {
    private WebDriver driver;

    public void setup() throws InterruptedException {
        driver = new ChromeDriver(getChromeOptions());
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        //LOGIN
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        //ENTRAR A PIM
        SideMenuPage sideMenuPage = new SideMenuPage(driver);
        sideMenuPage.selectPage();

        // GO TO ADD EMPLOYEE
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickOnAddEmployeeButton();

        // REGISTER EMPLOYEE
        String firstName = "Carlos";
        //String middleName = "Alberto";
        //String lastName = "Rojas";
        String employeeId = "QA123";

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.registerEmployee("Carlos", "Alberto", "Rojas", employeeId);

        //GO TO EMPLOYEE LIST
        pimPage.clickOnEmployeeList();

        //SEARCH CREATED EMPLOYEE
        pimPage.searchEmployeeById(employeeId);

        //SEEARCH CREATED EMPLOYEE BY NAME
        pimPage.searchEmployeeByName(firstName);

        Thread.sleep(5000);
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        OrangeRunner runner = new OrangeRunner();
        runner.setup();
    }

    private static ChromeOptions getChromeOptions() {
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }
}