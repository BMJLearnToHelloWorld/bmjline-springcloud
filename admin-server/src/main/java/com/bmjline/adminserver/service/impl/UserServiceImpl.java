package com.bmjline.adminserver.service.impl;

import com.bmjline.adminserver.mapper.UserMapper;
import com.bmjline.adminserver.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author bmj
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public String getPassByUsername(String username) {
        return userMapper.selectPassByUsername(username);
    }
}
