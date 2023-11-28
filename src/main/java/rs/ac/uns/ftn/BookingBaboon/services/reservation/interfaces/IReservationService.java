package rs.ac.uns.ftn.BookingBaboon.services.reservation.interfaces;

import rs.ac.uns.ftn.BookingBaboon.domain.reservation.Reservation;

import java.util.Collection;
import java.util.HashSet;

public interface IReservationService {
    public HashSet<Reservation> getAll();
    public Reservation get(Long reservationId);
    public Reservation create(Reservation reservation);
    public Reservation update(Reservation reservation);
    public Reservation remove(Long reservationId);
    public void removeAll();
    public Reservation cancel(Long id);
    public int getCancellationCountForUser(Long userId);
}
