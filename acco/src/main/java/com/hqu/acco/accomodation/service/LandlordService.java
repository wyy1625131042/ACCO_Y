package com.hqu.acco.accomodation.service;

import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandlordService {
    @Autowired
    LandlordRepository landlordRepository;


    public boolean save(Landlord landlord){
        landlord.setRole("ROLE_LANDLORD");
        try{
            landlordRepository.saveAndFlush(landlord);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Landlord findByLoginName(String s){
        return landlordRepository.findByLoginName(s);
    }

    public Optional<Landlord> findById(Integer id){
        return landlordRepository.findById(id);
    }
}
