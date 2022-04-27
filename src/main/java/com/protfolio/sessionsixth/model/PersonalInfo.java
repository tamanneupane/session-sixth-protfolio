package com.protfolio.sessionsixth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "personal_info")
@Table
public class PersonalInfo {

    private String name;
    private int age;

    @Id
    private String email;

    @Column(length = 500)
    private String address;

    @Column(name = "cv_url", length = 500)
    private String cvURL;
}
