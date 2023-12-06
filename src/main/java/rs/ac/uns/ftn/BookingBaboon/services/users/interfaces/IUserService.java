package rs.ac.uns.ftn.BookingBaboon.services.users.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.uns.ftn.BookingBaboon.domain.users.User;
import rs.ac.uns.ftn.BookingBaboon.dtos.users.PasswordChangeRequest;

import java.util.Collection;
import java.util.Set;

public interface IUserService{
    Collection<User> getAll();

    User get(Long userId) throws ResponseStatusException;

    User create(User user) throws ResponseStatusException;

    User update(User user) throws ResponseStatusException;

    User remove(Long userId);

    User login(String username, String password);

    User activate(Long userId);

    User changePassword(Long userId, PasswordChangeRequest passwordChangeRequest);

    void removeAll();

    User getByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
