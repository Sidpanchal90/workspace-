package com.blogs.dau;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
