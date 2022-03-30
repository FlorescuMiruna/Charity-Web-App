package com.example.charity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organization")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private String description;
    private Date foundingDate;

    /** Un ONG poate sustine mai multe cauze sociale, iar o cauza sociala poate fi sustinuta
     * de mai multe ONG-uri */
    @ManyToMany
    @JoinTable(
            name = "organization_causes",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "social_cause_id"))
    private List<SocialCause> socialCauses;



}
