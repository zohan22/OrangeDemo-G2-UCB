package orange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EmployeePage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import pages.PIMPage;
import pages.SideMenuPage;

import java.util.HashMap;
import java.util.Map;

public class OrangeRunner {
    private WebDriver driver;

    public void setup() throws InterruptedException {
        driver = new ChromeDriver(getChromeOptions());
        try {
            driver.get("https://opensource-demo.orangehrmlive.com/");
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("Admin", "admin123");

            SideMenuPage sideMenuPage = new SideMenuPage(driver);
            sideMenuPage.selectPage();

            PIMPage pimPage = new PIMPage(driver);
            pimPage.clickOnAddEmployeeButton();

            String employeeName = "Carlos";
            EmployeePage employeePage = new EmployeePage(driver);
            String employeeId = employeePage.registerEmployee(employeeName, "Alberto", "Rojas",
                    "carlos.rojas", "OrangeDemo123!");

            PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
            personalDetailsPage.fillPersonalDetails("DL123456", "2030-12-31", "American",
                    "Single", "1995-05-15");
            personalDetailsPage.savePersonalDetails();
            personalDetailsPage.fillCustomFields("A+", "Automation test");
            personalDetailsPage.saveCustomFields();
            String attachmentPath = System.getProperty(
                    "attachmentPath", "src/test/resources/attachments/employee-attachment.png");
            personalDetailsPage.addAttachment(attachmentPath, "Employee attachment");

            pimPage.clickOnEmployeeList();
            if (!pimPage.searchEmployeeByName(employeeName)) {
                throw new IllegalStateException("The created employee was not found in the employee list");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
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
