package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.nio.file.Paths;

public class PersonalDetailsPage extends BasePage {
    private final WebDriverWait wait;
    private final By driverLicense = field("Driver's License Number");
    private final By licenseExpiry = field("License Expiry Date");
    private final By nationality = dropdown("Nationality");
    private final By maritalStatus = dropdown("Marital Status");
    private final By dateOfBirth = field("Date of Birth");
    private final By maleGender = By.xpath("//label[normalize-space()='Male']//span[contains(@class, 'oxd-radio-input')]");
    private final By bloodType = dropdown("Blood Type");
    private final By testField = field("Test_Field");
    private final By addAttachmentButton = By.xpath("//button[normalize-space()='Add']");
    private final By attachment = By.cssSelector("input[type='file']");
    private final By comment = By.xpath("//label[normalize-space()='Comment']/following::textarea[1]");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By successToast = By.cssSelector("div.oxd-toast");

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static By field(String label) {
        return By.xpath("//label[normalize-space()=\"" + label + "\"]/parent::div/following-sibling::div//input");
    }

    private static By dropdown(String label) {
        return By.xpath("//label[normalize-space()=\"" + label + "\"]/parent::div/following-sibling::div");
    }

    private void type(By locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
    }

    private void select(By locator, String value) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        By option = By.xpath("//div[@role='option' and normalize-space()=\"" + value + "\"]");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void fillPersonalDetails(String license, String expiry, String nationalityValue,
                                    String maritalStatusValue, String birthDate) {
        type(driverLicense, license);
        type(licenseExpiry, expiry);
        select(nationality, nationalityValue);
        select(maritalStatus, maritalStatusValue);
        type(dateOfBirth, birthDate);
        wait.until(ExpectedConditions.elementToBeClickable(maleGender)).click();
    }

    public void savePersonalDetails() {
        clickSaveButton();
        waitForSaveConfirmation();
    }

    public void fillCustomFields(String bloodTypeValue, String testFieldValue) {
        select(bloodType, bloodTypeValue);
        type(testField, testFieldValue);
    }

    public void saveCustomFields() {
        clickSaveButton();
        waitForSaveConfirmation();
    }

    public void addAttachment(String filePath, String commentValue) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(addAttachmentButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(attachment))
                .sendKeys(Paths.get(filePath).toAbsolutePath().toString());
        type(comment, commentValue);
        clickSaveButton();
        waitForSaveConfirmation();
    }

    private void clickSaveButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    private void waitForSaveConfirmation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }
}
