package com.hummerrisk.base.domain;

import java.io.Serializable;

public class CloudNativeConfigResultItemWithBLOBs extends CloudNativeConfigResultItem implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_native_config_result_item.description
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_native_config_result_item.references
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    private String references;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_native_config_result_item.layer
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    private String layer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cloud_native_config_result_item.causemeta_data
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    private String causemetaData;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cloud_native_config_result_item
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_native_config_result_item.description
     *
     * @return the value of cloud_native_config_result_item.description
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_native_config_result_item.description
     *
     * @param description the value for cloud_native_config_result_item.description
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_native_config_result_item.references
     *
     * @return the value of cloud_native_config_result_item.references
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public String getReferences() {
        return references;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_native_config_result_item.references
     *
     * @param references the value for cloud_native_config_result_item.references
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public void setReferences(String references) {
        this.references = references == null ? null : references.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_native_config_result_item.layer
     *
     * @return the value of cloud_native_config_result_item.layer
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public String getLayer() {
        return layer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_native_config_result_item.layer
     *
     * @param layer the value for cloud_native_config_result_item.layer
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public void setLayer(String layer) {
        this.layer = layer == null ? null : layer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cloud_native_config_result_item.causemeta_data
     *
     * @return the value of cloud_native_config_result_item.causemeta_data
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public String getCausemetaData() {
        return causemetaData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cloud_native_config_result_item.causemeta_data
     *
     * @param causemetaData the value for cloud_native_config_result_item.causemeta_data
     *
     * @mbg.generated Sat Sep 03 02:23:00 CST 2022
     */
    public void setCausemetaData(String causemetaData) {
        this.causemetaData = causemetaData == null ? null : causemetaData.trim();
    }
}