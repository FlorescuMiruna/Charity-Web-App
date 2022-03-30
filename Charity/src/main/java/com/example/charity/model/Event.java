package com.example.charity.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Date date;

    /** Un eveniment caritabil sustine o singura cauza */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="social_cause_id")
    private SocialCause socialCause;

    private Integer totalAmountOfMoney;
}
