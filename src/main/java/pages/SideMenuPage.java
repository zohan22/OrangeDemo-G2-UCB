package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SideMenuPage extends BasePage {
    private final WebDriverWait wait;
    private final By pageButton = By.linkText("PIM");

    public SideMenuPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectPage() {
        wait.until(ExpectedConditions.elementToBeClickable(pageButton)).click();
    }
}
