package com.carl.web.repository;

import com.carl.web.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author carl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUuidIn(List<String> uuids);
}
