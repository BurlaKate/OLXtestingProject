import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Katherine on 18.07.2017.
 */
public class OLXManipulation {

    private static final String EMAIL = "kfortesting@gmail.com";
    private static final String PASSWORD = "1q2w3e4r5t";
    private static final String NEW_PASSWORD = "12345qwerty";
    private static final String NEW_PASSWORD_MESSAGE = "Новый пароль";
    private static final String CHERNIVTSI = "Чернівці";
    private static final String LVIV = "Львів";
    private static final String DESCRIPTION_VALUE = "Слухняний, полюбляє купатись";
    private static final String TITLE_VALUE = "Купи слона";
    private static final String Y_OFFSET_VALUE = "1500";
    private static final String NAME_VALUE = "Катерина";
    private static final String CHANGED_NAME_VALUE = "Петро";
    private static final String PRICE_VALUE = "10000";
    private static final String OLX_URL = "http://olx.ua";

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(OLX_URL);
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.authorization(EMAIL, PASSWORD);
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldAddNewAdAndDeleteIt() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.pressNewAdButton();
        NewAdScreen newAdScreen = new NewAdScreen(driver);
        newAdScreen.enterTitle(TITLE_VALUE)
                .enterDescription(DESCRIPTION_VALUE)
                .selectCategory()
                .enterPrice(PRICE_VALUE)
                .selectPrivateType()
                .scrollTo(Y_OFFSET_VALUE)
                .enterCity(CHERNIVTSI)
                .enterName(NAME_VALUE)
                .pressSaveButton().doNotAdvertise(TITLE_VALUE);
        UserTopMenu userTopMenu = new UserTopMenu(driver);
        userTopMenu.selectUserTopMenuItem(userTopMenu.selectAdsItem());
        AdsTabScreen adsTabScreen = new AdsTabScreen(driver);
        adsTabScreen.switchToExpectedTabs();
        assertThat(adsTabScreen.getTitleFromTheTable(), is(TITLE_VALUE));
        assertThat(adsTabScreen.getPriceFromTheTable(), is(PRICE_VALUE));
        adsTabScreen.pressCancelButton()
                .switchToNotActiveTabs()
                .pressDeleteButton();
    }

    @Test
    public void shouldChangeUserInfo() {
        UserTopMenu userTopMenu = new UserTopMenu(driver);
        userTopMenu.selectUserTopMenuItem(userTopMenu.selectSettingsItem());
        SettingsScreen settingsScreen = new SettingsScreen(driver);
        settingsScreen.openUserInfoTab();
        String oldCityValue = settingsScreen.getCityTitleFromCityInput();
        String oldNameValue = settingsScreen.getUserNameFromNameInput();
        System.out.println(oldCityValue);
        System.out.println(oldNameValue);
        settingsScreen.changeName(CHANGED_NAME_VALUE)
                .changeCity(LVIV)
                .pressSaveButton();
        driver.navigate().refresh();
        System.out.println(settingsScreen.getCityTitleFromCityInput());
        System.out.println(settingsScreen.getUserNameFromNameInput());
        settingsScreen.openUserInfoTab();
        Assert.assertNotEquals(oldCityValue, settingsScreen.getCityTitleFromCityInput(), "Old city but should be new!");
        Assert.assertNotEquals(oldNameValue, settingsScreen.getUserNameFromNameInput(), "Old name but should be new!");

    }

    @Test
    public void shouldChangeThePassword() {
        UserTopMenu userTopMenu = new UserTopMenu(driver);
        userTopMenu.selectUserTopMenuItem(userTopMenu.selectSettingsItem());
        SettingsScreen settingsScreen = new SettingsScreen(driver);
        settingsScreen.openChangePasswordTab()
                .enterNewPassword(NEW_PASSWORD)
                .enterRepeatedPassword(NEW_PASSWORD)
                .pressChangePassword();
        assertThat(settingsScreen.getTextFromChangePasswordLabel(), is(NEW_PASSWORD_MESSAGE));
    }

    @Test
    public void shouldDeleteCurrentAccount() {
        UserTopMenu userTopMenu = new UserTopMenu(driver);
        userTopMenu.selectUserTopMenuItem(userTopMenu.selectSettingsItem());
        SettingsScreen settingsScreen = new SettingsScreen(driver);
        settingsScreen.openDeleteAnAccountTab()
                .pressDeleteAnAccountButton();

    }


}
