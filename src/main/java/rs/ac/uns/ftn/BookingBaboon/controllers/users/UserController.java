package rs.ac.uns.ftn.BookingBaboon.controllers.users;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.BookingBaboon.domain.users.User;
import rs.ac.uns.ftn.BookingBaboon.domain.users.User;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.UserRequest;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.UserResponse;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.UserResponse;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.UserProfile;
import rs.ac.uns.ftn.BookingBaboon.services.users.interfaces.IUserService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final IUserService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<UserResponse>> getUsers() {
        Collection<User> users = service.getAll();
        Collection<UserResponse> userResponses =  users.stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<UserResponse> get(@PathVariable Long userId) {
        User user = service.get(userId);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( mapper.map(user, UserResponse.class), HttpStatus.OK);
    }

    @PostMapping({"/"})
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
        return new ResponseEntity<>(mapper.map(service.create(mapper.map(user, User.class)),UserResponse.class), HttpStatus.CREATED);
    }

    @PutMapping({"/"})
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest user) {
        return new ResponseEntity<>(mapper.map(service.update(mapper.map(user, User.class)),UserResponse.class),HttpStatus.OK);
    }

    @DeleteMapping({"/{userId}"})
    public ResponseEntity<UserResponse> remove(@PathVariable Long userId) {
        User user = service.remove(userId);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( mapper.map(user,UserResponse.class), HttpStatus.OK);
    }

    @GetMapping({"/profile/{userId}"})
    public ResponseEntity<UserProfile> getProfile(@PathVariable Long userId) {
        User user = service.get(userId);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( mapper.map(user, UserProfile.class), HttpStatus.OK);
    }

    @PostMapping({"/login"})
    public ResponseEntity<UserResponse> login(@RequestBody String email, @RequestBody String password){
        User user = service.login(email,password);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>( mapper.map(user, UserResponse.class), HttpStatus.OK);
    }

    @PutMapping({"{userId}/activate"})
    public ResponseEntity<UserResponse> activate(@PathVariable Long userId){
        return new ResponseEntity<>(mapper.map(service.activate(userId), UserResponse.class), HttpStatus.OK);
    }

    @PutMapping("{userId}/change-password")
    public ResponseEntity<UserResponse> changePassword(@PathVariable Long userId, @RequestBody String password){
        return new ResponseEntity<>(mapper.map(service.changePassword(userId,password), UserResponse.class), HttpStatus.OK);
    }

}
