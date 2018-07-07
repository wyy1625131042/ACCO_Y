package com.hqu.acco.accomodation.service;

import com.hqu.acco.accomodation.entity.HouseInfo;
import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.repository.HouseInfoRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseInfoService {
    @Autowired
    HouseInfoRepository repository;


    public boolean save(HouseInfo houseInfo){
        try{
            repository.saveAndFlush(houseInfo);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Page<HouseInfo> findHouseInfo(Integer page,Integer size){
        if(page == null) page = 0;
        if(size == null) size = 5;
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return repository.findAll(pageable);
    }

    public List<HouseInfo> findAll(){
        return repository.findAll();
    }

    public Optional<HouseInfo> findById(Integer hid){ return repository.findById(hid); }

    public List<HouseInfo> findByMy(Landlord landlord){
        return repository.findAllByLandlordId(landlord.getId());
    }
}
