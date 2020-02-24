package com.cxy.spring.boot.module.multidata;

import com.cxy.spring.boot.module.multidata.service.MultiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MultiDataController {
    @Autowired
    private MultiDataService multiDataService;

    @GetMapping(value="/multiData/test")
    public void deleteTestById(){
        List<AboutUsEntity> aboutUsInfoList = multiDataService.findAboutUsInfo();
        for (AboutUsEntity aboutUsEntity : aboutUsInfoList) {
            System.out.println(aboutUsEntity.toString());
        }
    }

}
