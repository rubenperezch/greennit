package com.greenfoxacademy.greennit.Repositories;

import com.greenfoxacademy.greennit.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByUsername(String username);

    User findFirstByUsername(String username);

    User findFirstById(Long id);
}
