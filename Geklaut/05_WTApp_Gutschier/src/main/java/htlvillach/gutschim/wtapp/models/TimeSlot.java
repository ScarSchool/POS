package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class TimeSlot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalTime starts;
  private LocalTime ends;
  private int maxParticipants;

  @ManyToOne
  private Participation participation;

  @ManyToMany
  private List<Pupil> pupils;
}
