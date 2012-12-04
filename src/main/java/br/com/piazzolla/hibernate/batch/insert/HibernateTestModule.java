package br.com.piazzolla.hibernate.batch.insert;


import com.google.inject.Binder;
import com.google.inject.Module;

public class HibernateTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    binder.bind(BatchInsert.class).to(BatchInsertGuice.class);
  }

}