package br.com.felipezanella.springbootrolesapi.config.security.models;

import br.com.felipezanella.springbootrolesapi.config.security.repository.RoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    private String name;

    @Column(name = "id_role")
    private Long idRoles;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Roles> roles = new ArrayList<>();
        Roles role = new Roles();
        role.setId(3L);
        role.setRole("adm");
        roles.add(role);

        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
