package com.hero.web.service;

import com.hero.web.domain.dto.UserDTO;
import com.hero.web.domain.entity.User;
import com.hero.web.domain.mapper.CompanyMapper;
import com.hero.web.domain.mapper.UserMapper;
import com.hero.web.domain.vo.UserVO;
import com.hero.web.repository.CompanyRepository;
import com.hero.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author carl
 */
@Service
public class UserService {
    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    private UserMapper userMapper;
    private CompanyMapper companyMapper;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository,
                       UserMapper userMapper, CompanyMapper companyMapper) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.userMapper = userMapper;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public UserDTO register(UserVO userVO) {
        User user = userMapper.toUser(userVO);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }
}
