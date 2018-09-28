package app;

import driver.api.Driver;
import driver.api.DriverManager;
import java.util.List;

/**
 * @author carl
 */
public class Main {

  public static void main(String[] args) {
    List<Driver> drivers = DriverManager.getDrivers();
    drivers.forEach(driver -> driver.connect("database"));
  }
}
