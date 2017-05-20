package common;

import common.IFactory;
import repository.ProductRepository;
import repository.UserRepository;
import repository.mysql.MySqlUserRepository;

/**
 * Created by carl on 5/20/17.
 */
public class MySqlFactory implements IFactory {

    public UserRepository getUserRepository() {
        return new MySqlUserRepository();
    }

    public ProductRepository getProductRepository() {
        return null;
    }
}
