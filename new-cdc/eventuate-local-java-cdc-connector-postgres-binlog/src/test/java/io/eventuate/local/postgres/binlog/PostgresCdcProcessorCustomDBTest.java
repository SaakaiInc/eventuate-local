package io.eventuate.local.postgres.binlog;


import io.eventuate.local.testutil.CustomDBCreator;
import io.eventuate.local.testutil.CustomDBTestConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CustomDBTestConfiguration.class, PostgresBinlogCdcIntegrationTestConfiguration.class})
@IntegrationTest
public class PostgresCdcProcessorCustomDBTest extends AbstractPostgresCdcProcessorTest {

  @Autowired
  private CustomDBCreator customDBCreator;

  @Before
  public void createCustomDB() {
    customDBCreator.create(sqlList -> {
      sqlList.set(0, sqlList.get(0).replace("CREATE SCHEMA", "CREATE SCHEMA IF NOT EXISTS"));
      for (int i = 0; i < sqlList.size(); i++) sqlList.set(i, sqlList.get(i).replace("eventuate", "custom"));
      return sqlList;
    });
  }
}
