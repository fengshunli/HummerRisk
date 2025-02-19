package com.hummerrisk.base.domain;

import java.io.Serializable;

public class MessageTaskWithBLOBs extends MessageTask implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message_task.template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String template;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message_task.text_template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private String textTemplate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table message_task
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message_task.template
     *
     * @return the value of message_task.template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getTemplate() {
        return template;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message_task.template
     *
     * @param template the value for message_task.template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message_task.text_template
     *
     * @return the value of message_task.text_template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public String getTextTemplate() {
        return textTemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message_task.text_template
     *
     * @param textTemplate the value for message_task.text_template
     *
     * @mbg.generated Mon Dec 12 21:03:52 CST 2022
     */
    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate == null ? null : textTemplate.trim();
    }
}