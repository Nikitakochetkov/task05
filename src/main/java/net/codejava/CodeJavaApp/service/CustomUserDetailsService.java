package net.codejava.CodeJavaApp.service;

import net.codejava.CodeJavaApp.entity.CustomeUserDetails;
import net.codejava.CodeJavaApp.entity.Status;
import net.codejava.CodeJavaApp.entity.User;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomeUserDetails(user) {
            @Override
            public boolean isAccountNonLocked() {
                Optional<User> optionalUser = repo.findById(user.getId());
                User user = optionalUser.isPresent() ? optionalUser.get() : new User();
                if (user.getStatus() == Status.BLOCK)
                    return false;
                else if (user.getStatus() == Status.UNBLOCK) {
                    user.setSignUpDate(new Date());
                    repo.save(user);
                    return true;
                }
                return super.isAccountNonLocked();
            }
        };
    }
}
