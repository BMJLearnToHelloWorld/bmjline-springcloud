package com.bmjline.blogserver.mapper;

import com.bmjline.blogserver.entity.BlogEntity;
import com.bmjline.blogserver.entity.BlogShortViewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author bmj
 */
@Mapper
@Repository
public interface BlogMapper {

    /**
     * fetch blog list
     *
     * @param params params
     * @return List<Map<String, Object>>
     * @throws Exception exception
     */
    @Select("select id, blog_name, blog_descr, updated_time, blog_status, published_time, content_type from t_blog order by updated_time desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "blog_name", property = "blogName"),
            @Result(column = "blog_descr", property = "blogDescr"),
            @Result(column = "updated_time", property = "updatedTime"),
            @Result(column = "blog_status", property = "status"),
            @Result(column = "published_time", property = "publishedTime"),
            @Result(column = "content_type", property = "contentType")
    })
    List<BlogShortViewEntity> queryAllBlogList(Map<String, Object> params) throws Exception;

    /**
     * fetch blog list by condition
     *
     * @param params params
     * @return List<Map<String, Object>>
     * @throws Exception exception
     */
    @Select("select id, blog_name, blog_descr, updated_time, blog_status from t_blog where blog_status = #{status} order by updated_time desc")
    List<Map<String, Object>> queryConditionedBlogList(Map<String, Object> params) throws Exception;

    /**
     * fetch published blog list
     *
     * @param pageNo pageNo
     * @return List<Map<String, Object>>
     * @throws Exception exception
     */
    @Select("select id, blog_name, blog_descr, published_time, content_type from t_blog where blog_status = '1' order by published_time desc limit 10 offset 10 * (#{pageNo} - 1)")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "blog_name", property = "blogName"),
            @Result(column = "blog_descr", property = "blogDescr"),
            @Result(column = "published_time", property = "publishedTime"),
            @Result(column = "content_type", property = "contentType")
    })
    List<BlogShortViewEntity> queryPublishedBlogList(@Param("pageNo") Integer pageNo) throws Exception;

    /**
     * fetch blog detail by id
     *
     * @param blogId blogId
     * @return BlogEntity
     * @throws Exception exception
     */
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
            @Result(column = "published_time", property = "publishedTime"),
            @Result(column = "content_type", property = "contentType")
    })
    BlogEntity getBlogDetailById(@Param("blogId") String blogId) throws Exception;

    /**
     * fetch blog tags by id
     *
     * @param blogId blogId
     * @return List<String>
     * @throws Exception exception
     */
    @Select("select tag from t_blog_tag where blog_id = #{blogId}")
    List<String> getBlogTagById(@Param("blogId") String blogId) throws Exception;

    /**
     * insert blog tags
     *
     * @param blogId blogId
     * @param tag tag
     * @throws Exception exception
     */
    @Select("insert into t_blog_tag (blog_id, tag) values (#{blogId}, #{tag})")
    void insertBlogTag(@Param("blogId") String blogId, @Param("tag") String tag) throws Exception;

    /**
     * delete blog tags by id
     *
     * @param blogId blogId
     * @throws Exception exception
     */
    @Select("delete from t_blog_tag where blog_id = #{blogId}")
    void deleteBlogTagById(@Param("blogId") String blogId) throws Exception;

    /**
     * insert blog tags detail
     *
     * @param params params
     * @return String
     * @throws Exception exception
     */
    @Select("insert into t_blog (blog_name, blog_descr, blog_content, blog_length, reading_time, blog_status, content_type) values " +
            "(#{blogName}, #{blogDescr}, #{blogContent}, #{blogLength}, #{readingTime}, #{status}, #{contentType}) RETURNING id")
    String newBlog(Map<String, Object> params) throws Exception;

    /**
     * update blog detail
     *
     * @param params params
     * @throws Exception exception
     */
    @Update("update t_blog set blog_name = #{blogName}, blog_descr = #{blogDescr}, blog_content = #{blogContent}, " +
            "blog_length = #{blogLength}, reading_time = #{readingTime} where id = #{id}")
    void updateBlog(Map<String, Object> params) throws Exception;

    /**
     * update blog status to published by id
     *
     * @param blogId blogId
     * @throws Exception exception
     */
    @Update("update t_blog set blog_status = '1', published_time = now() where id = #{blogId}")
    void publishBlogById(@Param("blogId") String blogId) throws Exception;

    /**
     * update blog status to deleted by id
     *
     * @param blogId blogId
     * @throws Exception exception
     */
    @Update("update t_blog set blog_status = '-1', published_time = null where id = #{blogId}")
    void deleteBlogById(@Param("blogId") String blogId) throws Exception;
}
