package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;
  private String label;
  private double defaultPrice;

  @ManyToOne
  private Admin organiser;

}
