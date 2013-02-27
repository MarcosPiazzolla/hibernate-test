package br.com.piazzolla.hibernate.entity;

public class MyEntityBuilder {

  private String name;

  public MyEntity newInstance() {
    return new MyEntity();
  }

  public String getName() {
    return name;
  }

  public MyEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

}