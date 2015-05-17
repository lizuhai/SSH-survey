package me.zhli.web.surveypark.struts2.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.statistics.OptionStatisticsModel;
import me.zhli.web.surveypark.model.statistics.QuestionStatisticsModel;
import me.zhli.web.surveypark.service.StatisticsService;

/**
 * MatrixStaticsAction
 */
@Controller
@Scope("prototype")
public class MatrixStaticsAction extends BaseAction<Question> {

	private static final long serialVersionUID = -8563706395380639800L;
	private Integer qid;
	private QuestionStatisticsModel qsm;
	@Resource
	private StatisticsService ss;
	// 颜色 rgb
	private String [] colors = {
			"#ff0000",
			"#00ff00",
			"#0000ff",
			"#ffff00",
			"#ff00ff",
			"#00ffff"
	};
	
	public String execute() throws Exception {
		this.qsm = ss.statistics(qid);
		return "" + qsm.getQuestion().getQuestionType();
	}
	
	/**
	 * 计算每个选项统计的结果
	 */
	public String getScale(int rowIndex, int colIndex) {
		// 问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0;
		// 选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsm()) {
			if(osm.getMatrixRowIndx() == rowIndex 
					&& osm.getMatrixColIndx() == colIndex) {
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0f;
		if(qcount != 0)
			scale = (float) ocount / qcount * 100;
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###.00");
		return "" + ocount + "(" + df.format(scale) + "%)";
	}
	
	public String getScale(int rowIndex, int colIndex, int optIndex) {
		// 问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0;
		// 选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsm()) {
			if(osm.getMatrixRowIndx() == rowIndex 
					&& osm.getMatrixColIndx() == colIndex
					&& osm.getMatrixSelectIndx() == optIndex) {
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0f;
		if(qcount != 0)
			scale = (float) ocount / qcount * 100;
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###.00");
		return "" + ocount + "(" + df.format(scale) + "%)";
	}
	
	/**
	 * 获得百分比的整数部分，作为选项的显示长度
	 */
	public int getPercent(int rowIndex, int colIndex, int optIndex) {
		// 问题回答人数
		int qcount = qsm.getCount();
		int ocount = 0;
		// 选项回答人数
		for(OptionStatisticsModel osm : qsm.getOsm()) {
			if(osm.getMatrixRowIndx() == rowIndex 
					&& osm.getMatrixColIndx() == colIndex
					&& osm.getMatrixSelectIndx() == optIndex) {
				ocount = osm.getCount();
				break;
			}
		}
		float scale = 0f;
		if(qcount != 0)
			scale = (float) ocount / qcount * 100;
		return (int) scale;
	}
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public QuestionStatisticsModel getQsm() {
		return qsm;
	}
	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}
	public String[] getColors() {
		return colors;
	}
	public void setColors(String[] colors) {
		this.colors = colors;
	}
}
