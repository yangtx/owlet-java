package com.xracoon.sys.owlet.orm.models;

import java.util.Date;

public class Article {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.uid
     *
     * @mbggenerated
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.is_invalid
     *
     * @mbggenerated
     */
    private Boolean isInvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.abstracts
     *
     * @mbggenerated
     */
    private String abstracts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.source
     *
     * @mbggenerated
     */
    private Integer source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.is_elite
     *
     * @mbggenerated
     */
    private Boolean isElite;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.is_top
     *
     * @mbggenerated
     */
    private Boolean isTop;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.view_num
     *
     * @mbggenerated
     */
    private Integer viewNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.comment_num
     *
     * @mbggenerated
     */
    private Integer commentNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.id
     *
     * @return the value of article.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.id
     *
     * @param id the value for article.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.type
     *
     * @return the value of article.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.type
     *
     * @param type the value for article.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.title
     *
     * @return the value of article.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.title
     *
     * @param title the value for article.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.uid
     *
     * @return the value of article.uid
     *
     * @mbggenerated
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.uid
     *
     * @param uid the value for article.uid
     *
     * @mbggenerated
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.create_time
     *
     * @return the value of article.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.create_time
     *
     * @param createTime the value for article.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.modify_time
     *
     * @return the value of article.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.modify_time
     *
     * @param modifyTime the value for article.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.status
     *
     * @return the value of article.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.status
     *
     * @param status the value for article.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.is_invalid
     *
     * @return the value of article.is_invalid
     *
     * @mbggenerated
     */
    public Boolean getIsInvalid() {
        return isInvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.is_invalid
     *
     * @param isInvalid the value for article.is_invalid
     *
     * @mbggenerated
     */
    public void setIsInvalid(Boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.abstracts
     *
     * @return the value of article.abstracts
     *
     * @mbggenerated
     */
    public String getAbstracts() {
        return abstracts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.abstracts
     *
     * @param abstracts the value for article.abstracts
     *
     * @mbggenerated
     */
    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts == null ? null : abstracts.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.source
     *
     * @return the value of article.source
     *
     * @mbggenerated
     */
    public Integer getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.source
     *
     * @param source the value for article.source
     *
     * @mbggenerated
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.is_elite
     *
     * @return the value of article.is_elite
     *
     * @mbggenerated
     */
    public Boolean getIsElite() {
        return isElite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.is_elite
     *
     * @param isElite the value for article.is_elite
     *
     * @mbggenerated
     */
    public void setIsElite(Boolean isElite) {
        this.isElite = isElite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.is_top
     *
     * @return the value of article.is_top
     *
     * @mbggenerated
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.is_top
     *
     * @param isTop the value for article.is_top
     *
     * @mbggenerated
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.view_num
     *
     * @return the value of article.view_num
     *
     * @mbggenerated
     */
    public Integer getViewNum() {
        return viewNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.view_num
     *
     * @param viewNum the value for article.view_num
     *
     * @mbggenerated
     */
    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.comment_num
     *
     * @return the value of article.comment_num
     *
     * @mbggenerated
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.comment_num
     *
     * @param commentNum the value for article.comment_num
     *
     * @mbggenerated
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.content
     *
     * @return the value of article.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.content
     *
     * @param content the value for article.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}