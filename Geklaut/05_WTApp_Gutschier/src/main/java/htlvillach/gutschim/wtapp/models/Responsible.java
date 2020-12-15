package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Responsible extends User {
    @ManyToOne
    private Mail mail;

    @OneToMany(mappedBy = "responsible", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Participation> responsibleFor;
}
