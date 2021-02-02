package com.bmjline.adminserver.service;

import com.bmjline.adminserver.entity.UserInfoEntity;

import java.util.List;

/**
 * @author bmj
 */
public interface UserService {
    /**
     * get Password By Username
     *
     * @param username username
     * @return String password
     */
    String getPassByUsername(String username);

    String getUserIdByUsername(String username);

    UserInfoEntity getUserInfoByUserId(String userId);

    List<String> getRolesByUserId(String userId);
}
