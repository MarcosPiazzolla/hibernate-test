package br.com.piazzolla.hibernate.entity;

import java.util.List;

public interface HibernateBatchInsert {

  void of(List<? extends Object> all);

}