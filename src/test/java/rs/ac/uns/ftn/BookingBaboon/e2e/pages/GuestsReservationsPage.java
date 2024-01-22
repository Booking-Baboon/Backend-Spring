package rs.ac.uns.ftn.BookingBaboon.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GuestsReservationsPage {
    private WebDriver driver;
    private static String PAGE_URL="http://localhost:4200/5/reservations";

    @FindBy(css = ".app-title")
    WebElement heading;
    @FindBy(css = "table tbody tr")
    List<WebElement> reservationRows;

    @FindBy(css = "#container")
    WebElement reservationsTable;

    public GuestsReservationsPage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(heading, "Booking Baboon"));

        return isOpened;
    }

    public void iterateThroughTable() {
        // Find the table using the provided locator
        WebElement table = reservationsTable;
        // Find all rows within the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("test!!!!!!!!!!");
        // Iterate through each row and print the text
        for (WebElement row : rows) {
            System.out.println("Row: " + row.getText());
        }
    }
}
