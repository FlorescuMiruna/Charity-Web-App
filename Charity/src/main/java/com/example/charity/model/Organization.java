package com.example.charity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.*;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date foundingDate;

    /** Un ONG poate sustine mai multe cauze sociale, iar o cauza sociala poate fi sustinuta
     * de mai multe ONG-uri */

    @ManyToMany
    @JoinTable(
            name = "organization_causes",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "social_cause_id"))
//    private List<SocialCause> socialCauses = new ArrayList<>();
    private  Set<SocialCause> socialCauses = new HashSet<>();






}
