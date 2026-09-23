package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{
    private final By inputUsername = By.name("username");
    private final By inputPassword = By.name("password");
    private final By loginButton = By.cssSelector("button.orangehrm-login-button");
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeUsername(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).sendKeys(user);
    }

    public void typePassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword)).sendKeys(pass);
    }

    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void loginAs(String user, String pass) {
        typeUsername(user);
        typePassword(pass);
        clickOnLoginButton();
    }
}
