package com.carl.web.service;

import com.carl.web.domain.dto.UserDTO;
import com.carl.web.domain.entity.User;
import com.carl.web.domain.mapper.CompanyMapper;
import com.carl.web.domain.mapper.UserMapper;
import com.carl.web.domain.vo.UserVO;
import com.carl.web.repository.CompanyRepository;
import com.carl.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public UserDTO getUser(Long id) {
        return userMapper.toUserDto(userRepository.getOne(id));
    }

    public List<UserDTO> getUsersByUuids(List<String> uuids) {
        return userMapper.toUsers(userRepository.findAllByUuidIn(uuids));
    }

    public UserDTO updateUser(UserVO userVO) {
        User user = userRepository.getOne(userVO.getId());
        userMapper.updateUser(userVO, user);
        return userMapper.toUserDto(userRepository.save(user));
    }
}
