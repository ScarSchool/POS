package janssen.app.models;

import lombok.Data;
import javax.persistence.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Mail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private LocalDate at;
  private LocalTime time;
  private String subject;
  private String content;
  private File attachment1;
  private File attachment2;
  private File attachment3;

  @ManyToOne
  private Admin sender;

  @ManyToMany
  private List<Responsible> receivers;

}
