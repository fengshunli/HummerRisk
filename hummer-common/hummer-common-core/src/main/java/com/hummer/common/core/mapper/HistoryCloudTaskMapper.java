package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.HistoryCloudTask;
import com.hummer.common.core.domain.HistoryCloudTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryCloudTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    long countByExample(HistoryCloudTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int deleteByExample(HistoryCloudTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int insert(HistoryCloudTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int insertSelective(HistoryCloudTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    List<HistoryCloudTask> selectByExample(HistoryCloudTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    HistoryCloudTask selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByExampleSelective(@Param("record") HistoryCloudTask record, @Param("example") HistoryCloudTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByExample(@Param("record") HistoryCloudTask record, @Param("example") HistoryCloudTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByPrimaryKeySelective(HistoryCloudTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_cloud_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByPrimaryKey(HistoryCloudTask record);
}
