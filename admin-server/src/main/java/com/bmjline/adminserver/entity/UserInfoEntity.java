package com.bmjline.adminserver.entity;

import com.bmjline.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author bmj
 */
public class UserInfoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6491970082435611293L;

    private String id;
    private String username;
    private String avatar;
    private List<Long> roles;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
