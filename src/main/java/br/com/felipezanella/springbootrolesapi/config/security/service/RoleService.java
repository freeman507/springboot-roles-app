package br.com.felipezanella.springbootrolesapi.config.security.service;

import br.com.felipezanella.springbootrolesapi.config.security.models.Roles;

import java.util.ArrayList;
import java.util.List;

public class RoleService {

    private List<Roles> roles;

    public RoleService() {
        this.roles = new ArrayList<>();

        Roles roleAdm = new Roles();
        roleAdm.setId(3L);
        roleAdm.setRole("adm");
        this.roles.add(roleAdm);

        Roles roleStaff = new Roles();
        roleStaff.setId(2L);
        roleStaff.setRole("staff");
        this.roles.add(roleStaff);

        Roles roleClient = new Roles();
        roleClient.setId(1L);
        roleClient.setRole("client");
        this.roles.add(roleClient);
    }

    public Roles findRoleById(Long id) {
        for (Roles role : this.roles) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }
}
