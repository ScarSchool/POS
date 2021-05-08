package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @Type(value = Admin.class, name = "admin"),
        @Type(value = Responsible.class, name = "responsible"),
        @Type(value = Pupil.class, name = "pupil")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected int id;

    @NonNull
    @Column(unique = true, nullable = false)
    protected String email;

    @NonNull
    @Column(nullable = false)
    protected String name;

    @NonNull
    @Column(nullable = false)
    protected String pwdToken;

}