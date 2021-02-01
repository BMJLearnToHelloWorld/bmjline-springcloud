package com.bmjline.common.entity;

import java.io.Serializable;

/**
 * @author bmj
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -6937363584703182146L;

    private String username;
    private String password;
    private String clientId;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
