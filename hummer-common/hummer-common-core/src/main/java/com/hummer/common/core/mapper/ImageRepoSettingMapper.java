package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.ImageRepoSetting;
import com.hummer.common.core.domain.ImageRepoSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageRepoSettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    long countByExample(ImageRepoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int deleteByExample(ImageRepoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int insert(ImageRepoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int insertSelective(ImageRepoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    List<ImageRepoSetting> selectByExample(ImageRepoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    ImageRepoSetting selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int updateByExampleSelective(@Param("record") ImageRepoSetting record, @Param("example") ImageRepoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int updateByExample(@Param("record") ImageRepoSetting record, @Param("example") ImageRepoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int updateByPrimaryKeySelective(ImageRepoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table image_repo_setting
     *
     * @mbg.generated Tue Jan 10 19:09:09 CST 2023
     */
    int updateByPrimaryKey(ImageRepoSetting record);
}
