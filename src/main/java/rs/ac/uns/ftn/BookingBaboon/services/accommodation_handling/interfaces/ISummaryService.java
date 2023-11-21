package rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.interfaces;

import rs.ac.uns.ftn.BookingBaboon.dtos.accommodation_handling.AccommodationMonthlySummary;
import rs.ac.uns.ftn.BookingBaboon.dtos.accommodation_handling.PeriodSummary;

import java.util.Date;

public interface ISummaryService {
    PeriodSummary getPeriodSummary(String hostId, Date startDate, Date endDate);
    AccommodationMonthlySummary getMonthlySummary(Long id);
}
