package com.bmjline.blogserver.service.impl;

import com.bmjline.blogserver.entity.BlogEntity;
import com.bmjline.blogserver.mapper.BlogMapper;
import com.bmjline.blogserver.service.BlogService;
import com.bmjline.common.utils.ObjectUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;

    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public List<Map<String, Object>> queryAllBlog(Map<String, Object> params) {
        List<Map<String, Object>> allBlogList = blogMapper.queryAllBlogList(params);
        return allBlogList;
    }

    @Override
    public List<Map<String, Object>> queryConditionedBlog(Map<String, Object> params) throws Exception {
        return blogMapper.queryConditionedBlogList(params);
    }

    @Override
    public BlogEntity blogDetail(String blogId) throws Exception {
        BlogEntity blogEntity = blogMapper.getBlogDetailById(blogId);
        blogEntity.setBlogTag(blogMapper.getBlogTagById(blogId));
        return blogEntity;
    }

    @Override
    public void newBlog(Map<String, Object> params) throws Exception {
        createBlog(params);
    }

    private String createBlog(Map<String, Object> params) {
        String newBlogId = blogMapper.newBlog(params);
        List<String> tagList = ObjectUtil.castList(params.get("blogTag"), String.class);
        for (String tag : tagList) {
            blogMapper.insertBlogTag(newBlogId, tag);
        }
        return newBlogId;
    }

    @Override
    public void updateBlog(Map<String, Object> params) throws Exception {
        saveBlog(params);
    }

    private void saveBlog(Map<String, Object> params) throws Exception {
        String blogId = params.get("id") == null ? "" : params.get("id").toString();
        if (blogId.isEmpty()) {
            throw new Exception("blogId is empty, please check!");
        }
        blogMapper.updateBlog(params);
        blogMapper.deleteBlogTagById(blogId);
        List<String> tagList = ObjectUtil.castList(params.get("blogTag"), String.class);
        for (String tag : tagList) {
            blogMapper.insertBlogTag(blogId, tag);
        }
    }

    @Override
    public void saveAndPublishBlog(Map<String, Object> params) throws Exception {
        String blogId = params.get("id") == null ? "" : params.get("id").toString();
        if (blogId.isEmpty()) {
            blogId = createBlog(params);
        } else {
            saveBlog(params);
        }
        blogMapper.publishBlogById(blogId);
    }

    @Override
    public void publishBlogById(String blogId) throws Exception {
        blogMapper.publishBlogById(blogId);
    }

    @Override
    public void deleteBlog(String blogId) throws Exception {
        blogMapper.deleteBlogById(blogId);
    }

}
