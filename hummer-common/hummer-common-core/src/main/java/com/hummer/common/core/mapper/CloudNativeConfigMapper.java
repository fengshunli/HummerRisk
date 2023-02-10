package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.CloudNativeConfig;
import com.hummer.common.core.domain.CloudNativeConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CloudNativeConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    long countByExample(CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int deleteByExample(CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int insert(CloudNativeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int insertSelective(CloudNativeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    List<CloudNativeConfig> selectByExampleWithBLOBs(CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    List<CloudNativeConfig> selectByExample(CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    CloudNativeConfig selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByExampleSelective(@Param("record") CloudNativeConfig record, @Param("example") CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") CloudNativeConfig record, @Param("example") CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByExample(@Param("record") CloudNativeConfig record, @Param("example") CloudNativeConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByPrimaryKeySelective(CloudNativeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(CloudNativeConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cloud_native_config
     *
     * @mbg.generated Sat Aug 20 02:02:31 CST 2022
     */
    int updateByPrimaryKey(CloudNativeConfig record);
}
