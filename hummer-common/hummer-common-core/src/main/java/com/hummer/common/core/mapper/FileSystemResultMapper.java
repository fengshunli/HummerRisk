package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.FileSystemResult;
import com.hummer.common.core.domain.FileSystemResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileSystemResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    long countByExample(FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int deleteByExample(FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int insert(FileSystemResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int insertSelective(FileSystemResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    List<FileSystemResult> selectByExampleWithBLOBs(FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    List<FileSystemResult> selectByExample(FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    FileSystemResult selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByExampleSelective(@Param("record") FileSystemResult record, @Param("example") FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") FileSystemResult record, @Param("example") FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByExample(@Param("record") FileSystemResult record, @Param("example") FileSystemResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByPrimaryKeySelective(FileSystemResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(FileSystemResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_system_result
     *
     * @mbg.generated Wed Nov 02 11:13:51 CST 2022
     */
    int updateByPrimaryKey(FileSystemResult record);
}
