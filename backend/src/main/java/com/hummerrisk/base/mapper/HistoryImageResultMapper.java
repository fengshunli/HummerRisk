package com.hummerrisk.base.mapper;

import com.hummerrisk.base.domain.HistoryImageResult;
import com.hummerrisk.base.domain.HistoryImageResultExample;
import com.hummerrisk.base.domain.HistoryImageResultWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryImageResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    long countByExample(HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int deleteByExample(HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int insert(HistoryImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int insertSelective(HistoryImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    List<HistoryImageResultWithBLOBs> selectByExampleWithBLOBs(HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    List<HistoryImageResult> selectByExample(HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    HistoryImageResultWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByExampleSelective(@Param("record") HistoryImageResultWithBLOBs record, @Param("example") HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") HistoryImageResultWithBLOBs record, @Param("example") HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByExample(@Param("record") HistoryImageResult record, @Param("example") HistoryImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByPrimaryKeySelective(HistoryImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(HistoryImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table history_image_result
     *
     * @mbg.generated Wed Oct 12 06:18:21 CST 2022
     */
    int updateByPrimaryKey(HistoryImageResult record);
}
