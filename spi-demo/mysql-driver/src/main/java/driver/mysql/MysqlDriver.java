package driver.mysql;

import driver.api.Driver;

/**
 * @author carl
 */
public class MysqlDriver implements Driver {

  @Override
  public void connect(String url) {
    System.out.println("mysql connect to " + url);
  }
}
