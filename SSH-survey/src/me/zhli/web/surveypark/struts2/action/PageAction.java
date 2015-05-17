package me.zhli.web.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.service.SurveyService;

/**
 * PageAciton
 */
@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	private static final long serialVersionUID = 2895566799611914080L;
	// 注入 SurveyService
	@Resource
	private SurveyService surveyService;
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 到达添加 page 的页面
	 */
	public String toAddPage() {
		return "addPagePage";
	}
	
	/**
	 * 保存或更新页面
	 */
	public String saveOrUpdatePage() {
		// model + sid
		// 维护关联关系
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.saveOrUpdate(model);
		return "designSurveyAction";
	}
	
	private Integer pid;
	
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * 编辑页面
	 */
	public String editPage() {
		// sid + pid
		this.model = surveyService.getPage(pid);
		return "editPagePage";
	}
	
	/**
	 * delete Page
	 */
	public String deletePage() {
		// sid + pid
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
}
