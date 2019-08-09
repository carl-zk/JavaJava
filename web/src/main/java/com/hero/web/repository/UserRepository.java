package com.hero.web.repository;

import com.hero.web.domain.entity.User;
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
