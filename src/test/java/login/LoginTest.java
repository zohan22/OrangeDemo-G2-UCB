package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginDataProvider")
    public void testSuccessfulLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessful(), "The login was not successful");
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return new Object[][]{{"Admin", "admin123"}};
    }
}
