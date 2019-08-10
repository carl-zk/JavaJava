package com.carl.web.domain.mapper;

import com.carl.web.domain.dto.CompanyDTO;
import com.carl.web.domain.entity.Company;
import org.mapstruct.Mapper;

/**
 * @author carl
 */
@Mapper
public interface CompanyMapper {
    CompanyDTO toCompanyDto(Company company);

    Company toCompany(CompanyDTO companyDto);
}
