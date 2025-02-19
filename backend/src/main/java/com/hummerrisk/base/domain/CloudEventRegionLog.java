package com.hummerrisk.base.domain;

import java.io.Serializable;
import java.util.Date;

public class CloudEventRegionLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.log_id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Integer logId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.region
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private String region;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.data_count
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Integer dataCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.status
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.start_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.end_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.create_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.exception
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private String exception;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_event_region_log.region_name
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private String regionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cloud_event_region_log
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.id
     *
     * @return the value of cloud_event_region_log.id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.id
     *
     * @param id the value for cloud_event_region_log.id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.log_id
     *
     * @return the value of cloud_event_region_log.log_id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.log_id
     *
     * @param logId the value for cloud_event_region_log.log_id
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.region
     *
     * @return the value of cloud_event_region_log.region
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.region
     *
     * @param region the value for cloud_event_region_log.region
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.data_count
     *
     * @return the value of cloud_event_region_log.data_count
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Integer getDataCount() {
        return dataCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.data_count
     *
     * @param dataCount the value for cloud_event_region_log.data_count
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.status
     *
     * @return the value of cloud_event_region_log.status
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.status
     *
     * @param status the value for cloud_event_region_log.status
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.start_time
     *
     * @return the value of cloud_event_region_log.start_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.start_time
     *
     * @param startTime the value for cloud_event_region_log.start_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.end_time
     *
     * @return the value of cloud_event_region_log.end_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.end_time
     *
     * @param endTime the value for cloud_event_region_log.end_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.create_time
     *
     * @return the value of cloud_event_region_log.create_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.create_time
     *
     * @param createTime the value for cloud_event_region_log.create_time
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.exception
     *
     * @return the value of cloud_event_region_log.exception
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public String getException() {
        return exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.exception
     *
     * @param exception the value for cloud_event_region_log.exception
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_event_region_log.region_name
     *
     * @return the value of cloud_event_region_log.region_name
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_event_region_log.region_name
     *
     * @param regionName the value for cloud_event_region_log.region_name
     *
     * @mbg.generated Thu Oct 20 07:33:40 CST 2022
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }
}