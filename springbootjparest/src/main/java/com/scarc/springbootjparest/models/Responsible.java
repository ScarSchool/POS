package com.scarc.springbootjparest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Responsible {
    @ManyToOne
    private Mail mail;

    @OneToMany(mappedBy = "responsible", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Participation> responsibleFor;

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
