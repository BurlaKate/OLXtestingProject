import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by Katherine on 18.07.2017.
 */
public class MainScreen {

    WebDriver driver;
    @FindBy(id = "postNewAdLink")
    private WebElement newAdButton;
    @FindBy(id = "userEmail")
    private WebElement emailField;
    @FindBy(id = "userPass")
    private WebElement passwordField;
    @FindBy(id = "se_userLogin")
    private WebElement enterLogin;

    MainScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainScreen openPage() {
        driver.get("http://olx.ua");
        return this;
    }

    public MainScreen pressNewAdButton() {
        newAdButton.click();
        return this;
    }

    public MainScreen enterLogin(String email) {
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> emailField.isDisplayed());
        emailField.sendKeys(email);
        return this;
    }

    public MainScreen enterPassword(String password) {
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> passwordField.isDisplayed());
        passwordField.sendKeys(password);
        return this;
    }

    public MainScreen pressEnterLogin() {
        enterLogin.click();
        return this;
    }

    public MainScreen authorization(String email, String password) {
        enterLogin(email)
                .enterPassword(password)
                .pressEnterLogin()
                .closePopUpWindow()
                .closeConfirmationPopUpWindow();
        return this;
    }

    public MainScreen closePopUpWindow() {
        Set<String> allWindows = driver.getWindowHandles();
        if (!allWindows.isEmpty()) {
            for (String windowId : allWindows) {
                driver.switchTo().window(windowId);
                WebElement closeButton = driver.findElement(By.id("fancybox-close"));
                closeButton.click();
            }
        }
        return this;
    }

    public MainScreen closeConfirmationPopUpWindow() {
        Set<String> allWindows = driver.getWindowHandles();
        if (!allWindows.isEmpty()) {
            for (String windowId : allWindows) {
                driver.switchTo().window(windowId);
                WebElement closeButton = driver.findElement(By.cssSelector("a[class='closeVerification link']"));
                closeButton.click();
            }
        }
        return this;
    }


}
