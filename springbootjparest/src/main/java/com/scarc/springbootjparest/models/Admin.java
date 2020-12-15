package com.scarc.springbootjparest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Admin {

    @OneToMany(mappedBy = "sender", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Mail> mails;

    @OneToMany(mappedBy = "organiser", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Event> organises;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
