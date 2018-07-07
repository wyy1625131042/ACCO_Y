package com.hqu.acco.accomodation.controller;

import com.hqu.acco.accomodation.entity.HouseInfo;
import com.hqu.acco.accomodation.entity.Landlord;
import com.hqu.acco.accomodation.service.HouseInfoService;
import com.hqu.acco.accomodation.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/h")
public class HouseInfoController {
    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    LandlordService landlordService;

    @RequestMapping("/findHouseInfoNoQuery")
    public String findHouseInfoNoQuery(ModelMap modelMap, Integer page,
                                   Integer size){
        Page<HouseInfo> datas = houseInfoService.findHouseInfo(page, size);
        modelMap.addAttribute("datas", datas);
        return "house-info";
    }
    @RequestMapping("/houseInfoList")
    public String houseInfoList(Model model){
        List<HouseInfo> houseInfoList = houseInfoService.findAll();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("datas",houseInfoList);
        model.addAttribute("username",userDetails.getUsername());
        return "house-info";
    }
    @RequestMapping("/houseInfoListLandlord")
    public String houseInfoListLandLord(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<HouseInfo> houseInfoList = houseInfoService.findByMy(landlordService.findByLoginName(userDetails.getUsername()));
        model.addAttribute("datas",houseInfoList);
        model.addAttribute("username",userDetails.getUsername());

        return "house-info-my";
    }
    @RequestMapping("/save")
    public String save(Model model,HouseInfo houseInfo){
        houseInfoService.save(houseInfo);
        return houseInfoListLandLord(model);
    }
    @RequestMapping("/add")
    public String add(Model model,Integer hid){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!StringUtils.isEmpty(hid)){
            HouseInfo houseInfo = houseInfoService.findById(hid).get();
            model.addAttribute("houseInfo",houseInfo);
            model.addAttribute("username",userDetails.getUsername());
            model.addAttribute("title","房屋信息修改");
            return "house-info-add";
        }
        Landlord landlord = landlordService.findByLoginName(userDetails.getUsername());
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setLandlordId(landlord.getId());
        houseInfo.setLandlordName(landlord.getName());
        houseInfo.setPnumber(landlord.getPhone());
        model.addAttribute("houseInfo",houseInfo);
        model.addAttribute("username",userDetails.getUsername());
        model.addAttribute("title","房屋信息新增");
        return "house-info-add";
    }
}
