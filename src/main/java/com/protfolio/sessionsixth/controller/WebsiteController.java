package com.protfolio.sessionsixth.controller;

import com.protfolio.sessionsixth.model.Contact;
import com.protfolio.sessionsixth.model.MyService;
import com.protfolio.sessionsixth.model.PersonalInfo;
import com.protfolio.sessionsixth.service.APIService;
import com.protfolio.sessionsixth.utility.MailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.logging.Logger;

@Controller
public class WebsiteController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    APIService apiService;

    @GetMapping(value = "/")
    public String index(Model model){

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Taman Neupane");
        personalInfo.setEmail("taman.neupane@gmail.com");
        personalInfo.setAge(30);
        personalInfo.setAddress("Bhaktapur, Nepal");
        personalInfo.setCvURL("https://drive.google.com/file/d/1n0GGAYj4LmGxIx3Jdm-qLDKekL1Qnwcx/view?usp=sharing");

        List<MyService> myServiceList = apiService.getAllServices();

        //Some changes done

        model.addAttribute("personalInfo", personalInfo);
        model.addAttribute("myServices", myServiceList);

        return "index";
    }

    @PostMapping(value = "/contact")
    public String sendEmail(@ModelAttribute Contact contact){
        Logger.getGlobal().info(contact.toString());

        StringBuilder mailMessageBuilder = new StringBuilder();
        mailMessageBuilder.append("Name : ");
        mailMessageBuilder.append(contact.getName());
        mailMessageBuilder.append("\n");
        mailMessageBuilder.append("Email : ");
        mailMessageBuilder.append(contact.getEmail());
        mailMessageBuilder.append("\n");
        mailMessageBuilder.append("Message : ");
        mailMessageBuilder.append(contact.getMessage());

        String toEmail = "taman.neupane@peacenepal.com";
        String subject = "Mail from my personal website";

        MailHelper.sendEmail(javaMailSender, subject, mailMessageBuilder.toString(), toEmail);

        return "redirect:";
    }
}
