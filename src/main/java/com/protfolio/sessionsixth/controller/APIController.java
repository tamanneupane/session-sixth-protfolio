package com.protfolio.sessionsixth.controller;

import com.protfolio.sessionsixth.model.MyService;
import com.protfolio.sessionsixth.model.PersonalInfo;
import com.protfolio.sessionsixth.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@RestController
public class APIController {

    @Autowired
    APIService apiService;

    @GetMapping(value = "/api/personal-info")
    public HashMap<String, Object> getPersonalInfo(){

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Taman Neupane");
        personalInfo.setEmail("taman.neupane@gmail.com");
        personalInfo.setAge(30);
        personalInfo.setAddress("Bhaktapur, Nepal");
        personalInfo.setCvURL("https://drive.google.com/file/d/1n0GGAYj4LmGxIx3Jdm-qLDKekL1Qnwcx/view?usp=sharing");


        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", personalInfo);
        return map;
    }

    @GetMapping(value = "/api/service")
    public HashMap<String, Object> getMyService(){
        var myServiceList = apiService.getAllServices();
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", myServiceList);
        return map;
    }

    @PostMapping(value = "/api/service")
    public HashMap<String, Object> addService(@Valid @RequestBody MyService myService){

        Logger.getGlobal().info(myService.toString());

        var createdService = apiService.createService(myService);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "success");
        map.put("data", createdService);
        map.put("message", "The service is successfully created");
        return map;
    }

    @PutMapping(value = "/api/service/{serviceName}")
    public HashMap<String, Object> updateService(@PathVariable(name = "serviceName") String serviceName, @RequestBody MyService myService){

        Logger.getGlobal().info(myService.toString());
        Logger.getGlobal().info(serviceName);

        boolean isUpdated = apiService.updateService(serviceName, myService);


        HashMap<String, Object> map = new HashMap<>();

        if(isUpdated){
            map.put("status", "success");
            map.put("data",myService);
            map.put("message", "The service is successfully updated");
        }else{
            map.put("status", "failure");
            map.put("message", "Unable to update the service. Please check your service name.");
        }
        return map;
    }

    @DeleteMapping(value = "/api/service/{serviceName}")
    public HashMap<String, Object> deleteService(@PathVariable(name = "serviceName") String serviceName){
        var isDeleted = apiService.deleteService(serviceName);

        HashMap<String, Object> map = new HashMap<>();

        if(isDeleted){
            map.put("status", "success");
            map.put("message", "The service is successfully deleted");
        }else{
            map.put("status", "failure");
            map.put("message", "Unable to delete the service. Please check your service name.");
        }
        return map;
    }
}
