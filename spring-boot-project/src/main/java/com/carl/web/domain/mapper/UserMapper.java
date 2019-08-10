package com.carl.web.domain.mapper;

import com.carl.web.domain.dto.UserDTO;
import com.carl.web.domain.entity.User;
import com.carl.web.domain.vo.UserVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author carl
 */
@Mapper
public interface UserMapper {
    UserDTO toUserDto(User user);

    User toUser(UserVO userRequest);

    List<UserDTO> toUsers(List<User> users);
}
