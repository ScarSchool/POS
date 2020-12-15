package janssen.app.models;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String label;
  private LocalDate date;
  private double defaultPrice;

  @ManyToOne
  private Admin organiser;

}
