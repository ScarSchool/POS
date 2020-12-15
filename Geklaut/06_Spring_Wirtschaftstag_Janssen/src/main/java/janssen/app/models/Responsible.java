package janssen.app.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(callSuper=true)
@Entity
@DiscriminatorValue("responsible")
public class Responsible extends User {

}
