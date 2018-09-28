package driver.api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author carl
 */
public final class DriverManager {

  static List<Driver> drivers = new LinkedList<>();

  static {
    ServiceLoader serviceLoader = ServiceLoader.load(DriverProvider.class);
    Iterator<? extends DriverProvider> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      DriverProvider provider = iterator.next();
      drivers.addAll(provider.getAvailableDrivers());
    }
  }

  public static List<Driver> getDrivers() {
    return drivers;
  }
}
