package com.hummerrisk.base.domain;

import java.io.Serializable;

public class FileSystemResult implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.fs_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String fsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.plugin_icon
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String pluginIcon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.rule_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String ruleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.rule_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String ruleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.rule_desc
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String ruleDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.result_status
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String resultStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.severity
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String severity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.create_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.update_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private Long updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.apply_user
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String applyUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.user_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.return_sum
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private Long returnSum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.sbom_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String sbomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.sbom_version_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String sbomVersionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.command
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String command;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_system_result.return_json
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private String returnJson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.id
     *
     * @return the value of file_system_result.id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.id
     *
     * @param id the value for file_system_result.id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.fs_id
     *
     * @return the value of file_system_result.fs_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getFsId() {
        return fsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.fs_id
     *
     * @param fsId the value for file_system_result.fs_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setFsId(String fsId) {
        this.fsId = fsId == null ? null : fsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.name
     *
     * @return the value of file_system_result.name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.name
     *
     * @param name the value for file_system_result.name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.plugin_icon
     *
     * @return the value of file_system_result.plugin_icon
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getPluginIcon() {
        return pluginIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.plugin_icon
     *
     * @param pluginIcon the value for file_system_result.plugin_icon
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setPluginIcon(String pluginIcon) {
        this.pluginIcon = pluginIcon == null ? null : pluginIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.rule_id
     *
     * @return the value of file_system_result.rule_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.rule_id
     *
     * @param ruleId the value for file_system_result.rule_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.rule_name
     *
     * @return the value of file_system_result.rule_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.rule_name
     *
     * @param ruleName the value for file_system_result.rule_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.rule_desc
     *
     * @return the value of file_system_result.rule_desc
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getRuleDesc() {
        return ruleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.rule_desc
     *
     * @param ruleDesc the value for file_system_result.rule_desc
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.result_status
     *
     * @return the value of file_system_result.result_status
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.result_status
     *
     * @param resultStatus the value for file_system_result.result_status
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus == null ? null : resultStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.severity
     *
     * @return the value of file_system_result.severity
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.severity
     *
     * @param severity the value for file_system_result.severity
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setSeverity(String severity) {
        this.severity = severity == null ? null : severity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.create_time
     *
     * @return the value of file_system_result.create_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.create_time
     *
     * @param createTime the value for file_system_result.create_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.update_time
     *
     * @return the value of file_system_result.update_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.update_time
     *
     * @param updateTime the value for file_system_result.update_time
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.apply_user
     *
     * @return the value of file_system_result.apply_user
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getApplyUser() {
        return applyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.apply_user
     *
     * @param applyUser the value for file_system_result.apply_user
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser == null ? null : applyUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.user_name
     *
     * @return the value of file_system_result.user_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.user_name
     *
     * @param userName the value for file_system_result.user_name
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.return_sum
     *
     * @return the value of file_system_result.return_sum
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public Long getReturnSum() {
        return returnSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.return_sum
     *
     * @param returnSum the value for file_system_result.return_sum
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setReturnSum(Long returnSum) {
        this.returnSum = returnSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.sbom_id
     *
     * @return the value of file_system_result.sbom_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getSbomId() {
        return sbomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.sbom_id
     *
     * @param sbomId the value for file_system_result.sbom_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setSbomId(String sbomId) {
        this.sbomId = sbomId == null ? null : sbomId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.sbom_version_id
     *
     * @return the value of file_system_result.sbom_version_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getSbomVersionId() {
        return sbomVersionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.sbom_version_id
     *
     * @param sbomVersionId the value for file_system_result.sbom_version_id
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setSbomVersionId(String sbomVersionId) {
        this.sbomVersionId = sbomVersionId == null ? null : sbomVersionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.command
     *
     * @return the value of file_system_result.command
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getCommand() {
        return command;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.command
     *
     * @param command the value for file_system_result.command
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setCommand(String command) {
        this.command = command == null ? null : command.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_system_result.return_json
     *
     * @return the value of file_system_result.return_json
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public String getReturnJson() {
        return returnJson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_system_result.return_json
     *
     * @param returnJson the value for file_system_result.return_json
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson == null ? null : returnJson.trim();
    }
}