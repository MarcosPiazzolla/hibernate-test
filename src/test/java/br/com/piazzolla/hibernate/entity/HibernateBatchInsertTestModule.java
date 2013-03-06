package br.com.piazzolla.hibernate.entity;

import com.google.inject.Binder;
import com.google.inject.Module;

public class HibernateBatchInsertTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    binder.install(new HibernateModule());
  }

}