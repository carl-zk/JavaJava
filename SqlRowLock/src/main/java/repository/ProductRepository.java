package repository;

import entity.Product;

/**
 * Created by carl on 5/20/17.
 */
public interface ProductRepository {
    Product get(String id);
}
