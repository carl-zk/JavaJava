package com.carl.web.domain.mapper;

import com.carl.web.domain.dto.UserDTO;
import com.carl.web.domain.entity.User;
import com.carl.web.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author carl
 */
@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target = "company.employees", ignore = true)
    })
    UserDTO toUserDto(User user);

    @Mappings({
            @Mapping(target = "company.employees", ignore = true)
    })
    User toUser(UserVO userRequest);

    List<UserDTO> toUsers(List<User> users);

    void updateUser(UserVO userVO, @MappingTarget User user);
}
