package com.example.pfenningvaadin2022mysql_1.repository.loginRepository;

import com.example.pfenningvaadin2022mysql_1.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User getByUsername(String username);

    User getByActivationCode(String activationCode);
}
