package com.bmjline.blogserver.entity;

import com.bmjline.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author bmj
 */
public class BlogEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4267191147545058720L;

    private String blogName;
    private String blogDescr;
    private List<String> blogTag;
    private String blogContent;
    private int blogLength;
    private String readingTime;
    private String publishedTime;

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

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public int getBlogLength() {
        return blogLength;
    }

    public void setBlogLength(int blogLength) {
        this.blogLength = blogLength;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }
}
