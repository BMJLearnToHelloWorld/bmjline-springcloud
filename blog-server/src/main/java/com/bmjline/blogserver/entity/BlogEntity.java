package com.bmjline.blogserver.entity;

import java.io.Serializable;

/**
 * @author bmj
 */
public class BlogEntity extends BlogShortViewEntity implements Serializable {

    private static final long serialVersionUID = 4267191147545058720L;

    private String blogContent;
    private int blogLength;
    private String readingTime;

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

}
