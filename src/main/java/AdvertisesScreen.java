import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 19.07.2017.
 */
public class AdvertisesScreen {

    private static final String Y_OFFSET_VALUE = "1000";
    private WebDriver driver;

    @FindBy(css = "a[data-promo='withoutpromo']")
    private WebElement doNotAdvertiseButton;

    @FindBy(css = ".olx-multipay__title")
    private WebElement adsTitle;

    @FindBy(id = "topLoginLink")
    private WebElement topUserLink;

    @FindBy(css = "#userLoginBox ul li:nth-child(1)")
    private WebElement adsItemInTheList;

    AdvertisesScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private AdvertisesScreen scrollTo(String yOffsetValue) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, " + yOffsetValue + ");");
        return this;
    }

    public AdvertisesScreen doNotAdvertise(String titleValue) {
        if (adsTitle.getText().equals(titleValue)) {
            scrollTo(Y_OFFSET_VALUE);
            doNotAdvertiseButton.click();
        }
        return this;
    }

    public AdvertisesScreen openUserAdsScreen() {
        Actions builder = new Actions(driver);
        builder.moveToElement(topUserLink)
                .moveToElement(adsItemInTheList)
                .click()
                .perform();


        return this;
    }


}
