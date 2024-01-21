package rs.ac.uns.ftn.BookingBaboon.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.testng.Assert;
import rs.ac.uns.ftn.BookingBaboon.config.security.JwtTokenUtil;
import rs.ac.uns.ftn.BookingBaboon.domain.reservation.Reservation;
import rs.ac.uns.ftn.BookingBaboon.domain.reservation.ReservationStatus;
import rs.ac.uns.ftn.BookingBaboon.domain.shared.TimeSlot;
import rs.ac.uns.ftn.BookingBaboon.dtos.accommodation_handling.accommodation.AccommodationReference;
import rs.ac.uns.ftn.BookingBaboon.dtos.accommodation_handling.accommodation.AccommodationResponse;
import rs.ac.uns.ftn.BookingBaboon.dtos.reservation.ReservationCreateRequest;
import rs.ac.uns.ftn.BookingBaboon.dtos.reservation.ReservationResponse;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.guests.GuestReference;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class ReservationControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;  // Inject your JwtTokenUtil

    @Test
    @DisplayName("Should Create Reservation When making POST request to /api/v1/reservations")
    public void shouldCreateReservation() {
        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest(
                new AccommodationReference(1L),
                new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),
                new GuestReference(5L),
                320F
        );

        String token = jwtTokenUtil.generateTokenForGuest(5L, "charlie.brown@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<ReservationCreateRequest> requestEntity = new HttpEntity<>(reservationCreateRequest, headers);

        ResponseEntity<ReservationResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/reservations",
                HttpMethod.POST,
                requestEntity,
                ReservationResponse.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        ReservationResponse createdReservation = responseEntity.getBody();
        assertNotNull(createdReservation);
    }

    @Test
    @DisplayName("Should approve Reservation When making PUT request to api/v1/reservations/{id}/approve")
    public void shouldApproveReservation() {
        Long reservationId = 26L;

        String token = jwtTokenUtil.generateTokenForGuest(5L, "charlie.brown@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ReservationResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/reservations/{id}/approve",
                HttpMethod.PUT,
                requestEntity,
                ReservationResponse.class,
                reservationId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ReservationResponse approvedReservation = responseEntity.getBody();
        assertNotNull(approvedReservation);
        Assert.assertEquals(ReservationStatus.Approved.toString(), approvedReservation.getStatus());
    }

    @Test
    @DisplayName("Should Get NotFound status for reservation with invalid ID When making PUT request to api/v1/reservations/{id}/approve")
    public void shouldNotApproveReservationWithInvalidId() {
        Long reservationId = -1L;

        String token = jwtTokenUtil.generateTokenForGuest(5L, "charlie.brown@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ReservationResponse> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/reservations/{id}/approve",
                HttpMethod.PUT,
                requestEntity,
                ReservationResponse.class,
                reservationId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
