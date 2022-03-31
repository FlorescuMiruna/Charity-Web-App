package com.example.charity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Integer totalAmountOfMoney = 0;

//    /** Un eveniment caritabil sustine o singura cauza */
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="social_cause_id")
//    private SocialCause socialCause;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "social_cause_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//  //  @JsonIgnore
//    private SocialCause socialCause;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_cause_id", referencedColumnName = "id")
    private SocialCause socialCause;


    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private Set<Donation> donations;



}
