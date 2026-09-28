package employees;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EmployeeListPage;
import pages.EmployeePage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import pages.PIMPage;
import pages.SideMenuPage;

import java.util.UUID;

public class EmployeeTest extends BaseTest {

    @Test(dataProvider = "employeeDataProvider")
    public void testCreatedEmployeeIsDisplayed(String firstName, String middleName, String lastName,
                                               String username, String password, String license,
                                               String licenseExpiry, String nationality, String maritalStatus,
                                               String birthDate, String bloodType, String testField,
                                               String comment) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        SideMenuPage sideMenuPage = new SideMenuPage(driver);
        sideMenuPage.selectPage();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickOnAddEmployeeButton();

        String uniqueSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        firstName += uniqueSuffix;
        lastName += uniqueSuffix;

        EmployeePage employeePage = new EmployeePage(driver);
        String employeeId = employeePage.registerEmployee(firstName, middleName, lastName,
                username + uniqueSuffix, password);

        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
        personalDetailsPage.fillPersonalDetails(license, licenseExpiry, nationality, maritalStatus, birthDate);
        personalDetailsPage.savePersonalDetails();
        personalDetailsPage.fillCustomFields(bloodType, testField);
        personalDetailsPage.saveCustomFields();
        personalDetailsPage.addAttachment(
                System.getProperty("attachmentPath",
                        "src/test/resources/attachments/employee-attachment.png"), comment);

        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.open();
        employeeListPage.searchById(employeeId);

        Assert.assertTrue(employeeListPage.isEmployeeDisplayedById(employeeId),
                "The created employee was not found in the employee list");
    }

    @DataProvider(name = "employeeDataProvider")
    public Object[][] employeeDataProvider() {
        return new Object[][]{{
                "Carlos", "Alberto", "Rojas", "carlos.rojas", "OrangeDemo123!",
                "DL123456", "2030-12-31", "American", "Single", "1995-05-15",
                "A+", "Automation test", "Employee attachment"
        }};
    }
}
