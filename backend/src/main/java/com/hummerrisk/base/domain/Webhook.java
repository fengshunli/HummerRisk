package com.hummerrisk.base.domain;

import java.io.Serializable;

public class Webhook implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.name
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.webhook
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String webhook;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.status
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.create_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.update_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private Long updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.creator
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook.proxy_id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private Integer proxyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table webhook
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.id
     *
     * @return the value of webhook.id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.id
     *
     * @param id the value for webhook.id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.name
     *
     * @return the value of webhook.name
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.name
     *
     * @param name the value for webhook.name
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.webhook
     *
     * @return the value of webhook.webhook
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getWebhook() {
        return webhook;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.webhook
     *
     * @param webhook the value for webhook.webhook
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setWebhook(String webhook) {
        this.webhook = webhook == null ? null : webhook.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.status
     *
     * @return the value of webhook.status
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.status
     *
     * @param status the value for webhook.status
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.create_time
     *
     * @return the value of webhook.create_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.create_time
     *
     * @param createTime the value for webhook.create_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.update_time
     *
     * @return the value of webhook.update_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.update_time
     *
     * @param updateTime the value for webhook.update_time
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.creator
     *
     * @return the value of webhook.creator
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.creator
     *
     * @param creator the value for webhook.creator
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook.proxy_id
     *
     * @return the value of webhook.proxy_id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public Integer getProxyId() {
        return proxyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook.proxy_id
     *
     * @param proxyId the value for webhook.proxy_id
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setProxyId(Integer proxyId) {
        this.proxyId = proxyId;
    }
}