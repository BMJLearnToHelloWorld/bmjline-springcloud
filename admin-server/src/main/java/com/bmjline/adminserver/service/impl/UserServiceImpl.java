package com.bmjline.adminserver.service.impl;

import com.bmjline.adminserver.entity.UserInfoEntity;
import com.bmjline.adminserver.mapper.UserMapper;
import com.bmjline.adminserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String getUserIdByUsername(String username) {
        return userMapper.selectIdByUsername(username);
    }

    @Override
    public UserInfoEntity getUserInfoByUserId(String userId) {
        return userMapper.selectUserInfoById(userId);
    }

    @Override
    public List<String> getRolesByUserId(String userId) {
        return userMapper.selectRolesByUserId(userId);
    }
}
