package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Admin extends User {

    @OneToMany(mappedBy = "sender", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Mail> mails;

    @OneToMany(mappedBy = "organiser", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Event> organises;
}
