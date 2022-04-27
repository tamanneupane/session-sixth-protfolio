package com.protfolio.sessionsixth.service;

import com.protfolio.sessionsixth.exceptions.ServiceNotFoundException;
import com.protfolio.sessionsixth.model.MyService;
import com.protfolio.sessionsixth.repository.MyServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class APIService {

    @Autowired
    MyServiceRepository myServiceRepository;

    public List<MyService> getAllServices(){
        List<MyService> allServices  = new ArrayList<>();
        myServiceRepository.findAll().forEach(allServices::add);
        return allServices;
    }

    public MyService createService(MyService myService){
        return myServiceRepository.save(myService);
    }

    public Boolean updateService(String serviceName, MyService myService) throws ServiceNotFoundException{
        var optionalService = myServiceRepository.findById(serviceName);

        if(optionalService.isPresent()){
            var service = optionalService.get();
            String serviceDescription = Objects.requireNonNullElse(myService.getServiceDescription(), service.getServiceDescription());
            service.setServiceDescription(serviceDescription);
            String serviceImage = Objects.requireNonNullElse(myService.getServiceImage(), service.getServiceImage());
            service.setServiceImage(serviceImage);

            myServiceRepository.save(service);

            return  true;
        }else{
            throw new ServiceNotFoundException("The service you are trying to update is not available");
        }
    }

    public boolean deleteService(String serviceName){
        myServiceRepository.deleteById(serviceName);
        return true;
    }


}
