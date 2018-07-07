package com.hqu.acco.accomodation.service;

import com.hqu.acco.accomodation.entity.Student;
import com.hqu.acco.accomodation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public boolean save(Student student){
        student.setRole("ROLE_STUDENT");
        try {
            studentRepository.saveAndFlush(student);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Student findByLoginName(String s){
        return studentRepository.findByLoginName(s);
    }

    public Optional<Student> findById(Integer id) { return studentRepository.findById(id); }

}
