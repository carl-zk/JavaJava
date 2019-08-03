package com.hero.web.repository;

import com.hero.web.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author carl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
