package me.zhli.web.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.service.SurveyService;

/**
 * 
 *
 */
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 1041253956643167206L;
	private Integer sid;
	private Integer pid;
	
	@Resource
	private SurveyService surveyService;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String toSelectQuestionType() {
		// sid + pid
		return "selectQuestionTypePage";
	}
	
	public String toDesignQuestionPage() {
		// sid + cid + model
		return "" + model.getQuestionType();
	}
	
	public String saveOrUpdateQuestion() {
		Page p = new Page();
		p.setId(pid);
		// sid + pid + model
		// 维护关联关系
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	
	private Integer qid;
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}

	/**
	 * 删除问题同时删除答案
	 */
	public String deleteQuestion() {
		// sid + qid
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	/**
	 * 编辑问题
	 */
	public String editQuestion() {
		// sid + qid 
		this.model = surveyService.getQuestion(qid);
		return "" + model.getQuestionType();
	}
}
