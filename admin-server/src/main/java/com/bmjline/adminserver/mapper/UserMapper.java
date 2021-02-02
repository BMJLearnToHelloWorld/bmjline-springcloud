package com.bmjline.adminserver.mapper;

import com.bmjline.adminserver.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bmj
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select password from t_user where username = #{username}")
    String selectPassByUsername(@Param("username") String username);

    @Select("select id from t_user where username = #{username}")
    String selectIdByUsername(@Param("username") String username);

    @Select("select username, gender, avatar, birthday, email, language, city, status, last_login_time, seq from t_user where id = #{userId}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "email", property = "email"),
            @Result(column = "language", property = "language"),
            @Result(column = "city", property = "city"),
            @Result(column = "status", property = "status"),
            @Result(column = "last_login_time", property = "lastLoginTime"),
            @Result(column = "seq", property = "seq")
    })
    UserInfoEntity selectUserInfoById(@Param("userId") String userId);

    @Select("select trd.role_name from t_user_role tur, t_role_def trd where tur.role_id = trd.id and tur.user_id = #{userId}")
    List<String> selectRolesByUserId(@Param("userId") String userId);
}
