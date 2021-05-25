package com.bme.lab.ptl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/3/21
 */
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull Long id;

    private @NonNull @NotBlank(message = "Field can not be blank") String name;

    private @NonNull @NotBlank(message = "Field can not be blank") String email;

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Route> routes = new ArrayList<>();


    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Rating> ratings = new ArrayList<>();

    @Override
    public String toString() {
        return "Company{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
