package employees;

import base.BaseTest;
import helper.JsonTestDataHelper;
import models.Employee;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;
import pages.PIMPage;
import pages.SideMenuPage;

import java.io.FileNotFoundException;
import java.util.UUID;

public class EmployeeTest extends BaseTest {

    @Test(dataProvider = "employeeDataProvider")
    public void testCreatedEmployeeIsDisplayed(Employee employee) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("Admin", "admin123");

        SideMenuPage sideMenuPage = new SideMenuPage(driver);
        sideMenuPage.selectPage();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickOnAddEmployeeButton();

        String uniqueSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String firstName = employee.getFirstName() + uniqueSuffix;
        String middleName = employee.getMiddleName();
        String lastName = employee.getLastName() + uniqueSuffix;
        String username = employee.getUsername() + uniqueSuffix;

        EmployeePage employeePage = new EmployeePage(driver);
        String employeeId = employeePage.registerEmployee(firstName, middleName, lastName,
                username, employee.getPassword(), employee.getStatus());

        pimPage.openEmployeeList();
        pimPage.searchById(employeeId);

        Assert.assertTrue(pimPage.isEmployeeDisplayedById(employeeId),
                "The created employee was not found in the employee list");
    }

    @DataProvider(name = "employeeDataProvider")
    public Object[] employeeDataProvider() throws FileNotFoundException {
        return JsonTestDataHelper.getInstance().getTestData(
                "src/test/resources/testdata/employee/employeeData.json", Employee.class);
    }
}
