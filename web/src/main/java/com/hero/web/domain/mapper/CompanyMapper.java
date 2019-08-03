package com.hero.web.domain.mapper;

import com.hero.web.domain.dto.CompanyDTO;
import com.hero.web.domain.entity.Company;
import org.mapstruct.Mapper;

/**
 * @author carl
 */
@Mapper
public interface CompanyMapper {
    CompanyDTO toCompanyDto(Company company);

    Company toCompany(CompanyDTO companyDto);
}
