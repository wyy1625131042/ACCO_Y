package com.hqu.acco.accomodation.controller;

import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.entity.Student;
import com.hqu.acco.accomodation.entity.User;
import com.hqu.acco.accomodation.service.LandlordService;
import com.hqu.acco.accomodation.service.StudentService;
import com.hqu.acco.accomodation.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LandlordService landlordService;
    @RequestMapping("/signup")
    public String signUp(Model model){
        User user = new User();
        model.addAttribute(user);
        return "signup";
    }
    @RequestMapping("/addUser")
    public String addUser(Model model,@ModelAttribute("user") User user,String type){
        if(userService.save(user,type)){
            if("landlord".equals(type)){
                Landlord landlord = landlordService.findByLoginName(user.getLoginName());
                model.addAttribute("landlord",landlord);
                model.addAttribute("loginName",user.getLoginName());
                return "landlord-info";
            }
            if("student".equals(type)){
                Student student = studentService.findByLoginName(user.getLoginName());
                model.addAttribute("student",student);
                model.addAttribute("type",type);
                model.addAttribute("loginName",user.getLoginName());
                return "student-info";
            }
            return "index";
        } else {
            return "false";
        }
    }
    @RequestMapping("/studentInfoUpdate")
    public String infoUpdateStudent(Student student,String loginName,Model model){
        Student dbStudent = studentService.findByLoginName(loginName);
        dbStudent.setBirth(student.getBirth());
        dbStudent.setGender(student.getGender());

        if(studentService.save(dbStudent)){
            model.addAttribute("msg","保存成功");
            model.addAttribute("username",dbStudent.getLoginName());
            return "success";
        }
        model.addAttribute("msg","保存失败");
        return "success";
    }
    @RequestMapping("/landlordInfoUpdate")
    public String infoUpdateLandlord(Landlord landlord,String loginName,Model model){

        Landlord dbLandlord = landlordService.findByLoginName(loginName);
        dbLandlord.setIdNumber(landlord.getIdNumber());

        if(landlordService.save(dbLandlord)){
            model.addAttribute("msg","保存成功");
            model.addAttribute("username",landlord.getLoginName());
            return "success";
        }
        model.addAttribute("msg","保存失败");
        return "success";
    }
}
