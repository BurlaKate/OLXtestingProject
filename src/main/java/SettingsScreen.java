import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 26.07.2017.
 */
public class SettingsScreen {

    private static final String VALUE_ATTRIBUTE = "value";
    private WebDriver driver;
    @FindBy(id = "se_changeDefault")
    private WebElement userInfoTab;

    @FindBy(id = "geoCity")
    private WebElement cityInput;

    @FindBy(css = ".autosuggest-geo-div li:nth-child(3) a[href='#']")
    private WebElement suggectedCity;

    @FindBy(id = "defaultPerson")
    private WebElement nameInput;

    @FindBy(id = "submitDefault")
    private WebElement saveButton;

    SettingsScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SettingsScreen openUserInfoTab() {
        userInfoTab.click();
        return this;
    }

    public String getCityTitleFromCityInput() {
        return cityInput.getAttribute(VALUE_ATTRIBUTE);
    }

    public String getUserNameFromNameInput() {
        return nameInput.getAttribute(VALUE_ATTRIBUTE);
    }

    public SettingsScreen changeCity(String newCityName) {
        cityInput.clear();
        cityInput.sendKeys(newCityName);
        cityInput.click();
//        Actions builder = new Actions(driver);
//        builder.moveToElement(suggectedCity)
//                .perform();
//        suggectedCity.click();
        return this;
    }

    public SettingsScreen changeName(String newName) {
        nameInput.clear();
        nameInput.sendKeys(newName);
        return this;
    }

    public SettingsScreen pressSaveButton() {
        saveButton.click();
        return this;
    }
}
