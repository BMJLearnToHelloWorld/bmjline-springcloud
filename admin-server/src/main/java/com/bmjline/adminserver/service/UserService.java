package com.bmjline.adminserver.service;

import com.bmjline.adminserver.entity.UserInfoEntity;

import java.util.List;

/**
 * @author bmj
 */
public interface UserService {
    /**
     * get password by username
     *
     * @param username username
     * @return String password
     */
    String getPassByUsername(String username);

    /**
     * get userId by username
     *
     * @param username username
     * @return String id
     */
    String getUserIdByUsername(String username);

    /**
     * get userinfo by userId
     *
     * @param userId userId
     * @return UserInfoEntity
     */
    UserInfoEntity getUserInfoByUserId(String userId);

    /**
     * get roles by userId
     *
     * @param userId userId
     * @return List<String>
     */
    List<String> getRolesByUserId(String userId);
}
