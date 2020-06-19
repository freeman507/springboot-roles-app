package br.com.felipezanella.springbootrolesapi.config.security.service;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import br.com.felipezanella.springbootrolesapi.config.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByLogin(String login) {

        return userRepository.findByLogin(login);
    }

}
