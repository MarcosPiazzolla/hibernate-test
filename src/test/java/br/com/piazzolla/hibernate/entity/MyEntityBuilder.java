package br.com.piazzolla.hibernate.entity;

import br.com.piazzolla.hibernate.entity.MyEntity;
import br.com.piazzolla.hibernate.entity.MyEntityHibernate;

public class MyEntityBuilder implements MyEntity.Builder {

  private int id;

  private String name;

  @Override
  public MyEntity newInstance() {
    MyEntityHibernate impl = new MyEntityHibernate(this);
    impl.setId(id);
    return impl;
  }

  public MyEntityBuilder id(int id) {
    this.id = id;
    return this;
  }

  public MyEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

}