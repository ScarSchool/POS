package janssen.app.models;

import lombok.Data;
import javax.persistence.*;

import java.util.List;

@Data
@Entity
public class Participation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private double price;
  private double paidAlready;
  private String comments;

  @ManyToOne
  private Event at;

  @ManyToOne
  private Responsible responsible;

  @ManyToOne
  private Company company;

  @ManyToMany
  private List<Department> interestedIn;

  @OneToMany
  private List<Timeslot> slots;

}
