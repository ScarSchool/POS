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
public class Animal {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(JsonViews.Summary.class)
  protected long id;
  
  @NotBlank
  @Column(unique = true, nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected String name;
  
  @NotBlank
  @Column(nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected String race;
  
  @PastOrPresent
  @Column(nullable = false)
  protected LocalDate dateOfBirth;
  
  protected String note;
  
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  protected Gender gender;
  
  @Column(nullable = false)
  @JsonView(JsonViews.Summary.class)
  protected boolean sold;
  
}
