package com.example.charity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "donation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amountOfMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dontaionDate;

    /**Asociez evenimentul in cadrul caruia fac donatia*/

    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
}
