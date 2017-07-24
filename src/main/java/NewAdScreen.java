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

    private static final String VALUE_ATTRIBUTE = "value";
    private WebDriver driver;

    @FindBy(id = "add-title")
    private WebElement titleInput;

    @FindBy(id = "choose-category-ilu")
    private WebElement categorySelect;

    @FindBy(id = "cat-35")
    private WebElement animalCategory;

    @FindBy(css = "#category-35 .overview li:nth-child(9)")
    private WebElement otherAnimalCategory;

    @FindBy(css = "#parameter-price + input[type = 'text']")
    private WebElement priceInput;

    @FindBy(id = "targetid_private_business")
    private WebElement privateBusinessSelectBox;

    @FindBy(css = "#targetid_private_business ul li:nth-child(2)")
    private WebElement privateType;

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

    public NewAdScreen enterTitle(String titleValue) {
        titleInput.sendKeys(titleValue);
        return this;
    }

    public NewAdScreen selectCategory() {
        categorySelect.click();
        animalCategory.click();
        if (otherAnimalCategory.isDisplayed())
            otherAnimalCategory.click();
        return this;
    }

    public NewAdScreen enterPrice(String priceValue) {
        (new WebDriverWait(driver, 10))
                .until((WebDriver webDriver) -> priceInput.isDisplayed());
        priceInput.sendKeys(priceValue);
        return this;
    }

    public NewAdScreen selectPrivateType() {
        privateBusinessSelectBox.click();
        privateType.click();
        return this;
    }

    public NewAdScreen enterDescription(String descriptionValue) {
        descriptionArea.sendKeys(descriptionValue);
        return this;
    }

    public NewAdScreen enterCity(String cityValue) {
        if(cityInput.getAttribute(VALUE_ATTRIBUTE).isEmpty()){
            cityInput.sendKeys(cityValue);
            if (suggestedcity.isDisplayed()) {
                suggestedcity.click();
            }
        }
        return this;
    }

    public NewAdScreen scrollTo(String yOffsetValue) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, " + yOffsetValue + ");");
        return this;
    }

    public NewAdScreen enterName(String nameValue) {
        if (nameInput.getAttribute(VALUE_ATTRIBUTE).isEmpty()) {
            nameInput.sendKeys(nameValue);
        }
        return this;
    }

    public AdvertisesScreen pressSave() {
        saveButton.click();
        return new AdvertisesScreen(driver);
    }


}
