package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.HistoryCloudTaskItem;
import com.hummer.common.core.domain.HistoryCloudTaskItemExample;
import com.hummer.common.core.domain.HistoryCloudTaskItemWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryCloudTaskItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    long countByExample(HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int deleteByExample(HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int insert(HistoryCloudTaskItemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int insertSelective(HistoryCloudTaskItemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    List<HistoryCloudTaskItemWithBLOBs> selectByExampleWithBLOBs(HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    List<HistoryCloudTaskItem> selectByExample(HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    HistoryCloudTaskItemWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByExampleSelective(@Param("record") HistoryCloudTaskItemWithBLOBs record, @Param("example") HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") HistoryCloudTaskItemWithBLOBs record, @Param("example") HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByExample(@Param("record") HistoryCloudTaskItem record, @Param("example") HistoryCloudTaskItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByPrimaryKeySelective(HistoryCloudTaskItemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(HistoryCloudTaskItemWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task_item
     *
     * @mbg.generated Thu Jul 21 18:34:03 CST 2022
     */
    int updateByPrimaryKey(HistoryCloudTaskItem record);
}
