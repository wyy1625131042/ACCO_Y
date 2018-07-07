package com.hqu.acco.accomodation.repository;

import com.hqu.acco.accomodation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLoginName(String s);
}
