import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Katherine on 18.07.2017.
 */
public class OLXManipulation {

    public static final String EMAIL = "kfortesting@gmail.com";
    public static final String PASSWORD = "1q2w3e4r5t";

    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void openPage() throws InterruptedException {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openPage().pressNewAdButton().authorization(EMAIL, PASSWORD);
        Thread.sleep(2000);
    }
}
