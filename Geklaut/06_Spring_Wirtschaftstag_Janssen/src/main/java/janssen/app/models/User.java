package janssen.app.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @Type(value = Admin.class, name = "admin"),
  @Type(value = Responsible.class, name = "responsible"),
  @Type(value = Pupil.class, name = "pupil")
})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;
  protected String email;
  protected String name;
  protected String pwdToken;

}
