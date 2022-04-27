package com.protfolio.sessionsixth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;



@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "my_service")
@Table
public class MyService implements Comparable<MyService> {

    @Getter
    @Setter
    @Id
    @Column(name = "service_name")
    @NotEmpty(message = "Service name should not be empty")
    private String serviceName;

    @Getter
    @Setter
    @Column(name = "service_description", length = 500)
    @NotEmpty(message = "Service description should not be empty")
    @Size(min = 10, max = 500, message = "Service description cannot be more than 500 characters")
    private String serviceDescription;

    @Getter
    @Setter
    @Column(name = "service_image")
    @NotEmpty(message = "Service Image should not be empty")
    private String serviceImage;

    @Override
    public int compareTo(MyService o) {
        return this.serviceName.compareTo(o.serviceDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyService myService = (MyService) o;
        return serviceName.equals(myService.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName);
    }

    @Override
    public String toString() {
        return "MyService{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", serviceImage='" + serviceImage + '\'' +
                '}';
    }
}
