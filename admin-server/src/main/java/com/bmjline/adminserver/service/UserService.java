package com.bmjline.adminserver.service;

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
}
