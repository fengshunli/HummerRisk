package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.RuleGroup;
import com.hummer.common.core.domain.RuleGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    long countByExample(RuleGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByExample(RuleGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insert(RuleGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insertSelective(RuleGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    List<RuleGroup> selectByExample(RuleGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    RuleGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExample(@Param("record") RuleGroup record, @Param("example") RuleGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKeySelective(RuleGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_group
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKey(RuleGroup record);
}
