package com.pmp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileData extends JpaRepository<com.pmp.entity.UserProfileData, Integer> {
	Optional<com.pmp.entity.UserProfileData> findByUserName(String userName);

}
