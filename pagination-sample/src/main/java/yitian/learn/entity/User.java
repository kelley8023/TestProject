package yitian.learn.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String name;
  private String password;
  private LocalDate birthday;
}
