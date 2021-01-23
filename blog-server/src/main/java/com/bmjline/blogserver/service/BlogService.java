package com.bmjline.blogserver.service;

import com.bmjline.blogserver.entity.BlogEntity;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Map<String, Object>> queryAllBlog(Map<String, Object> req);

    List<Map<String, Object>> queryConditionedBlog(Map<String, Object> req) throws Exception;

    BlogEntity blogDetail(String blogId) throws Exception;

    void newBlog(Map<String, Object> req) throws Exception;

    void updateBlog(Map<String, Object> req) throws Exception;

    void publishBlogById(String blogId) throws Exception;

    void deleteBlog(String blogId) throws Exception;

    void saveAndPublishBlog(Map<String, Object> req) throws Exception;
}
