package com.carl.web.domain.mapper;

import com.carl.web.domain.dto.CompanyDTO;
import com.carl.web.domain.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author carl
 */
@Mapper
public interface CompanyMapper {
    @Mappings({
            @Mapping(target = "employees", ignore = true)
    })
    CompanyDTO toCompanyDto(Company company);

    @Mappings({
            @Mapping(target = "employees", ignore = true)
    })
    Company toCompany(CompanyDTO companyDto);
}
