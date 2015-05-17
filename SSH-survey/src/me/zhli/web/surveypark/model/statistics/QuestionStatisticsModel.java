package me.zhli.web.surveypark.model.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.zhli.web.surveypark.model.Question;

public class QuestionStatisticsModel implements Serializable {

	private static final long serialVersionUID = -5185835560063506912L;
	
	private Question question;
	private int count;
	// 选项统计模型的集合
	private List<OptionStatisticsModel> osm = new ArrayList<>();
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<OptionStatisticsModel> getOsm() {
		return osm;
	}
	public void setOsm(List<OptionStatisticsModel> osm) {
		this.osm = osm;
	}
	
}
