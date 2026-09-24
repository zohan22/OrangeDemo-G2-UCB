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
    private final By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
    private final By inputEmployeeId = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    //success message after creating an employee
    private final By successToast = By.id("oxd-toaster_1");
    
    public EmployeePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeFirstName(String name) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName)).sendKeys(name);
        Thread.sleep(3000);
    }

    public void typeMiddleName(String middle) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputMiddleName)).sendKeys(middle);
        Thread.sleep(3000);
    }

    public void typeLastName(String lastName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputLastName)).sendKeys(lastName);
        Thread.sleep(3000);
    }

    public void typeEmployeeId(String employeeId) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeId));
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(employeeId);
        Thread.sleep(3000);
    }

    public void clickOnSaveButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    //wait for success message to be visible
    public void waitForSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void registerEmployee(String name, String middle, String lastName, String employeeId) throws InterruptedException {
        typeFirstName(name);
        typeMiddleName(middle);
        typeLastName(lastName);
        typeEmployeeId(employeeId);
        clickOnSaveButton();
        waitForSuccessMessage();
        Thread.sleep(5000);
    }
}
