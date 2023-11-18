package rs.ac.uns.ftn.BookingBaboon.domain.reservation_handling;

import jakarta.persistence.*;
import lombok.Data;
import rs.ac.uns.ftn.BookingBaboon.domain.accommodation_handling.Accommodation;
import rs.ac.uns.ftn.BookingBaboon.domain.TimeSlot;
import rs.ac.uns.ftn.BookingBaboon.domain.Users.Guest;

@Entity
@Data
@Table(name = "reservations")
@TableGenerator(name="reservation_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="reservation", valueColumnName="value_pk")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "reservation_id_generator")
    private Long id;

    @ManyToOne
    private Accommodation accommodation;

    @Embedded
    private TimeSlot timeSlot;

    @ManyToOne
    private Guest guest;

    private ReservationStatus status = ReservationStatus.Pending;

    public void Approve(){
        status = ReservationStatus.Approved;
    }

    public void Deny(){
        status = ReservationStatus.Denied;
    }

    public void Cancel(){
        status = ReservationStatus.Canceled;
    }

    public void Finish(){
        status = ReservationStatus.Finished;
    }
}