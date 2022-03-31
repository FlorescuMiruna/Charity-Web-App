package com.example.charity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "social_cause")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocialCause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "socialCauses")
//    private List<Organization> organizations = new ArrayList<>();
    private Set<Organization> organizations = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "socialCause",orphanRemoval = true)
    private Set<Event> events;

    @Override
    public String toString() {
        return "SocialCause{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
