package com.tschlatd.springdatajpa.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

}
