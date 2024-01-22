package rs.ac.uns.ftn.BookingBaboon.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage {
    private WebDriver driver;
    private static String PAGE_URL="http://localhost:4200/";

    @FindBy(css = ".app-title")
    WebElement heading;
    @FindBy(css = "input[formcontrolname='city']")
    WebElement cityField;

    @FindBy(css = "button[aria-label='Open calendar']")
    WebElement openCalendarButton;

    @FindBy(css = ".cdk-overlay-pane.mat-datepicker-popup")
    WebElement calendarPopUp;

    @FindBy(css = "button[aria-label='Choose month and year']")
    WebElement chooseMonthYearButton;

    @FindBy(css = "input[formcontrolname='guestNum']")
    WebElement guestNumField;

    @FindBy(css = "#filter-button")
    WebElement filterButton;

    @FindBy(css = "#filter-box")
    WebElement filterBox;

    @FindBy(css = "input[formcontrolname='minPrice']")
    WebElement minPriceField;

    @FindBy(css = "input[formcontrolname='maxPrice']")
    WebElement maxPriceField;

    @FindBy(css = "#rating-boxes")
    WebElement ratingBoxesSection;

    @FindBy(css = "#type-box")
    WebElement typeBoxesSection;

    @FindBy(css = "#amenities-box")
    WebElement amenityBoxesSection;

    @FindBy(css = "#apply-button")
    WebElement applyFilterButton;

    @FindBy(css = "#accommodationList")
    WebElement accommodationListContainer;

    @FindBy(css = "button[type='submit']")
    WebElement searchButton;

    @FindBy(css = "button[ng-reflect-router-link='/login']")
    WebElement logInButton;

    @FindBy(css = "#right-section>button[color='warn']")
    WebElement logOutButton;

    @FindBy(css = "button[ng-reflect-router-link='/host/accommodations']")
    WebElement hostAccommodationsButton;

    @FindBy(css = "button[ng-reflect-router-link='/guest,5,reservations']")
    WebElement reservationsButton;

    @FindBy(css = "table tbody tr")
    List<WebElement> reservationRows;


    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(heading, "Booking Baboon"));

        return isOpened;
    }

    public void enterCity(String city){
        cityField.clear();
        cityField.sendKeys(city);
    }


    public void enterPeriod(String checkin, String checkout){
        openCalendarButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(calendarPopUp));

        chooseMonthYearButton.click();
        calendarPopUp.findElement(new By.ByCssSelector("button[aria-label='2024']")).click();
        calendarPopUp.findElement(new By.ByCssSelector("button[aria-label='April 2024']")).click();
        calendarPopUp.findElement(new By.ByCssSelector("button[aria-label='April 3, 2024']")).click();
        calendarPopUp.findElement(new By.ByCssSelector("button[aria-label='April 6, 2024']")).click();
    }

    public void enterGuestNum(Integer guestNum){
        guestNumField.clear();
        guestNumField.sendKeys(String.valueOf(guestNum));
    }

    public void openFiltersBox(){
        filterButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(filterBox));
    }

    public void enterMinPrice(Integer value){
        minPriceField.clear();
        minPriceField.sendKeys(String.valueOf(value));
    }

    public void enterMaxPrice(Integer value){
        maxPriceField.clear();
        maxPriceField.sendKeys(String.valueOf(value));
    }

    public void enterMinRating(Integer value){
        String locator = String.format("//*[text()='%d+']", value);
        ratingBoxesSection.findElement(By.xpath(locator)).click();
    }

    public void enterAccommodationTypes(List<String> types){
        for(String type: types){
            String locator = String.format("//*[text()='%s']", type);
            typeBoxesSection.findElement(By.xpath(locator)).click();
        }
    }

    public void enterAmenities(List<String> amenities){
        for(String type: amenities){
            String locator = String.format("//*[text()='%s']", type);
            amenityBoxesSection.findElement(By.xpath(locator)).click();
        }
    }

    public void pressSearch(){
        searchButton.click();
    }

    public void applyFilter(){
        applyFilterButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(filterBox));
    }

    public List<WebElement> getResultAccommodations(){
        return accommodationListContainer.findElements(By.cssSelector("app-accommodation-card"));
    }

    public boolean verifyResultsCity(String value){
        List<WebElement> accommodationCards = getResultAccommodations();
        boolean isCorrect = true;
        for(WebElement accommodationCard: accommodationCards){
            String location = accommodationCard.findElement(new By.ByCssSelector("p.location")).getText();
            String city = location.split(", ")[1];
            if(!city.equals(value)){
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

    public boolean verifyResultsRating(Integer value){
        List<WebElement> accommodationCards = getResultAccommodations();
        boolean isCorrect = true;
        for(WebElement accommodationCard: accommodationCards){
            String rating = accommodationCard.findElement(new By.ByCssSelector("p.rating")).getText();
            if(Float.parseFloat(rating) < value){
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

    public boolean accommodationsExist(){
        return !getResultAccommodations().isEmpty();
    }

    public void goToLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }

    public boolean isLoggedIn(){
        try{
            return (new WebDriverWait(driver, Duration.ofSeconds(1)))
                    .until(ExpectedConditions.textToBePresentInElement(logOutButton,"Log Out"));
        }catch(Exception e){
            return false;
        }


    }

    public void goToHostAccommodations(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(hostAccommodationsButton)).click();
    }

    public void goToReservations() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(reservationsButton)).click();
    }

    public void iterateThroughTable() {

        for (WebElement element : reservationRows) {
            System.out.println("aaa" + element.getText());
        }
    }
}
