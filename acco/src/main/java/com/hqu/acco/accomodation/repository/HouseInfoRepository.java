package com.hqu.acco.accomodation.repository;

import com.hqu.acco.accomodation.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseInfoRepository extends JpaRepository<HouseInfo,Integer>,JpaSpecificationExecutor<HouseInfo> {
    List<HouseInfo> findAllByLandlordId(Integer id);
}

