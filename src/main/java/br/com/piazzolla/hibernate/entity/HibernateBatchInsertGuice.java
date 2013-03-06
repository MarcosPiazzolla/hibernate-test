package br.com.piazzolla.hibernate.entity;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

class HibernateBatchInsertGuice implements HibernateBatchInsert {

  private EntityManagerFactory emf;
  private static final String UNIT = "br.com.piazzolla.persistence";

  @Override
  public void of(List<? extends Object> all) {
    Iterator<? extends Object> iterator = all.iterator();
    Object obj = iterator.next();
    Class<? extends Object> realTypeOfList = obj.getClass();
    String clazzName = realTypeOfList.getSimpleName();

    emf = Persistence.createEntityManagerFactory(UNIT);
    EntityManager em = emf.createEntityManager();

    if (tableLines(em, clazzName) > 0) {
      truncateTable(em, clazzName);
    }

    batchInsert(em, all);

    em.close();
    emf.close();
  }

  private void batchInsert(EntityManager em, List<? extends Object> all) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();

    for (int i = 0; i < all.size(); i++) {
      em.persist(all.get(i));

      if (i % 20 == 0) {
        em.flush();
        em.clear();
      }
    }

    transaction.commit();
  }

  private void truncateTable(EntityManager em, String clazzName) {
    String formattedQuery = String.format("truncate table %s", clazzName);
    Query truncateQuery = em.createQuery(formattedQuery);
    truncateQuery.executeUpdate();

  }

  private int tableLines(EntityManager em, String clazzName) {
    String formattedQuery;
    formattedQuery = String.format("select count(e) from %s e", clazzName);

    Query query = em.createQuery(formattedQuery);
    Number result = (Number) query.getSingleResult();

    return result.intValue();
  }

}