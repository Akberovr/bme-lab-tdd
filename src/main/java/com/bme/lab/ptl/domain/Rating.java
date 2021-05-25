package com.bme.lab.ptl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/3/21
 */

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Rating {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull Long id;

    private @NonNull @NotBlank(message = "Field can not be blank") Integer value;

    private @NonNull @NotBlank(message = "Field can not be blank") String feedback;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;


    @Override
    public String toString() {
        return "Rating{" +
                ", value=" + value +
                '}';
    }
}
