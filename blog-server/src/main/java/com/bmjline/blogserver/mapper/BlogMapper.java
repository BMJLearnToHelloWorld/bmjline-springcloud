package com.bmjline.blogserver.mapper;

import com.bmjline.blogserver.entity.BlogEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper {

    @Select("select id, blog_name, blog_descr, updated_time, blog_status, published_time from t_blog order by updated_time desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "blog_name", property = "blogName"),
            @Result(column = "blog_descr", property = "blogDescr"),
            @Result(column = "updated_time", property = "updatedTime"),
            @Result(column = "blog_status", property = "status"),
            @Result(column = "published_time", property = "publishedTime")
    })
    List<Map<String, Object>> queryAllBlogList(Map<String, Object> params);

    @Select("select id, blog_name, blog_descr, updated_time, blog_status from t_blog where blog_status = #{blogStatus} order by updated_time desc")
    List<Map<String, Object>> queryConditionedBlogList(Map<String, Object> params);

    @Select("select * from t_blog where id = #{blogId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "blog_name", property = "blogName"),
            @Result(column = "blog_descr", property = "blogDescr"),
            @Result(column = "blog_content", property = "blogContent"),
            @Result(column = "blog_length", property = "blogLength"),
            @Result(column = "reading_time", property = "readingTime"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "updated_time", property = "updatedTime"),
            @Result(column = "blog_status", property = "status"),
            @Result(column = "published_time", property = "publishedTime")
    })
    BlogEntity getBlogDetailById(@Param("blogId") String blogId);

    @Select("select tag from t_blog_tag where blog_id = #{blogId}")
    List<String> getBlogTagById(@Param("blogId") String blogId);

    @Select("insert into t_blog_tag (blog_id, tag) values (#{blogId}, #{tag})")
    void insertBlogTag(@Param("blogId") String blogId, @Param("tag") String tag);

    @Select("delete from t_blog_tag where blog_id = #{blogId}")
    void deleteBlogTagById(@Param("blogId") String blogId);

    @Select("insert into t_blog (blog_name, blog_descr, blog_content, blog_length, reading_time, blog_status) values " +
            "(#{blogName}, #{blogDescr}, #{blogContent}, #{blogLength}, #{readingTime}, #{blogStatus}) RETURNING id")
    String newBlog(Map<String, Object> params);

    @Update("update t_blog set blog_name = #{blogName}, blog_descr = #{blogDescr}, blog_content = #{blogContent}, " +
            "blog_length = #{blogLength}, reading_time = #{readingTime} where id = #{id}")
    void updateBlog(Map<String, Object> params);

    @Update("update t_blog set blog_status = '1', published_time = now() where id = #{blogId}")
    void publishBlogById(@Param("blogId") String blogId);

//    @Delete("delete from t_blog where id = #{blogId}")
    @Update("update t_blog set blog_status = '-1', published_time = null where id = #{blogId}")
    void deleteBlogById(@Param("blogId") String blogId);

}
