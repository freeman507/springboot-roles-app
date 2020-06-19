package br.com.felipezanella.springbootrolesapi.config.security.repository;

import br.com.felipezanella.springbootrolesapi.config.security.models.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Long> {
}
