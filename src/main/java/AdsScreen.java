import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 19.07.2017.
 */
public class AdsScreen {

    WebDriver driver;

    @FindBy(css = "a[class='button big4 br3 add cfff large rel']")
    private WebElement newAdSmallerButton;

    AdsScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdsScreen pressNewAdSmallerButton() {
        newAdSmallerButton.click();
        return this;
    }


}
