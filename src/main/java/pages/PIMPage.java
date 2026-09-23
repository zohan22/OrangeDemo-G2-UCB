package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage extends BasePage{
    private final WebDriverWait wait;
    private final By addEmployeeButton = By.linkText("Add Employee");
    private final By employeeListButton = By.linkText("Employee List");
    private final By inputEmployeeId = By.xpath("//label[contains(., \"Employee Id\")]/parent::div/following-sibling::div/input");
    private final By searchButton = By.xpath("//div[@class=\"oxd-form-actions\"]/button[contains(., \" Search \")]");

    public PIMPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnAddEmployeeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
    }

    public void clickOnEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListButton)).click();
    }

    public void typeEmployeeId(String employeeId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeId)).sendKeys(employeeId);
    }

    public void clickOnSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void searchEmployeeById(String employeeId) {
        typeEmployeeId(employeeId);
        clickOnSearchButton();
    }
}
