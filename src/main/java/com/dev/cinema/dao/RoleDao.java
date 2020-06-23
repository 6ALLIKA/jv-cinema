package com.dev.cinema.dao;

import com.dev.cinema.model.Role;
import java.util.List;

public interface RoleDao {

    Role get(Long id);

    List<Role> getAll();

    Role add(Role element);

    Role getRoleByName(String roleName);
}
