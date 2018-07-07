package com.hqu.acco.accomodation.repository;

import com.hqu.acco.accomodation.entity.Student;
import com.hqu.acco.accomodation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByLoginName(String s);
}
