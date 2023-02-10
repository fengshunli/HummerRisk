package com.hummer.common.core.mapper;

import com.hummer.common.core.domain.RuleType;
import com.hummer.common.core.domain.RuleTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    long countByExample(RuleTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByExample(RuleTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insert(RuleType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int insertSelective(RuleType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    List<RuleType> selectByExample(RuleTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    RuleType selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") RuleType record, @Param("example") RuleTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByExample(@Param("record") RuleType record, @Param("example") RuleTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKeySelective(RuleType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rule_type
     *
     * @mbg.generated Tue Jan 19 17:40:09 CST 2022
     */
    int updateByPrimaryKey(RuleType record);
}
