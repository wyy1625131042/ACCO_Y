package com.hqu.acco.accomodation.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity

@DiscriminatorValue("student")//设置鉴别器值
public class Student extends User {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    private String gender;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
