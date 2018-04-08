package dao;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hero
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.name=?1")
    public User findByName(@Param("name") String name);

    @Query(value = "select * from tbl_user", nativeQuery = true)
    public List<User> listAllUser();
}
