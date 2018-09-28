package driver.api;

/**
 * @author carl
 */
public interface Driver {

  /**
   * 连接
   *
   * @param url the URL of the database to which to connect
   */
  void connect(String url);
}
