package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeePage extends BasePage {
    private final WebDriverWait wait;
    private final By inputFirstName = By.name("firstName");
    private final By inputMiddleName = By.name("middleName");
    private final By inputLastName = By.name("lastName");
    private final By saveButton = By.xpath("//div[@class=\"oxd-form-actions\"]/button[contains(., \"Save\")]");
    private final By inputEmployeeId = By.xpath("//label[contains(., \"Employee Id\")]/parent::div/following-sibling::div/input");
    private final By createLoginDetails = By.cssSelector("span.oxd-switch-input");
    private final By inputUsername = By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div/input");
    private final By inputPassword = By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div/input");
    private final By inputConfirmPassword = By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/input");
    private final By enabledStatus = By.xpath("//label[normalize-space()='Enabled']//span[contains(@class, 'oxd-radio-input')]");
    private final By successToast = By.cssSelector("div.oxd-toast");

    public EmployeePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName)).sendKeys(name);
    }

    public void typeMiddleName(String middle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputMiddleName)).sendKeys(middle);
    }

    public void typeLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputLastName)).sendKeys(lastName);
    }

    public String getEmployeeId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeId)).getAttribute("value");
    }

    public void clickCreateLoginDetails() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(createLoginDetails)).click();
    }

    public void typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).sendKeys(username);
    }

    public void selectEnabledStatus() {
        WebElement status = wait.until(ExpectedConditions.elementToBeClickable(enabledStatus));
        if (!status.isSelected()) {
            status.click();
        }
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword)).sendKeys(password);
    }

    public void typeConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputConfirmPassword)).sendKeys(password);
    }

    public void waitForSaveConfirmation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void clickOnSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String registerEmployee(String name, String middle, String lastName,
                                   String username, String password) {
        typeFirstName(name);
        typeMiddleName(middle);
        typeLastName(lastName);
        String employeeId = getEmployeeId();
        clickCreateLoginDetails();
        typeUsername(username);
        selectEnabledStatus();
        typePassword(password);
        typeConfirmPassword(password);
        clickOnSaveButton();
        waitForSaveConfirmation();
        return employeeId;
    }
}
