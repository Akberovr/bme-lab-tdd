package com.bme.lab.ptl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "route")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank(message = "Field can not be blank") String origin;

    private @NotBlank(message = "Field can not be blank") String destination;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

    @Override
    public String toString() {
        return "Route{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
