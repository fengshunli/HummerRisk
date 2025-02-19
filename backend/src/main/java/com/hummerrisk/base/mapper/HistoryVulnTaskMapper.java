package com.hummerrisk.base.mapper;

import com.hummerrisk.base.domain.HistoryVulnTask;
import com.hummerrisk.base.domain.HistoryVulnTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryVulnTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    long countByExample(HistoryVulnTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int deleteByExample(HistoryVulnTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int insert(HistoryVulnTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int insertSelective(HistoryVulnTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    List<HistoryVulnTask> selectByExample(HistoryVulnTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    HistoryVulnTask selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByExampleSelective(@Param("record") HistoryVulnTask record, @Param("example") HistoryVulnTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByExample(@Param("record") HistoryVulnTask record, @Param("example") HistoryVulnTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByPrimaryKeySelective(HistoryVulnTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_vuln_task
     *
     * @mbg.generated Thu Jul 21 18:38:41 CST 2022
     */
    int updateByPrimaryKey(HistoryVulnTask record);
}
