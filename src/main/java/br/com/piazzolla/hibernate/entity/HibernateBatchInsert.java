package br.com.piazzolla.hibernate.entity;

import java.util.List;

import com.google.inject.ImplementedBy;

@ImplementedBy(HibernateBatchInsertGuice.class)
public interface HibernateBatchInsert {

  void of(List<? extends Object> all);

}