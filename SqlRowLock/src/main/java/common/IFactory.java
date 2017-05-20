package common;

import repository.ProductRepository;
import repository.UserRepository;

/**
 * Created by carl on 5/20/17.
 */
public interface IFactory {
    UserRepository getUserRepository();
    ProductRepository getProductRepository();
}
