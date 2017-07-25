import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Katherine on 24.07.2017.
 */
public class AdsTabScreen {

    private static final String TITLE_ATTRIBUTE = "title";
    private static final String DATA_PRICE_ATTRIBUTE = "data-price";
    private WebDriver driver;

    @FindBy(css = "#globalLinks li:nth-child(3) .fbold")
    private WebElement notActiveTabTitle;

    @FindBy(css = ".tbody td:nth-child(4) .table .normal")
    private WebElement expectedAdTitle;

    @FindBy(css = ".tbody td:nth-child(5)")
    private WebElement expectedAdPrice;

    @FindBy(css = "#adsTable .tbody tr:nth-child(2) td:nth-child(3) a[href='#']")
    private WebElement cancelButton;

    @FindBy(css = ".tbody td:nth-child(4) a[href='#']")
    private WebElement deleteButton;

    AdsTabScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitleFromTheTable() {
        return expectedAdTitle.getAttribute(TITLE_ATTRIBUTE);
    }

    public String getPriceFromTheTable() {
        return expectedAdPrice.getAttribute(DATA_PRICE_ATTRIBUTE);
    }

    public AdsTabScreen pressCancelButton() {
        cancelButton.click();
        return this;
    }

    public AdsTabScreen switchToNotActiveTabs() {
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> notActiveTabTitle.isDisplayed());
        notActiveTabTitle.click();
        return this;
    }

    public AdsTabScreen pressDeleteButton() {
        deleteButton.click();
        return this;
    }


}
