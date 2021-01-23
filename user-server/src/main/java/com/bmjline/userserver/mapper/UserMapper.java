package com.bmjline.userserver.mapper;

import com.bmjline.userserver.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into t_user (wx_openid, user_name, gender, avatar_url, language, country, city, province) values (#{wxOpenId}, #{userName}, #{gender}, #{avatarUrl}, #{language}, #{country}, #{city}, #{province})")
    void insertUserWhenLogin(UserEntity userEntity);

    @Select("select * from t_user where wx_openid = #{wxOpenId}")
    @Results({
            @Result(column = "wx_openid", property = "wxOpenId"),
            @Result(column = "wx_openid", property = "userName"),
            @Result(column = "wx_openid", property = "userSignature"),
            @Result(column = "wx_openid", property = "avatarUrl"),
            @Result(column = "wx_openid", property = "roleId"),
            @Result(column = "wx_openid", property = "statusId"),
            @Result(column = "wx_openid", property = "createdTime", jdbcType = JdbcType.TIMESTAMP_WITH_TIMEZONE),
            @Result(column = "wx_openid", property = "lastLoginTime", jdbcType = JdbcType.TIMESTAMP_WITH_TIMEZONE),
            @Result(column = "wx_openid", property = "updatedTime", jdbcType = JdbcType.TIMESTAMP_WITH_TIMEZONE)
    })
    Map<String, Object> selectOneByOpenId(String wxOpenId);

    @Update("update t_user set user_name = #{userName}, gender = #{gender}, avatar_url = #{avatarUrl}, language = #{language}, country = #{country}, city = #{city}, province = #{province}, last_login_time = #{lastLoginTime, jdbcType = TIMESTAMP_WITH_TIMEZONE}, updated_time = #{updatedTime, jdbcType = TIMESTAMP_WITH_TIMEZONE} where wx_openid = #{wxOpenId}")
    void updateUserWhenLogin(UserEntity userEntity);

    void updateUser(UserEntity userEntity);
}
