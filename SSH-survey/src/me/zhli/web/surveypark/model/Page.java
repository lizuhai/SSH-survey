package me.zhli.web.surveypark.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Page Class
 */
public class Page extends BaseEntity {

	private static final long serialVersionUID = 6581720340029118619L;
	private Integer id;
	private String title = "未命名";
	private String description;
	
	// 从 Page 到 Survey: n -> 1
	// 不进行串行化
	private transient Survey survey;
	
	// 从 Page 到 Question: 1-> n
	private Set<Question> questions = new HashSet<>();
	
	// 页序(默认和 id 一致)
	private float orderno;
	
	public float getOrderno() {
		return orderno;
	}
	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		if(id != null) {
			this.orderno = id;
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
