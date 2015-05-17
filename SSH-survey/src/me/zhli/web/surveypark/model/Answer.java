package me.zhli.web.surveypark.model;

import java.util.Date;

/**
 * Answer
 * 设置的是没有外键，用 questionId 充当
 */
public class Answer extends BaseEntity {
	
	private static final long serialVersionUID = -5287384609420677067L;
	
	private Integer id;
	private String answerIds;	// 选项的 index
	private String otherAnswer;
	private String uuid;		// 批次
	private Date answearTime;
	
	private Integer questionId;	// 关联字段（冗余）
	private Integer surveyId; 	// 关联字段（冗余）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(String answerIds) {
		this.answerIds = answerIds;
	}
	public String getOtherAnswer() {
		return otherAnswer;
	}
	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getAnswearTime() {
		return answearTime;
	}
	public void setAnswearTime(Date answearTime) {
		this.answearTime = answearTime;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

}
