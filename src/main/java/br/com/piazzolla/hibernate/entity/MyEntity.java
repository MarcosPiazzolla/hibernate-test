package br.com.piazzolla.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MY_ENTITY", schema = "HTEST")
public class MyEntity {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  @SuppressWarnings("unused")
  private MyEntity() {
  }

  MyEntity(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}