package com.bmjline.blogserver.entity;

import com.bmjline.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author bmj
 */
public class BlogShortViewEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1957961686928260629L;

    private String blogName;
    private String blogDescr;
    private List<String> blogTag;
    private String publishedTime;
    private String contentType;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogDescr() {
        return blogDescr;
    }

    public void setBlogDescr(String blogDescr) {
        this.blogDescr = blogDescr;
    }

    public List<String> getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(List<String> blogTag) {
        this.blogTag = blogTag;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
