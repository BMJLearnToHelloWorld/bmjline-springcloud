package com.bmjline.blogserver.service;

import com.bmjline.blogserver.entity.BlogEntity;

import java.util.List;
import java.util.Map;

/**
 * @author bmj
 */
public interface BlogService {
    /**
     * fetch blogs
     *
     * @param req req
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryAllBlog(Map<String, Object> req);

    /**
     * fetch blogs by conditions
     *
     * @param req req
     * @return List<Map<String, Object>>
     * @throws Exception exception
     */
    List<Map<String, Object>> queryConditionedBlog(Map<String, Object> req) throws Exception;

    /**
     * fetch blog detail by blogId
     *
     * @param blogId blogId
     * @return BlogEntity
     * @throws Exception exception
     */
    BlogEntity blogDetail(String blogId) throws Exception;

    /**
     * new blog
     *
     * @param req req
     * @throws Exception exception
     */
    void newBlog(Map<String, Object> req) throws Exception;

    /**
     * update blog
     *
     * @param req req
     * @throws Exception exception
     */
    void updateBlog(Map<String, Object> req) throws Exception;

    /**
     * publish blog by blogId
     *
     * @param blogId blogId
     * @throws Exception exception
     */
    void publishBlogById(String blogId) throws Exception;

    /**
     * delete blog by blogId
     *
     * @param blogId blogId
     * @throws Exception exception
     */
    void deleteBlog(String blogId) throws Exception;

    /**
     * save and publish blog
     *
     * @param req req
     * @throws Exception exception
     */
    void saveAndPublishBlog(Map<String, Object> req) throws Exception;
}
