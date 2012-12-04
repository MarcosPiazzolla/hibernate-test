package br.com.piazzolla.hibernate.batch.insert;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import br.com.piazzolla.hibernate.entity.MyEntity;
import br.com.piazzolla.hibernate.entity.MyEntityFakes;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

@Test
@Guice(modules = { HibernateTestModule.class })
public class BatchInsertTest {

  @Inject
  private BatchInsert insert;

  private EntityManagerFactory emFactory;

  @BeforeClass
  public void init() {
    emFactory = Persistence
        .createEntityManagerFactory("br.com.piazzolla.hibernate.jpa");// criar
                                                                      // persisntece.xml

    insert.doInsert(MyEntityFakes.getAll());// recebe uma lista de insertables
  }

  @AfterClass
  public void close() {
    emFactory.close();
  }

  public void verify_data_insertion() {
    List<MyEntity> counter = MyEntityFakes.getAll();
    List<String> proof = Lists.transform(counter, new ToString());

    List<MyEntity> list = searchForMyEntityInDb();
    List<String> res = Lists.transform(list, new ToString());

    assertThat(proof.size(), equalTo(res.size()));
    assertThat(proof, equalTo(res));
  }

  private List<MyEntity> searchForMyEntityInDb() {
    EntityManager em = emFactory.createEntityManager();
    em.getTransaction().begin();

    List<MyEntity> res = em.createQuery("from MyEntity", MyEntity.class)
        .getResultList();

    em.close();

    return res;
  }

  private class ToString implements Function<MyEntity, String> {
    @Override
    public String apply(MyEntity obj) {
      return Objects.toStringHelper(this)
          .addValue(obj)
          .toString();
    }
  }

}