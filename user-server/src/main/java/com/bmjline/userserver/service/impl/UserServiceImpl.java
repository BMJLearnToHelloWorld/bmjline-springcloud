package com.bmjline.userserver.service.impl;

import com.bmjline.userserver.entity.UserEntity;
import com.bmjline.userserver.mapper.UserMapper;
import com.bmjline.userserver.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(Map<String, Object> req) {
        Map<String, Object> selectResult = selectOne(req.get("openid") == null ? "" : (String) req.get("openid"));
        UserEntity userEntity = setUserInfoFromCloudFunc(req);
        if (selectResult != null && !selectResult.isEmpty()) {
            updateOneWhenLogin(userEntity);
        } else {
            insertOneWhenLogin(userEntity);
        }
    }

    @Override
    public Map<String, Object> selectOne(String wxOpenId) {
        return userMapper.selectOneByOpenId(wxOpenId);
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return null;
    }

    @Override
    public void updateOne(Map<String, Object> req) {
        Map<String, Object> selectResult = selectOne(req.get("openid") == null ? "" : (String) req.get("openid"));
        UserEntity userEntity = setUserInfoFromCloudFunc(req);
        userEntity.setUserSignature((String) req.get("userSignature"));
        userEntity.setBirthday((DateTime) req.get("birthday"));
        userEntity.setLastLoginTime(selectResult.get("updatedTime") == null ? null : (DateTime) selectResult.get("updatedTime"));
        userEntity.setUpdatedTime(new DateTime());
        userMapper.updateUser(userEntity);
    }

    private UserEntity setUserInfoFromCloudFunc(Map<String, Object> req) {
        UserEntity userEntity = new UserEntity();
        userEntity.setWxOpenId(req.get("openid") == null ? "" : (String) req.get("openid"));
        userEntity.setAvatarUrl((String) req.get("avatarUrl"));
        userEntity.setUserName(req.get("nickName") == null ? "" : (String) req.get("nickName"));
        userEntity.setGender((Integer) req.get("gender"));
        userEntity.setLanguage((String) req.get("language"));
        userEntity.setCountry((String) req.get("country"));
        userEntity.setCity((String) req.get("city"));
        userEntity.setProvince((String) req.get("province"));
        return userEntity;
    }

    private void updateOneWhenLogin(UserEntity userEntity) {
        userMapper.updateUserWhenLogin(userEntity);
    }

    private void insertOneWhenLogin(UserEntity userEntity) {
        userMapper.insertUserWhenLogin(userEntity);
    }
}
