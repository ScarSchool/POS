package janssen.app.models;

import lombok.Data;
import javax.persistence.*;

import java.time.LocalTime;

@Data
@Entity
public class Timeslot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private LocalTime starts;
  private LocalTime ends;
  private int maxParticipants;

}
