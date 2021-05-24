package janssen.spring.entities;

import janssen.spring.JsonViews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnimalShelter {
  
  @Id
  @NotBlank
  @JsonView(JsonViews.Summary.class)
  protected String address;
  
  @OneToMany
  @ToString.Exclude
  protected Set<Animal> animals;
  
  @NotNull
  @Column(nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected String phoneNumber;
  
  @OneToMany(mappedBy = "shelter")
  @ToString.Exclude
  @JsonIgnoreProperties({ "shelter" })
  protected Set<Sales> sales;
  
}
