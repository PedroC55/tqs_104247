package com.ua.containers.model;

import javax.persistence.*;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

}