package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("responsible")
public class Responsible extends User {

//    @ManyToMany(mappedBy = "receivers")
//    @JsonIgnoreProperties("receivers")
//    private Set<Mail> mails;

}
