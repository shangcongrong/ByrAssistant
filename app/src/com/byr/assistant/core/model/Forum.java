package com.byr.assistant.core.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 上午2:49
 * To change this template use File | Settings | File Templates.
 */
public class Forum implements Serializable {

    private String forumId;

    private String title;

    private String articleUrl;

    private String author;

    private String content;

    private String publishDate;

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
