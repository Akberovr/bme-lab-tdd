package com.bme.lab.ptl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/19/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private @NotBlank(message = "Field can not be blank") String name;

    private @NotBlank(message = "Field can not be blank") String email;


    @Override
    public String toString() {
        return "Company{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
