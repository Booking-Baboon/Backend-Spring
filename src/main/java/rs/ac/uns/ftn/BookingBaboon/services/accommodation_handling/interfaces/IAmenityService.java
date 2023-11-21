package rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.interfaces;

import rs.ac.uns.ftn.BookingBaboon.domain.accommodation_handling.Accommodation;
import rs.ac.uns.ftn.BookingBaboon.domain.accommodation_handling.Amenity;

import java.util.Collection;

public interface IAmenityService {
    public Collection<Amenity> getAll();
    public Amenity get(Long amenityId);
    public Amenity create(Amenity amenity);
    public Amenity update(Amenity amenity);
    public void remove(Long amenityId);
}