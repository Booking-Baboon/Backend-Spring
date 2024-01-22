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
    private static String PAGE_URL = "http://localhost:4200/guest/5/reservations";

    @FindBy(css = ".app-title")
    WebElement heading;
    @FindBy(css = "table tbody tr")
    List<WebElement> reservationRows;

    @FindBy(css = "#container")
    WebElement reservationsTable;

    public GuestsReservationsPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(heading, "Booking Baboon"));

        return isOpened;
    }

    public void ClickCancel(String id) {
        // Find the table using the provided locator
        WebElement table = reservationsTable;
        WebElement nextButton = driver.findElement(By.cssSelector("button[aria-label='Next page']"));
        WebElement reservation;
        while (true) {
            reservation = getReservationRow(table, id);

            if (nextButton.isEnabled() && reservation == null) {
                nextButton.click();
            } else if (reservation != null) {
                WebElement cancelButton = getCancelButtonFromRow(reservation);
                cancelButton.click();
                break;
            } else {
                break;
            }
        }
    }

    private WebElement getReservationRow(WebElement table, String id) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            String[] parts = row.getText().split("\n");
            String rowId = parts[0];
            System.out.println("id: " + parts[0]);

            if (rowId.equals(id)) {
                return row;
            }

        }
        return null;
    }

    public boolean checkIfReservationStatus(String id, String status) {
        WebElement table = reservationsTable;
        WebElement nextButton = driver.findElement(By.cssSelector("button[aria-label='Next page']"));
        WebElement reservation;
        while (true) {
            reservation = getReservationRow(table, id);

            if (nextButton.isEnabled() && reservation == null) {
                nextButton.click();

            } else if (reservation != null) {
                return getStatusFromRow(reservation).equals(status);

            } else {
                break;
            }
        }
        return false;
    }

    private String getStatusFromRow(WebElement row) {
        String[] parts = row.getText().split("\n");
        return parts[7];
    }

    private WebElement getCancelButtonFromRow(WebElement row) {
        return row.findElement(By.id("cancel-reservation-button"));
    }

    public boolean isCancelButtonEnabled(String id) {
        WebElement table = reservationsTable;
        WebElement nextButton = driver.findElement(By.cssSelector("button[aria-label='Next page']"));
        WebElement reservation;
        while (true) {
            reservation = getReservationRow(table, id);

            if (nextButton.isEnabled() && reservation == null) {
                nextButton.click();

            } else if (reservation != null) {
                return getCancelButtonFromRow(reservation).isEnabled();

            } else {
                break;
            }
        }
        return false;
    }
}
