package com.hero.web.repository;

import com.hero.web.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author carl
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
