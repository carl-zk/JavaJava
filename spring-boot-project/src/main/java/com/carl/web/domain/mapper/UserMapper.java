package com.carl.web.domain.mapper;

import com.carl.web.domain.dto.UserDTO;
import com.carl.web.domain.entity.User;
import com.carl.web.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author carl
 */
@Mapper
public interface UserMapper {
    UserDTO toUserDto(User user);

    User toUser(UserVO userRequest);

    List<UserDTO> toUsers(List<User> users);

    void updateUser(UserVO userVO, @MappingTarget User user);
}
