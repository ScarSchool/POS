package janssen.app.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Data
@ToString(callSuper=true)
@Entity
@DiscriminatorValue("pupil")
public class Pupil extends User {

  @ManyToMany
  List<Timeslot> joins;

}
