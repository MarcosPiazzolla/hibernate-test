package br.com.piazzolla.hibernate.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.inject.ImplementedBy;

@ImplementedBy(HibernateBatchInsert.class)
class HibernateBatchInsertGuice implements HibernateBatchInsert {

  private EntityManagerFactory emf;

  @Override
  public void of(List<? extends Object> all) {
    if (count() > 0) {

    }
  }

  private int count() {
    String persistence = "br.com.piazzolla.persistence";
    emf = Persistence.createEntityManagerFactory(persistence);

    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("select count(m) from MyEntity m");
    Number result = (Number) query.getSingleResult();

    em.close();
    emf.close();

    return result.intValue();
  }

}