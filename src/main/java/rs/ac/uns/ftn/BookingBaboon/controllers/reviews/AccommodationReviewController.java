package rs.ac.uns.ftn.BookingBaboon.controllers.reviews;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.BookingBaboon.domain.reviews.AccommodationReview;
import rs.ac.uns.ftn.BookingBaboon.dtos.reviews.AccommodationReviewCreateRequest;
import rs.ac.uns.ftn.BookingBaboon.dtos.reviews.AccommodationReviewResponse;
import rs.ac.uns.ftn.BookingBaboon.dtos.reviews.AccommodationReviewUpdateRequest;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.guests.GuestResponse;
import rs.ac.uns.ftn.BookingBaboon.services.accommodation_handling.interfaces.IAccommodationService;
import rs.ac.uns.ftn.BookingBaboon.services.reviews.interfaces.IAccommodationReviewService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accommodation-reviews")
public class AccommodationReviewController {

    private final IAccommodationReviewService service;
    private final IAccommodationService accommodationService;
    private final ModelMapper mapper;


    // Get all accommodation reports
    @GetMapping
    public ResponseEntity<Collection<AccommodationReviewResponse>> getHosts() {
        Collection<AccommodationReview> accommodationReviews = service.getAll();
        Collection<AccommodationReviewResponse> accommodationReviewResponses =  accommodationReviews.stream()
                .map(accommodationReview -> mapper.map(accommodationReview, AccommodationReviewResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accommodationReviewResponses, HttpStatus.OK);
    }

    // Get a accommodation report by ID
    @GetMapping({"/{accommodationReviewId}"})
    public ResponseEntity<AccommodationReviewResponse> get(@PathVariable Long accommodationReviewId) {
        AccommodationReview accommodationReview = service.get(accommodationReviewId);
        if(accommodationReview==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( mapper.map(accommodationReview, AccommodationReviewResponse.class), HttpStatus.OK);
    }

    // Create a new accommodation report
    @PostMapping({"/"})
    public ResponseEntity<AccommodationReviewResponse> create(@RequestBody AccommodationReviewCreateRequest accommodationReview) {

        return new ResponseEntity<>(mapper.map(service.create(mapper.map(accommodationReview, AccommodationReview.class)), AccommodationReviewResponse.class), HttpStatus.CREATED);
    }

    // Update an existing accommodation report
    @PutMapping({"/"})
    public ResponseEntity<AccommodationReviewResponse> update(@RequestBody AccommodationReviewUpdateRequest accommodationReview) {

        return new ResponseEntity<>(mapper.map(service.update(mapper.map(accommodationReview, AccommodationReview.class)),AccommodationReviewResponse.class),HttpStatus.OK);
    }

    // Delete a accommodation report by ID
    @DeleteMapping("/{accommodationReviewId}")
    public ResponseEntity<AccommodationReviewResponse> remove(@PathVariable Long accommodationReviewId) {

        AccommodationReview accommodationReview = service.remove(accommodationReviewId);
        if(accommodationReview==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( mapper.map(accommodationReview,AccommodationReviewResponse.class), HttpStatus.OK);
    }

    @GetMapping("/{accommodationReviewId}/reviewer")
    public ResponseEntity<GuestResponse> getReviewer(@PathVariable Long accommodationReviewId) {

        if (service.get(accommodationReviewId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GuestResponse guestResponse = mapper.map(service.getReviewer(accommodationReviewId), GuestResponse.class);

        return new ResponseEntity<>(guestResponse, HttpStatus.OK);
    }

    @GetMapping("/average-rating/{accommodationId}")
    public ResponseEntity<Float> getAverageRating(@PathVariable Long accommodationId) {

        if (accommodationService.get(accommodationId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        float averageRating = service.getAverageRating(accommodationId);

        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}