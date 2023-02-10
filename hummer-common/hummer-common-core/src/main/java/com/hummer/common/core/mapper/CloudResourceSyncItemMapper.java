package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.CloudResourceSyncItem;
import com.hummer.common.core.domain.CloudResourceSyncItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CloudResourceSyncItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    long countByExample(CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int deleteByExample(CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int insert(CloudResourceSyncItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int insertSelective(CloudResourceSyncItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    List<CloudResourceSyncItem> selectByExampleWithBLOBs(CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    List<CloudResourceSyncItem> selectByExample(CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    CloudResourceSyncItem selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") CloudResourceSyncItem record, @Param("example") CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") CloudResourceSyncItem record, @Param("example") CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByExample(@Param("record") CloudResourceSyncItem record, @Param("example") CloudResourceSyncItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByPrimaryKeySelective(CloudResourceSyncItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(CloudResourceSyncItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_resource_sync_item
     *
     * @mbg.generated Sun Oct 02 23:25:09 CST 2022
     */
    int updateByPrimaryKey(CloudResourceSyncItem record);
}
