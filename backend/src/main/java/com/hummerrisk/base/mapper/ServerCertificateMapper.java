package com.hummerrisk.base.mapper;

import com.hummerrisk.base.domain.ServerCertificate;
import com.hummerrisk.base.domain.ServerCertificateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServerCertificateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    long countByExample(ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int deleteByExample(ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int insert(ServerCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int insertSelective(ServerCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    List<ServerCertificate> selectByExampleWithBLOBs(ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    List<ServerCertificate> selectByExample(ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    ServerCertificate selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByExampleSelective(@Param("record") ServerCertificate record, @Param("example") ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") ServerCertificate record, @Param("example") ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByExample(@Param("record") ServerCertificate record, @Param("example") ServerCertificateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByPrimaryKeySelective(ServerCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(ServerCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_certificate
     *
     * @mbg.generated Wed Sep 21 04:56:25 CST 2022
     */
    int updateByPrimaryKey(ServerCertificate record);
}