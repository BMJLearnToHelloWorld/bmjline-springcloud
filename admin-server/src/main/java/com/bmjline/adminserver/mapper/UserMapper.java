package com.bmjline.adminserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author bmj
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select password from t_user where username = #{username}")
    String selectPassByUsername(@Param("username") String username);
}
