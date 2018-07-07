package com.hqu.acco.accomodation.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("landlord")//设置鉴别器值
public class Landlord extends User {
    private String idNumber;//身份证号

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


}
