package com.bmjline.blogserver.controller;

import com.bmjline.blogserver.entity.BlogEntity;
import com.bmjline.blogserver.service.BlogService;
import com.bmjline.common.api.CommonResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author bmj
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/query/all")
    public CommonResult<List<Map<String, Object>>> queryAllBlog(@RequestBody Map<String, Object> req) {
        try {
            return CommonResult.success(blogService.queryAllBlog(req));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to query blog");
        }
    }

    @PostMapping("/query/conditioned")
    public CommonResult<List<Map<String, Object>>> queryBlog(@RequestBody Map<String, Object> req) {
        try {
            return CommonResult.success(blogService.queryConditionedBlog(req));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to query blog");
        }
    }

    @GetMapping("/detail/{blogId}")
    public CommonResult<BlogEntity> detailBlog(@PathVariable String blogId) {
        try {
            return CommonResult.success(blogService.blogDetail(blogId));
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to get blog detail");
        }
    }

    @PostMapping("/new")
    public CommonResult<String> newBlog(@RequestBody Map<String, Object> req) {
        try {
            blogService.newBlog(req);
            return CommonResult.success("success to save blog");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to save blog");
        }
    }

    @PostMapping("/update")
    public CommonResult<String> updateBlog(@RequestBody Map<String, Object> req) {
        try {
            blogService.updateBlog(req);
            return CommonResult.success("success to save blog");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to save blog");
        }
    }

    @PostMapping("/save-publish")
    public CommonResult<String> saveAndPublishBlog(@RequestBody Map<String, Object> req) {
        try {
            blogService.saveAndPublishBlog(req);
            return CommonResult.success("success to save and publish blog");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to save and publish blog");
        }
    }

    @PostMapping("/publish/{blogId}")
    public CommonResult<String> publishBlog(@PathVariable String blogId) {
        try {
            blogService.publishBlogById(blogId);
            return CommonResult.success("success to publish blog");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to publish blog");
        }
    }

    @DeleteMapping("/delete/{blogId}")
    public CommonResult<String> deleteBlog(@PathVariable String blogId) {
        try {
            blogService.deleteBlog(blogId);
            return CommonResult.success("success to delete blog");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("failed to delete blog");
        }
    }
}
