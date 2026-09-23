package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeePage extends BasePage{
    private final WebDriverWait wait;
    private final By inputFirstName = By.name("firstName");
    private final By inputMiddleName = By.name("middleName");
    private final By inputLastName = By.name("lastName");
    private final By saveButton = By.xpath("//div[@class=\"oxd-form-actions\"]/button[contains(., \"Save\")]");
    private final By inputEmployeeId = By.xpath("//label[contains(., \"Employee Id\")]/parent::div/following-sibling::div/input");

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

    public void typeEmployeeId(String employeeId) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeId));
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(employeeId);
    }

    public void clickOnSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void registerEmployee(String name, String middle, String lastName, String employeeId) {
        typeFirstName(name);
        typeMiddleName(middle);
        typeLastName(lastName);
        typeEmployeeId(employeeId);
        clickOnSaveButton();
    }
}
