package rs.ac.uns.ftn.BookingBaboon.Domain.Users;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@TableGenerator(name="user_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="user", valueColumnName="value_pk")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_id_generator")
    private Long id;

    private String username;

    private String password;

    private boolean isBlocked;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    public User(Long id, String username, String password, boolean isBlocked, String email, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}