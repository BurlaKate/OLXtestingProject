import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 19.07.2017.
 */
public class UserTopMenu {

    private WebDriver driver;

    @FindBy(id = "topLoginLink")
    private WebElement topUserLink;

    @FindBy(css = "#userLoginBox ul li:nth-child(1)")
    private WebElement adsItemInTheList;

    @FindBy(id = "login-box-settings")
    private WebElement settingsItemInTheList;

    UserTopMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement selectAdsItem() {
        return adsItemInTheList;
    }

    public WebElement selectSettingsItem() {
        return settingsItemInTheList;
    }

    public AdsTabScreen selectUserTopMenuItem(WebElement menuElement) {
        Actions builder = new Actions(driver);
        builder.moveToElement(topUserLink)
                .moveToElement(menuElement)
                .click()
                .perform();
        return new AdsTabScreen(driver);
    }


}
