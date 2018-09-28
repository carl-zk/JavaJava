package driver.api;

import java.util.List;

/**
 * @author carl
 */
public interface DriverProvider {

  /**
   * create drivers
   *
   * @return drivers
   */
  List<Driver> getAvailableDrivers();
}
