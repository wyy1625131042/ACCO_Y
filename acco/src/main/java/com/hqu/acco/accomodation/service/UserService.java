package com.hqu.acco.accomodation.service;

import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.entity.Student;
import com.hqu.acco.accomodation.entity.User;
import com.hqu.acco.accomodation.repository.LandlordRepository;
import com.hqu.acco.accomodation.repository.StudentRepository;
import com.hqu.acco.accomodation.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LandlordRepository landlordRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(StringUtils.isEmpty(user.getRole())){
            throw new UsernameNotFoundException("用户不可用");
        }
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLoginName(),user.getPassword(),authorities);
    }


    public boolean save(User user){
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean save(User user,String type){
        try {
            if("student".equals(type)){
                Student student = new Student();
                BeanUtils.copyProperties(user,student);
                studentRepository.saveAndFlush(student);
            } else {
                Landlord landlord = new Landlord();
                BeanUtils.copyProperties(user,landlord);
                landlordRepository.saveAndFlush(landlord);
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
