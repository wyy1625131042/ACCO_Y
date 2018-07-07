package com.hqu.acco.accomodation.service;


import com.hqu.acco.accomodation.entity.HouseInfo;
import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.entity.RentApply;
import com.hqu.acco.accomodation.entity.Student;
import com.hqu.acco.accomodation.repository.HouseInfoRepository;
import com.hqu.acco.accomodation.repository.LandlordRepository;
import com.hqu.acco.accomodation.repository.RentApplyRepository;
import com.hqu.acco.accomodation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentApplyService {
    @Autowired
    RentApplyRepository rentApplyRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    LandlordRepository landlordRepository;


    public boolean save(RentApply rentApply){
        try{
            rentApplyRepository.save(rentApply);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(String loginName, Integer hid){
        RentApply rentApply = new RentApply();
        Optional<HouseInfo> houseInfo =  houseInfoRepository.findById(hid);
        Student student = studentRepository.findByLoginName(loginName);
        Optional<Landlord> landlord = landlordRepository.findById(houseInfo.get().getLandlordId());
        rentApply.setLocation(houseInfo.get().getAddress());
        rentApply.setHid(hid);
        rentApply.setSid(student.getId());
        rentApply.setLid(landlord.get().getId());
        rentApply.setMeet(new Date());
        return save(rentApply);
    }


    public List<RentApply> findAll(){
        return rentApplyRepository.findAll();
    }
    public boolean checkPlan(Student s,Integer hid){
        if(rentApplyRepository.findByHidAndSid(hid,s.getId()) != null){
            return false;
        } else {
            return true;
        }
    }

    public List<RentApply> findMyAppointment(Student s){
        return rentApplyRepository.findBySid(s.getId());
    }
    public List<RentApply> findMyAppointment(Landlord l){
        return rentApplyRepository.findByLid(l.getId());
    }

}
