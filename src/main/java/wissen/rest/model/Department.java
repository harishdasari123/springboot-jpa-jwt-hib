package wissen.rest.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "department")
@Data
public class Department implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_generator")
  private Long id;


  private String name;


  // getters and setters
}