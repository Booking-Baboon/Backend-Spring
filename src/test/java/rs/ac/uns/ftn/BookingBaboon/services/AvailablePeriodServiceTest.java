package rs.ac.uns.ftn.BookingBaboon.services;

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
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.BookingBaboon.config.TestSecurityConfig;
import rs.ac.uns.ftn.BookingBaboon.domain.accommodation_handling.AvailablePeriod;
import rs.ac.uns.ftn.BookingBaboon.domain.shared.TimeSlot;
import rs.ac.uns.ftn.BookingBaboon.repositories.accommodation_handling.IAvailablePeriodRepository;
import rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.AvailablePeriodService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class AvailablePeriodServiceTest {

    @Autowired
    private AvailablePeriodService availablePeriodService;

    @MockBean
    private IAvailablePeriodRepository availablePeriodRepository;

    @Test
    public void testCreateValidAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        assertEquals(availablePeriodService.create(availablePeriod),availablePeriod);
        verify(availablePeriodRepository,times(1)).save(availablePeriod);
    }

    @Test
    public void testCreateInvalidAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        doThrow(new ConstraintViolationException("", new HashSet<>())).when(availablePeriodRepository).save(availablePeriod);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> availablePeriodService.create(availablePeriod));
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatusCode().value());
        verify(availablePeriodRepository,times(1)).save(availablePeriod);
    }

    @Test
    public void testUpdateValidAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        AvailablePeriod oldAvailablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5)),100F);
        when(availablePeriodRepository.findById(1L)).thenReturn(Optional.of(oldAvailablePeriod));
        assertEquals(availablePeriodService.update(availablePeriod),availablePeriod);
        verify(availablePeriodRepository,times(1)).save(availablePeriod);
    }

    @Test
    public void testUpdateInvalidAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        AvailablePeriod oldAvailablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5)),100F);
        when(availablePeriodRepository.findById(1L)).thenReturn(Optional.of(oldAvailablePeriod));
        doThrow(new ConstraintViolationException("", new HashSet<>())).when(availablePeriodRepository).save(availablePeriod);
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> availablePeriodService.update(availablePeriod));
        verify(availablePeriodRepository,times(1)).save(availablePeriod);
    }

    @Test
    public void testUpdateNotFoundAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        when(availablePeriodRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> availablePeriodService.update(availablePeriod));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value());
        verify(availablePeriodRepository, never()).save(availablePeriod);
    }

    @Test
    public void testRemoveValidAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        when(availablePeriodRepository.findById(1L)).thenReturn(Optional.of(availablePeriod));
        assertEquals(availablePeriodService.remove(1L), availablePeriod);
        verify(availablePeriodRepository, times(1)).delete(availablePeriod);
    }

    @Test
    public void testRemoveNotFoundAvailablePeriod(){
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        when(availablePeriodRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> availablePeriodService.remove(1L));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value());
        verify(availablePeriodRepository, never()).delete(availablePeriod);
    }

    @Test
    public void testSplitPeriodsWhenNoSplitting() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);

        List<AvailablePeriod> result = availablePeriodService.splitPeriods(timeSlot, Arrays.asList(availablePeriod));
        assertEquals(0, result.size());
    }

    @Test
    public void testSplitPeriodsWhenSplittingIntoTwo() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 8));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.splitPeriods(timeSlot, Arrays.asList(availablePeriod));
        assertEquals(2, result.size());

        //assert that end split date is equal to reserved timeslot's start date
        assertEquals(timeSlot.getStartDate(), result.get(0).getTimeSlot().getEndDate());
        assertEquals(timeSlot.getEndDate(), result.get(1).getTimeSlot().getStartDate());
    }

    @Test
    public void testSplitPeriodsWhenSplittingMultiplePeriods() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 10));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7)),10F);
        AvailablePeriod availablePeriod2 = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 7), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.splitPeriods(timeSlot, Arrays.asList(availablePeriod, availablePeriod2));
        assertEquals(2, result.size());

        //assert that end split date is equal to reserved timeslot's start date
        assertEquals(timeSlot.getStartDate(), result.get(0).getTimeSlot().getEndDate());
        assertEquals(timeSlot.getEndDate(), result.get(1).getTimeSlot().getStartDate());
    }

    @Test
    public void testSplitPeriodsWhenSplittingIntoOneAfter() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.splitPeriods(timeSlot, Arrays.asList(availablePeriod));
        assertEquals(1, result.size());

        //assert that end split date is equal to reserved timeslot's start date
        assertEquals(timeSlot.getEndDate(), result.get(0).getTimeSlot().getStartDate());
    }

    @Test
    public void testSplitPeriodsWhenSplittingIntoOneBefore() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 15));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.splitPeriods(timeSlot, Arrays.asList(availablePeriod));
        assertEquals(1, result.size());

        //assert that end split date is equal to reserved timeslot's start date
        assertEquals(timeSlot.getStartDate(), result.get(0).getTimeSlot().getEndDate());
    }

    @Test
    public void testGetOverlappingPeriods() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 6), LocalDate.of(2024, 1, 10));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        AvailablePeriod availablePeriod2 = new AvailablePeriod(2L, new TimeSlot(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 15)),10F);
        AvailablePeriod availablePeriod3 = new AvailablePeriod(3L, new TimeSlot(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.getOverlappingPeriods(timeSlot, Arrays.asList(availablePeriod, availablePeriod2, availablePeriod3));
        assertEquals(1, result.size());

        assertFalse(timeSlot.overlaps(availablePeriod.getTimeSlot()));
        assertTrue(timeSlot.overlaps(availablePeriod2.getTimeSlot()));
        assertFalse(timeSlot.overlaps(availablePeriod3.getTimeSlot()));
    }

    @Test
    public void testGetOverlappingPeriodsWhenNotOverlapping() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 6), LocalDate.of(2024, 1, 10));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        AvailablePeriod availablePeriod2 = new AvailablePeriod(2L, new TimeSlot(LocalDate.of(2024, 1, 20), LocalDate.of(2024, 1, 25)),10F);
        AvailablePeriod availablePeriod3 = new AvailablePeriod(3L, new TimeSlot(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.getOverlappingPeriods(timeSlot, Arrays.asList(availablePeriod, availablePeriod2, availablePeriod3));
        assertEquals(0, result.size());

        assertFalse(timeSlot.overlaps(availablePeriod.getTimeSlot()));
        assertFalse(timeSlot.overlaps(availablePeriod2.getTimeSlot()));
        assertFalse(timeSlot.overlaps(availablePeriod3.getTimeSlot()));
    }

    @Test
    public void testGetOverlappingPeriodsWhenMultipleOverlapping() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.of(2024, 1, 3), LocalDate.of(2024, 1, 13));
        AvailablePeriod availablePeriod = new AvailablePeriod(1L, new TimeSlot(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 5)),10F);
        AvailablePeriod availablePeriod2 = new AvailablePeriod(2L, new TimeSlot(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 10)),10F);
        AvailablePeriod availablePeriod3 = new AvailablePeriod(3L, new TimeSlot(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 15)),10F);

        List<AvailablePeriod> result = availablePeriodService.getOverlappingPeriods(timeSlot, Arrays.asList(availablePeriod, availablePeriod2, availablePeriod3));
        assertEquals(3, result.size());

        assertTrue(timeSlot.overlaps(availablePeriod.getTimeSlot()));
        assertTrue(timeSlot.overlaps(availablePeriod2.getTimeSlot()));
        assertTrue(timeSlot.overlaps(availablePeriod3.getTimeSlot()));
    }
}
