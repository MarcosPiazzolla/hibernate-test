package br.com.piazzolla.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MY_ENTITY", schema = "HIBERNATE")
public class MyEntityHibernate implements MyEntity {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private int id;

  @Column(name = "NAME")
  private String name;

  MyEntityHibernate() {
  }

  public MyEntityHibernate(Builder builder) {
    this.name = builder.getName();
  }

  @Override
  public int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

}