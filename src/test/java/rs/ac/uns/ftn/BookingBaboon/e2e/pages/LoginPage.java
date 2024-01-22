package rs.ac.uns.ftn.BookingBaboon.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private static String PAGE_URL="http://localhost:4200/login";

    @FindBy(css = ".app-title")
    WebElement heading;

    @FindBy(css = "input[ng-reflect-name='username']")
    WebElement usernameInput;

    @FindBy(css = "input[ng-reflect-name='password']")
    WebElement passwordInput;

    @FindBy(id = "login")
    WebElement logInButton;

    @FindBy(css = "button[ng-reflect-router-link='/host/accommodations']")
    WebElement hostAccommodationsButton;

    @FindBy(css = "button[ng-reflect-router-link='/guest,5,reservations']")
    WebElement reservationsButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(heading, "Booking Baboon"));

        return isOpened;
    }

    public void logIn(String username, String password){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        passwordInput.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.visibilityOf(hostAccommodationsButton));
    }

    public void logInGuest(String username, String password){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        passwordInput.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(reservationsButton));
    }
}
