package br.com.piazzolla.hibernate.batch.insert;

import java.util.List;

import br.com.piazzolla.hibernate.entity.MyEntity;

import com.google.inject.ImplementedBy;

@ImplementedBy(BatchInsertGuice.class)
public interface BatchInsert {

  void doInsert(List<MyEntity> all);// usar generics

}