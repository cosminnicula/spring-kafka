package dev.intermediatebox.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // or customize for the entire application via application.yml -> e.g. spring.jackson.property-naming-strategy
public class Employee {
  private String employeeId;
  private String name;
  private LocalDate birthDate;
}
