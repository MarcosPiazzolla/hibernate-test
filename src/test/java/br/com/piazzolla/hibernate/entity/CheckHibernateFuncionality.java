package br.com.piazzolla.hibernate.entity;

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

@Test
public class CheckHibernateFuncionality {

  private EntityManagerFactory emf;

  @BeforeClass
  protected void setUp() {
    String unit = "br.com.piazzolla.persistence";
    emf = Persistence.createEntityManagerFactory(unit);
  }

  @AfterClass
  protected void tearDown() {
    emf.close();
  }

  public void should_insert_my_entity_in_db() {
    persistSomeEntitiesInDb();

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    List<MyEntity> entities;
    entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();

    List<String> res = transform(entities, new ToName());
    assertThat(res.size(), equalTo(2));
    assertThat(res.get(0), equalTo("A"));
    assertThat(res.get(1), equalTo("B"));

    em.close();
  }

  private class ToName implements Function<MyEntity, String> {
    @Override
    public String apply(MyEntity input) {
      return input.getName();
    }
  }

  private void persistSomeEntitiesInDb() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    em.persist(new MyEntity("A"));
    em.persist(new MyEntity("B"));

    em.getTransaction().commit();
    em.close();
  }

}