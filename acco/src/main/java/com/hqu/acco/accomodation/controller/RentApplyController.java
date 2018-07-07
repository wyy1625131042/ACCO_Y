package com.hqu.acco.accomodation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hqu.acco.accomodation.entity.*;
import com.hqu.acco.accomodation.repository.RentApplyRepository;
import com.hqu.acco.accomodation.service.HouseInfoService;
import com.hqu.acco.accomodation.service.LandlordService;
import com.hqu.acco.accomodation.service.RentApplyService;
import com.hqu.acco.accomodation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/r")
public class RentApplyController {
    @Autowired
    RentApplyService rentApplyService;
    @Autowired
    LandlordService landlordService;
    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    StudentService studentService;

    @RequestMapping("/arrange")
    @ResponseBody
    public Map<String,String> arrange(Integer hid) throws JsonProcessingException {
        Map<String,String> result = new HashMap<String,String>();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!rentApplyService.checkPlan(studentService.findByLoginName(userDetails.getUsername()),hid)){
            result.put("success","false");
            result.put("msg","已经预约过,\n可在我的预约页面中查看,<a href='/r/my-appointment'>点击打开</a>");
            return result;
        }
        if(rentApplyService.save(userDetails.getUsername(),hid)){
            result.put("success","true");
            HouseInfo houseInfo = houseInfoService.findById(hid).get();
            Landlord l = landlordService.findById(houseInfo.getLandlordId()).get();
            result.put("landlord",new ObjectMapper().writeValueAsString(l));
            result.put("msg","预约成功!\n房东联系电话:"+l.getPhone());
            return result;
        } else {
            result.put("success","false");
            result.put("msg","预约失败");
            return result;
        }
    }
    @RequestMapping("my-appointment")
    public String myAppointment(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student s = studentService.findByLoginName(userDetails.getUsername());
        List<RentApply> rentApplyList = rentApplyService.findMyAppointment(s);
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = null;
        for(RentApply r : rentApplyList){
            appointment = new Appointment();
            appointment.setMeet(r.getMeet());
            appointment.setLocation(r.getLocation());
            appointment.setHouseInfo(houseInfoService.findById(r.getHid()).get());
            appointment.setLandlord(landlordService.findById(r.getLid()).get());
            appointments.add(appointment);
        }
        model.addAttribute("list",appointments);
        model.addAttribute("username",userDetails.getUsername());
        return "my-appointment";
    }

    @RequestMapping("my-appointment-landlord")
    public String myAppointmentLandlord(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Landlord l = landlordService.findByLoginName(userDetails.getUsername());
        List<RentApply> rentApplyList = rentApplyService.findMyAppointment(l);
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = null;
        for(RentApply r : rentApplyList){
            appointment = new Appointment();
            appointment.setMeet(r.getMeet());
            appointment.setLocation(r.getLocation());
            appointment.setHouseInfo(houseInfoService.findById(r.getHid()).get());
            appointment.setStudent(studentService.findById(r.getSid()).get());
            appointments.add(appointment);
        }
        model.addAttribute("list",appointments);
        model.addAttribute("username",userDetails.getUsername());
        return "my-appointment-landlord";
    }
}
