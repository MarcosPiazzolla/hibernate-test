package br.com.piazzolla.hibernate.entity;

public interface MyEntity {

  interface Builder extends br.com.piazzolla.hibernate.base.Builder<MyEntity> {

    String getName();

  }

  int getId();

  String getName();

}