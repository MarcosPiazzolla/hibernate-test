package br.com.piazzolla.hibernate.entity;

import static com.google.common.collect.Lists.transform;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.inject.Inject;

@Test
public class HibernateBatchInsertTest {

  @Inject
  private HibernateBatchInsert batchInsert;

  private EntityManagerFactory emf;

  @BeforeClass
  public void setUp() {
    String unit = "br.com.piazzolla.persistence";
    emf = Persistence.createEntityManagerFactory(unit);
  }

  @AfterClass
  public void tearDown() {
    emf.close();
  }

  public void hibernate_batch_insert_should_insert_some_entities() {
    EntityManager em = emf.createEntityManager();

    List<MyEntity> before;
    before = em.createQuery("from MyEntity", MyEntity.class).getResultList();

    assertThat(before.size(), equalTo(0));

    batchInsert.of(MyEntities.getAll());

    List<MyEntity> after;
    after = em.createQuery("from MyEntiy", MyEntity.class).getResultList();

    List<String> res = transform(after, new ToMyEntityName());
    assertThat(res.size(), equalTo(3));
    assertThat(res.get(0), equalTo("ENTITY_A"));
    assertThat(res.get(1), equalTo("ENTITY_B"));
    assertThat(res.get(2), equalTo("ENTITY_C"));

    em.close();
  }

  private class ToMyEntityName implements Function<MyEntity, String> {
    @Override
    public String apply(MyEntity input) {
      return input.getName();
    }
  }

}