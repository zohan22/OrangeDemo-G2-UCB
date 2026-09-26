package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeListPage extends BasePage {
    private final WebDriverWait wait;
    private final By employeeListButton = By.linkText("Employee List");
    private final By inputEmployeeName = By.xpath("//label[contains(., 'Employee Name')]/parent::div/following-sibling::div//input");
    private final By inputEmployeeId = By.xpath("//label[contains(., 'Employee Id')]/parent::div/following-sibling::div/input");
    private final By searchButton = By.xpath("//div[@class='oxd-form-actions']/button[contains(., ' Search ')]");

    public EmployeeListPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListButton)).click();
    }

    public void searchByName(String employeeName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeName)).sendKeys(employeeName);
        By suggestion = By.xpath("//div[@role='option' and contains(normalize-space(), \"" + employeeName + "\")]");
        wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void searchById(String employeeId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeId)).sendKeys(employeeId);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isEmployeeDisplayed(String employeeName) {
        By employeeRow = By.xpath("//div[contains(@class, 'oxd-table-row') and .//*[normalize-space()=\""
                + employeeName + "\"]]");
        return !wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(employeeRow)).isEmpty();
    }

    public boolean isEmployeeDisplayedById(String employeeId) {
        By employeeRow = By.xpath("//div[contains(@class, 'oxd-table-row') and .//*[normalize-space()=\""
                + employeeId + "\"]]");
        return !wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(employeeRow)).isEmpty();
    }
}
