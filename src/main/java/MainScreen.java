import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Katherine on 18.07.2017.
 */
public class MainScreen {

    private WebDriver driver;
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

    public NewAdScreen authorization(String email, String password) {
        pressNewAdButton()
                .enterLogin(email)
                .enterPassword(password)
                .pressEnterLogin();
        return new NewAdScreen(driver);
    }

}
