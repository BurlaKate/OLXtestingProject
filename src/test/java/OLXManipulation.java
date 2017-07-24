import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Katherine on 18.07.2017.
 */
public class OLXManipulation {

    private static final String EMAIL = "kfortesting@gmail.com";
    private static final String PASSWORD = "1q2w3e4r5t";
    private static final String CHERNIVTSI = "Чернівці";
    private static final String DESCRIPTION_VALUE = "Слухняний, полюбляє купатись";
    private static final String TITLE_VALUE = "Купи слона";
    private static final String Y_OFFSET_VALUE = "1500";
    private static final String NAME_VALUE = "Катерина";
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
//        driver.quit();
    }

    @Test
    public void shouldAddNewAdAndDeleteIt() {
        NewAdScreen newAdScreen = new NewAdScreen(driver);
        newAdScreen.enterTitle(TITLE_VALUE)
                .enterDescription(DESCRIPTION_VALUE)
                .selectCategory()
                .enterPrice(PRICE_VALUE)
                .selectPrivateType()
                .scrollTo(Y_OFFSET_VALUE)
                .enterCity(CHERNIVTSI)
                .enterName(NAME_VALUE)
                .pressSave();
        AdvertisesScreen advertScreen = new AdvertisesScreen(driver);
        advertScreen.doNotAdvertise(TITLE_VALUE)
                .openUserAdsScreen();

    }
}
