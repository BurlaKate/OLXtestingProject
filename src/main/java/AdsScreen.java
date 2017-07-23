import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 19.07.2017.
 */
public class AdsScreen {

    WebDriver driver;

    @FindBy(css = "a[data-promo='withoutpromo']")
    private WebElement doNotAdvertiseButton;

    @FindBy(css = ".olx-multipay__title")
    private WebElement adsTitle;

    AdsScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private AdsScreen scrollTo(String yOffsetValue) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, " + yOffsetValue + ");");
        return this;
    }

    public AdsScreen doNotAdvertise(String titleValue) {
        if (adsTitle.getText().equals(titleValue)) {
            scrollTo("1000");
            doNotAdvertiseButton.click();
        }
        return this;
    }


}
