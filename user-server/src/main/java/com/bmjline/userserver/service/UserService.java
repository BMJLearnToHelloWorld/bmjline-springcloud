package com.bmjline.userserver.service;

import com.bmjline.userserver.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    void login(Map<String, Object> req);

//    void insertOne(UserEntity userEntity);

    Map<String, Object> selectOne(String openId);

    List<Map<String, Object>> queryAll();

    void updateOne(Map<String, Object> req);
}
