package com.wework.repository;

import com.wework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author carl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
