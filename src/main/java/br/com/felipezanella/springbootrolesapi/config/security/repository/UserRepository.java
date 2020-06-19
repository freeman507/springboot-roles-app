package br.com.felipezanella.springbootrolesapi.config.security.repository;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
