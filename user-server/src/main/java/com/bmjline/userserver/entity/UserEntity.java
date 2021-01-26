package com.bmjline.userserver.entity;

import com.bmjline.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author bmj
 */
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6491970082435611293L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
