package com.xracoon.sys.owlet.orm.models;

public class UserIdentity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_identity.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_identity.user
     *
     * @mbggenerated
     */
    private Integer user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_identity.auth_channel
     *
     * @mbggenerated
     */
    private Integer authChannel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_identity.channel_key
     *
     * @mbggenerated
     */
    private String channelKey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_identity.id
     *
     * @return the value of user_identity.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_identity.id
     *
     * @param id the value for user_identity.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_identity.user
     *
     * @return the value of user_identity.user
     *
     * @mbggenerated
     */
    public Integer getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_identity.user
     *
     * @param user the value for user_identity.user
     *
     * @mbggenerated
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_identity.auth_channel
     *
     * @return the value of user_identity.auth_channel
     *
     * @mbggenerated
     */
    public Integer getAuthChannel() {
        return authChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_identity.auth_channel
     *
     * @param authChannel the value for user_identity.auth_channel
     *
     * @mbggenerated
     */
    public void setAuthChannel(Integer authChannel) {
        this.authChannel = authChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_identity.channel_key
     *
     * @return the value of user_identity.channel_key
     *
     * @mbggenerated
     */
    public String getChannelKey() {
        return channelKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_identity.channel_key
     *
     * @param channelKey the value for user_identity.channel_key
     *
     * @mbggenerated
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey == null ? null : channelKey.trim();
    }
}