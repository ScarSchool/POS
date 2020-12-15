package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Pupil extends User {


  @ManyToMany
  @JoinTable(
          name = "joins",
          joinColumns = @JoinColumn(name = "pupil_id"),
          inverseJoinColumns = @JoinColumn(name = "timeslot_id"))
  private List<TimeSlot> joins;

}
