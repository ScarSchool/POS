package janssen.spring.entities;

import janssen.spring.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sales {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(JsonViews.Summary.class)
  protected long id;
  
  @NotNull
  @ManyToOne
  @JsonView(JsonViews.Summary.class)
  protected AnimalShelter shelter;
  
  @NotNull
  @ManyToOne
  @JsonView(JsonViews.Summary.class)
  protected Animal animal;
  
  @Positive
  @Column(nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected double price;
  
  @NotNull
  @PastOrPresent
  @Column(nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected LocalDate date;
  
}
