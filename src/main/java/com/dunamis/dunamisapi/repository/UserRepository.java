package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
