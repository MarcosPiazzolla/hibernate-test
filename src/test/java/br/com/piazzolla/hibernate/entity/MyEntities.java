package br.com.piazzolla.hibernate.entity;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class MyEntities {

  public static MyEntity ENTITY_A = _new()
      .name("ENTITY_A")
      .newInstance();

  public static MyEntity ENTITY_B = _new()
      .name("ENTITY_B")
      .newInstance();

  public static MyEntity ENTITY_C = _new()
      .name("ENTITY_C")
      .newInstance();

  public static List<? extends Object> getAll() {
    return ImmutableList.<MyEntity> builder()
        .add(ENTITY_A)
        .add(ENTITY_B)
        .add(ENTITY_C)
        .build();
  }

  private static MyEntityBuilder _new() {
    return new MyEntityBuilder();
  }

}