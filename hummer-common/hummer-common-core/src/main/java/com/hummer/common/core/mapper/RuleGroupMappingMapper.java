package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.RuleGroupMapping;
import com.hummer.common.core.domain.RuleGroupMappingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleGroupMappingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    long countByExample(RuleGroupMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByExample(RuleGroupMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insert(RuleGroupMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insertSelective(RuleGroupMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    List<RuleGroupMapping> selectByExample(RuleGroupMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    RuleGroupMapping selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") RuleGroupMapping record, @Param("example") RuleGroupMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExample(@Param("record") RuleGroupMapping record, @Param("example") RuleGroupMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKeySelective(RuleGroupMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group_mapping
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKey(RuleGroupMapping record);
}
