package com.cxy.spring.boot.module.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoTestController {
    @Autowired
    private MongoTestDao mtdao;

    @GetMapping(value="/test1")
    public void saveTest() throws Exception {
        MongoEntity mgtest=new MongoEntity();
        mgtest.setId(12);
        mgtest.setAge(45);
        mgtest.setName("ceshi3");
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value="/test2")
    public MongoEntity findTestByName(){
        MongoEntity mgtest= mtdao.findTestByName("ceshi3");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @GetMapping(value="/test3")
    public void updateTest(){
        MongoEntity mgtest=new MongoEntity();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @GetMapping(value="/test4")
    public void deleteTestById(){
        mtdao.deleteTestById(11);
    }

}
