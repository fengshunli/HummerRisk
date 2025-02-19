package com.hummerrisk.base.domain;

import java.io.Serializable;

public class ImageRepoSyncLogWithBLOBs extends ImageRepoSyncLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_repo_sync_log.operation
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    private String operation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column image_repo_sync_log.output
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    private String output;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table image_repo_sync_log
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_repo_sync_log.operation
     *
     * @return the value of image_repo_sync_log.operation
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_repo_sync_log.operation
     *
     * @param operation the value for image_repo_sync_log.operation
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column image_repo_sync_log.output
     *
     * @return the value of image_repo_sync_log.output
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    public String getOutput() {
        return output;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column image_repo_sync_log.output
     *
     * @param output the value for image_repo_sync_log.output
     *
     * @mbg.generated Sun Jan 22 22:55:01 CST 2023
     */
    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }
}