package com.example.charity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    private Date dontaionDate;

    /**Asociez evenimentul in cadrul caruia fac donatia*/
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="event_id")
    private Event event;
}
