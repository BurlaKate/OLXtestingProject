import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by Katherine on 26.07.2017.
 */
public class SettingsScreen {

    private static final String VALUE_ATTRIBUTE = "value";
    private WebDriver driver;
    @FindBy(id = "se_changeDefault")
    private WebElement userInfoTab;

    @FindBy(id = "se_changePassword")
    private WebElement changePasswordTab;

    @FindBy(id = "se_deleteAccount")
    private WebElement deleteAnAccountTab;

    @FindBy(id = "se_chPassword")
    private WebElement newPasswordInput;

    @FindBy(id = "se_chRepeatPassword")
    private WebElement repeatPasswordInput;

    @FindBy(id = "passwordInput")
    private WebElement changePasswordButton;

    @FindBy(css = ".xxx-large")
    private WebElement changePasswordLabel;

    @FindBy(css = ".margin20_0 #deleteAccount")
    private WebElement deleteAnAccountButton;

    @FindBy(id = "geoCity")
    private WebElement cityInput;

    @FindBy(css = ".autosuggest-geo-div ul li:nth-child(3)")
    private WebElement suggestedCity;

    @FindBy(id = "defaultPerson")
    private WebElement nameInput;

    @FindBy(id = "submitDefault")
    private WebElement saveButton;

    @FindBy(id = "removeInput")
    private WebElement sendOnEmailButton;

    SettingsScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SettingsScreen openUserInfoTab() {
        userInfoTab.click();
        return this;
    }

    public SettingsScreen openChangePasswordTab() {
        changePasswordTab.click();
        return this;
    }

    public SettingsScreen openDeleteAnAccountTab() {
        deleteAnAccountTab.click();
        return this;
    }

    public String getCityTitleFromCityInput() {
        return cityInput.getAttribute(VALUE_ATTRIBUTE);
    }

    public String getUserNameFromNameInput() {
        return nameInput.getAttribute(VALUE_ATTRIBUTE);
    }

    private SettingsScreen changeCity(String newCityName) {
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> cityInput.isDisplayed());
        cityInput.clear();
        cityInput.sendKeys(newCityName);
        if (getUserNameFromNameInput().equals("Петро")) {
            suggestedCity = driver.findElement(By.cssSelector(".autosuggest-geo-div ul li:nth-child(1)"));
        } else {
            suggestedCity = driver.findElement(By.cssSelector(".autosuggest-geo-div ul li:nth-child(3)"));
        }
        Actions builder = new Actions(driver);
        builder.moveToElement(suggestedCity)
                .click()
                .moveByOffset(50, 0)
                .click()
                .perform();
        return this;
    }

    private SettingsScreen changeName(String newName) {
        nameInput.clear();
        nameInput.sendKeys(newName);
        return this;
    }

    private SettingsScreen pressSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
        return this;
    }

    public SettingsScreen fillNameAndPasswordInputs(String newName, String newCityName) {
        changeCity(newCityName)
                .changeName(newName)
                .pressSaveButton();
        return this;
    }

    public SettingsScreen enterNewPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    public SettingsScreen enterRepeatedPassword(String repeatedPassword) {
        repeatPasswordInput.sendKeys(repeatedPassword);
        return this;
    }

    public SettingsScreen pressChangePassword() {
        changePasswordButton.click();
        return this;
    }

    public String getTextFromChangePasswordLabel() {
        (new WebDriverWait(driver, 20))
                .until((WebDriver webDriver) -> changePasswordLabel.isDisplayed());
        return changePasswordLabel.getText();
    }

    public SettingsScreen pressDeleteAnAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(deleteAnAccountButton));
        deleteAnAccountButton.click();
        return this;
    }

    public SettingsScreen pressSendOnEmailButton() {
        sendOnEmailButton.click();
        return this;
    }

    public SettingsScreen pressSendOnEmailButtonOnConfirmationPopup() {
        Set<String> allWindows = driver.getWindowHandles();
        if (!allWindows.isEmpty()) {
            for (String windowId : allWindows) {
                driver.switchTo().window(windowId);
                try {
                    pressSendOnEmailButton();
                    break;
                } catch (NoSuchWindowException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }
}
