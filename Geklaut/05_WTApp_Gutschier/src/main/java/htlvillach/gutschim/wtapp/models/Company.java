package htlvillach.gutschim.wtapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String zipTown;
  private String street;
  private String phone;
  private String email;
  private String replyTo;
  private String comments;

  @OneToMany(mappedBy = "company", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Participation> participations;

}
