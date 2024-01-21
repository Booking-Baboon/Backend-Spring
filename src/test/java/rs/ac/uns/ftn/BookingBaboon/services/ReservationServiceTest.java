package rs.ac.uns.ftn.BookingBaboon.services.reservation;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.BookingBaboon.config.TestSecurityConfig;
import rs.ac.uns.ftn.BookingBaboon.domain.accommodation_handling.Accommodation;
import rs.ac.uns.ftn.BookingBaboon.domain.notifications.Notification;
import rs.ac.uns.ftn.BookingBaboon.domain.notifications.NotificationType;
import rs.ac.uns.ftn.BookingBaboon.domain.reservation.Reservation;
import rs.ac.uns.ftn.BookingBaboon.domain.reservation.ReservationStatus;
import rs.ac.uns.ftn.BookingBaboon.domain.shared.TimeSlot;
import rs.ac.uns.ftn.BookingBaboon.domain.users.Guest;
import rs.ac.uns.ftn.BookingBaboon.domain.users.Host;
import rs.ac.uns.ftn.BookingBaboon.domain.users.User;
import rs.ac.uns.ftn.BookingBaboon.repositories.reservation_handling.IReservationRepository;
import rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.interfaces.IAccommodationService;
import rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.interfaces.IAvailablePeriodService;
import rs.ac.uns.ftn.BookingBaboon.services.notifications.INotificationService;
import rs.ac.uns.ftn.BookingBaboon.services.users.interfaces.IUserService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private IAccommodationService accommodationService;

    @MockBean
    private INotificationService notificationService;

    @MockBean
    private IReservationRepository reservationRepository;

    @MockBean
    private  IAvailablePeriodService availablePeriodService;

    @MockBean
    private IUserService userService;

    @Test
    public void testCreateValidReservation() {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        Host host = new Host();
        host.setId(1L);
        accommodation.setHost(host);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setAccommodation(accommodation);
        reservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)));

        when(accommodationService.get(reservation.getAccommodation().getId())).thenReturn(accommodation);
        when(userService.get(1L)).thenReturn(host);

        Reservation result = reservationService.create(reservation);

        assertNotNull(result);
        assertEquals(ReservationStatus.Pending, result.getStatus());

        verify(accommodationService, times(1)).get(reservation.getAccommodation().getId());
        verify(notificationService, times(1)).create(any(Notification.class));
    }

    @Test
    public void testCreateInvalidReservation() {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setAccommodation(accommodation);
        reservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)));

        when(accommodationService.get(reservation.getAccommodation().getId())).thenReturn(accommodation);

        doThrow(new ConstraintViolationException("", new HashSet<>())).when(reservationRepository).save(reservation);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.create(reservation));

        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatusCode().value());

        verify(reservationRepository, times(1)).save(reservation);
        verify(accommodationService, never()).get(reservation.getAccommodation().getId());
        verify(notificationService, never()).create(any(Notification.class));
    }

    @Test
    public void testHandleAutomaticAcceptanceAutomaticallyAccepted() {
        Guest guest = new Guest();
        guest.setId(1L);

        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setIsAutomaticallyAccepted(true);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setAccommodation(accommodation);
        reservation.setStatus(ReservationStatus.Pending);
        reservation.setGuest(guest);
        reservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)));

        when(accommodationService.get(reservation.getAccommodation().getId())).thenReturn(accommodation);
        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.handleAutomaticAcceptance(reservation);

        assertNotNull(result);
        assertEquals(ReservationStatus.Approved, result.getStatus());

        verify(accommodationService, times(2)).get(reservation.getAccommodation().getId());
        verify(reservationRepository, times(2)).findById(reservation.getId());
    }

    @Test
    public void testHandleAutomaticAcceptanceNotAutomaticallyAccepted() {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setIsAutomaticallyAccepted(false);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setAccommodation(accommodation);

        when(accommodationService.get(reservation.getAccommodation().getId())).thenReturn(accommodation);

        Reservation result = reservationService.handleAutomaticAcceptance(reservation);

        assertNotNull(result);
        assertEquals(ReservationStatus.Pending, result.getStatus());

        verify(accommodationService, times(1)).get(reservation.getAccommodation().getId());
    }

    @Test
    public void testApproveReservation() {
        Guest guest = new Guest();
        guest.setId(1L);

        Accommodation accommodation = new Accommodation();
        accommodation.setId(1L);
        accommodation.setIsAutomaticallyAccepted(false);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setGuest(guest);
        reservation.setAccommodation(accommodation);
        reservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)));

        Reservation firstOverlappingReservation = new Reservation();
        firstOverlappingReservation.setId(2L);
        firstOverlappingReservation.setAccommodation(accommodation);
        firstOverlappingReservation.setGuest(guest);
        firstOverlappingReservation.setStatus(ReservationStatus.Pending);
        firstOverlappingReservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 6)));

        Reservation notOverlappingReservation = new Reservation();
        notOverlappingReservation.setId(3L);
        notOverlappingReservation.setAccommodation(accommodation);
        notOverlappingReservation.setGuest(guest);
        notOverlappingReservation.setStatus(ReservationStatus.Pending);
        notOverlappingReservation.setTimeSlot(new TimeSlot(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 8)));

        //mocks
        when(reservationRepository.findAllByAccommodationId(eq(accommodation.getId())))
                .thenReturn(Arrays.asList(reservation, firstOverlappingReservation, notOverlappingReservation));

        when(reservationRepository.findById(eq(reservation.getId()))).thenReturn(Optional.of(reservation));
        when(reservationRepository.findById(eq(firstOverlappingReservation.getId()))).thenReturn(Optional.of(firstOverlappingReservation));
        when(reservationRepository.findById(eq(notOverlappingReservation.getId()))).thenReturn(Optional.of(notOverlappingReservation));

        when(accommodationService.get(reservation.getAccommodation().getId())).thenReturn(accommodation);
        when(userService.get(eq(guest.getId()))).thenReturn(guest);

        Reservation result = reservationService.approveReservation(reservation.getId());

        assertNotNull(result);
        assertEquals(ReservationStatus.Approved, result.getStatus());

        // Verify that the overlapping reservation is denied
        assertEquals(ReservationStatus.Denied, firstOverlappingReservation.getStatus());
        assertEquals(ReservationStatus.Pending, notOverlappingReservation.getStatus());

    }
}
