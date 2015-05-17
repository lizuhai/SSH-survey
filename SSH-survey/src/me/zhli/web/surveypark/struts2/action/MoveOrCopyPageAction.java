package me.zhli.web.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.SurveyService;
import me.zhli.web.surveypark.struts2.UserAware;

/**
 * 移动/复制页面 action
 */
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = 64864847511601677L;

	@Resource
	private SurveyService surveyService;
	// 源页面 id
	private Integer srcPid;
	// 目标页面 id
	private Integer targPid;
	// 位置 0-之前 1-之后
	private Integer pos;
	// 目标调查 id
	private Integer sid;

	private List<Survey> mySurveys;

	private User user;
	
	public Integer getTargPid() {
		return targPid;
	}
	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSrcPid() {
		return srcPid;
	}
	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}
	public List<Survey> getMySurveys() {
		return mySurveys;
	}
	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	/**
	 * 到达移动/复制页面
	 */
	public String toSelectTargetPage() {
		// 原页面 id（srcId）
		mySurveys = surveyService.getSurveyWithPages(user);
		return "moveOrCopyPageListPage";
	}
	
	public String doMoveOrCopyPage() {
		// srcPid + targPid + pos + sid
		surveyService.moveOrCopyPage(srcPid, targPid, pos);
		return "designSurveyAction";
	}
	
	// 注入 user
	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
