package driver.mysql;

import driver.api.Driver;
import driver.api.DriverProvider;
import java.util.Arrays;
import java.util.List;

/**
 * @author carl
 */
public class MysqlDriverProvider implements DriverProvider {

  @Override
  public List<Driver> getAvailableDrivers() {
    return Arrays.asList(new MysqlDriver(), new MysqlDriver());
  }
}
