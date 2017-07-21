import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Katherine on 18.07.2017.
 */
public class OLXManipulation {

    private static final String EMAIL = "kfortesting@gmail.com";
    private static final String PASSWORD = "1q2w3e4r5t";
    private static final String CHERNIVTSI = "Чернівці";
    private static final String DESCRIPTION_VALUE = "Слухняний, полюбляє купатись";
    private static final String TITLE_VALUE = "Купи слона";
    private static final String Y_OFFSET_VALUE = "2000";
    private static final String NAME_VALUE = "Катерина";

    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://olx.ua");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldAddNewAdAndDeleteIt() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.pressNewAdButton().authorization(EMAIL, PASSWORD);
        NewAdScreen newAdScreen = new NewAdScreen(driver);
        newAdScreen.enterTitleIntoInput(TITLE_VALUE)
                .enterDescription(DESCRIPTION_VALUE)
                .selectRubric()
                .scrollTo(Y_OFFSET_VALUE)
                .enterCityIntoInput(CHERNIVTSI)
                .enterNameIntoInput(NAME_VALUE)
                .pressSaveButton();
    }
}
