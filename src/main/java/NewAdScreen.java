import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Katherine on 19.07.2017.
 */
public class NewAdScreen {

    WebDriver driver;

    @FindBy(id = "add-title")
    private WebElement titleInput;

    @FindBy(id = "choose-category-ilu")
    private WebElement rubricSelect;

    @FindBy(id = "add-description")
    private WebElement descriptionArea;

    @FindBy(id = "mapAddress")
    private WebElement cityInput;

    @FindBy(css = ".autosuggest-geo-div ul li:nth-child(2)")
    private WebElement suggestedcity;

    @FindBy(id = "add-person")
    private WebElement nameInput;

    @FindBy(id = "save")
    private WebElement saveButton;

    NewAdScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewAdScreen enterTitleIntoInput(String titleValue) {
        titleInput.sendKeys(titleValue);
        return this;
    }

    public NewAdScreen selectRubric() {
//        (new WebDriverWait(driver, 10))
//                .until((WebDriver webDriver) -> rubricSelect.isDisplayed());
        rubricSelect.click();

        return this;
    }

    public NewAdScreen enterDescription(String descriptionValue) {
        descriptionArea.sendKeys(descriptionValue);
        return this;
    }

    public NewAdScreen enterCityIntoInput(String inputValue) {
        cityInput.sendKeys(inputValue);
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> suggestedcity.isDisplayed());
        suggestedcity.click();
        return this;
    }

    public NewAdScreen scrollTo(String yOffsetValue) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, " + yOffsetValue + ");");
        return this;
    }

    public NewAdScreen enterNameIntoInput(String nameValue) {
        nameInput.sendKeys(nameValue);
        return this;
    }

    public NewAdScreen pressSaveButton() {
        saveButton.click();
        return this;
    }


}
