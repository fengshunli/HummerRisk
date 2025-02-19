package com.hummerrisk.base.mapper;

import com.hummerrisk.base.domain.ImageResult;
import com.hummerrisk.base.domain.ImageResultExample;
import com.hummerrisk.base.domain.ImageResultWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    long countByExample(ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int deleteByExample(ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int insert(ImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int insertSelective(ImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    List<ImageResultWithBLOBs> selectByExampleWithBLOBs(ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    List<ImageResult> selectByExample(ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    ImageResultWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByExampleSelective(@Param("record") ImageResultWithBLOBs record, @Param("example") ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") ImageResultWithBLOBs record, @Param("example") ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByExample(@Param("record") ImageResult record, @Param("example") ImageResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByPrimaryKeySelective(ImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(ImageResultWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_result
     *
     * @mbg.generated Wed Nov 02 11:13:50 CST 2022
     */
    int updateByPrimaryKey(ImageResult record);
}
