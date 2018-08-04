package com.vdudnyk.appointmentengine.application.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String roleName);
}
