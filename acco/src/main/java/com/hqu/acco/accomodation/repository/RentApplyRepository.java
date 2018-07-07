package com.hqu.acco.accomodation.repository;

import com.hqu.acco.accomodation.entity.RentApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentApplyRepository extends JpaRepository<RentApply,Integer> {
    RentApply findByHidAndSid(Integer hid,Integer sid);
    List<RentApply> findBySid(Integer sid);
    List<RentApply> findByLid(Integer lid);
}
