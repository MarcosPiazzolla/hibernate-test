package br.com.piazzolla.hibernate.entity;

import java.util.List;

import br.com.piazzolla.hibernate.batch.insert.Insertable;
import br.com.piazzolla.hibernate.entity.MyEntity;

import com.google.common.collect.ImmutableList;

public class MyEntityFakes implements Insertable {

  public static final MyEntity A = _new()
      .name("My Entity A")
      .newInstance();
  
  public static final MyEntity B = _new()
      .name("My Entity B")
      .newInstance();

  public static final MyEntity C = _new()
      .name("My Entity C")
      .newInstance();
  
  private static List<MyEntity> ALL = ImmutableList.<MyEntity> builder()
      .add(A)
      .add(B)
      .add(C)
  .build();
  
  public static List<MyEntity> getAll() {
    return ALL;
  }

  private static MyEntityBuilder _new() {
    return new MyEntityBuilder();
  }

}