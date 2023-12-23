
package com.blogs.dau;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {


	Optional<User> findByEmail(String email);

}
