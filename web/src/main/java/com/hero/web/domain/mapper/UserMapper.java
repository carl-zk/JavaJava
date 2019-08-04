package com.hero.web.domain.mapper;

import com.hero.web.domain.dto.UserDTO;
import com.hero.web.domain.entity.User;
import com.hero.web.domain.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * @author carl
 */
@Mapper
public interface UserMapper {
    UserDTO toUserDto(User user);

    User toUser(UserVO userRequest);
}
