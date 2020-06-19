package br.com.felipezanella.springbootrolesapi.config.security.service;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.findByLogin(login);

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("User not found");
    }
}
