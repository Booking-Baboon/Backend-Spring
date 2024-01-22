package rs.ac.uns.ftn.BookingBaboon.e2e.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import rs.ac.uns.ftn.BookingBaboon.domain.users.Guest;
import rs.ac.uns.ftn.BookingBaboon.e2e.pages.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SpringBootTest
public class CancelReservationTest extends TestBase {
    private final String guestUsername = "charlie.brown@example.com";
    private final String guestPassword = "charliespass";

    @PersistenceContext
    EntityManager entityManager;

    public void rollbackDatabase(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("rollback.sql");
        String rollbackScript = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        entityManager.createNativeQuery(rollbackScript).executeUpdate();
    }

    public void loginAsGuest() {
        HomePage homePage = new HomePage(driver);
        if (!homePage.isLoggedIn()) {
            Assert.assertTrue(homePage.isPageOpened());
            homePage.goToLogin();
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isPageOpened());
            loginPage.logInGuest(guestUsername, guestPassword);
        }
    }

    @Test
    public void navigateToChangeAccommodationAvailability(){
        loginAsGuest();
        HomePage homePageGuest = new HomePage(driver);
        Assert.assertTrue(homePageGuest.isPageOpened());
        homePageGuest.goToReservations();
        GuestsReservationsPage guestsReservationsPage = new GuestsReservationsPage(driver);
        Assert.assertTrue(guestsReservationsPage.isPageOpened());
        guestsReservationsPage.iterateThroughTable();

    }

/*    @Test
    public void test() {
        GuestsReservationsPage guestsReservationsPage = navigateToChangeAccommodationAvailability();
    }*/
}