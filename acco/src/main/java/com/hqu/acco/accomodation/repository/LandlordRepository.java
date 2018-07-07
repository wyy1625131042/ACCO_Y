package com.hqu.acco.accomodation.repository;

import com.hqu.acco.accomodation.entity.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord,Integer> {
    Landlord findByLoginName(String s);
}
