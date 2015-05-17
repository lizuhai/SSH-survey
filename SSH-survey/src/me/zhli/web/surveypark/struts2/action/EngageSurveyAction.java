package me.zhli.web.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.datasource.SurveyToken;
import me.zhli.web.surveypark.model.Answer;
import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.service.SurveyService;
import me.zhli.web.surveypark.util.StringUtil;
import me.zhli.web.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware, SessionAware, ParameterAware {

	private static final long serialVersionUID = 1228597728948089726L;
	// 当前调查的 key
	private static final String CURRENT_SURVEY = "current_survey";
	// 所有参数的 map
	private static final String ALL_PARAMS_MAP = "all_params_map";
	
	@Resource
	private SurveyService surveyService;
	private List<Survey> surveys;

	// 接收 servletContext
	private ServletContext sc;

	private Page currPage;

	private Integer sid;
	
	// 当前页 id
	private Integer currPid;
	
	public Integer getCurrPid() {
		return currPid;
	}
	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}
	public Page getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public String findAllAvailableSurveys() {
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	/**
	 * 获得 Url 的地址
	 */
	public String getImageUrl(String path) {
		if(ValidateUtil.isValidate(path)) {
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			if(file.exists()) {
				// SuveyPark/upload/xxx.jpg
				return sc.getContextPath() + path;
			}
		}
		// 返回默认图片
		return sc.getContextPath() + "/question.jpg";
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		sc = arg0;
	}
	
	public String entry() {
		// sid
		// 查询调查首页
		this.currPage = surveyService.getFirstPage(sid);
		// 查询当前调查存放到 session 中，以免每次取调查标题
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		
		// 将调查的存放所有参数的map存放到 session 中 ，用于保存答案和回显
		sessionMap.put(ALL_PARAMS_MAP, new HashMap<Integer, Map<String, String[]>>());
		
		return "engageSurveyPage";
	}
	
	/**
	 * 处理参与调查
	 */
	public String doEngageSurvey() {
		String submitName = getSubmitName();
//		System.out.println(submitName);
		if(submitName.endsWith("pre")) {			// 上一步
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		} else if(submitName.endsWith("next")) {	// 下一步
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		} else if(submitName.endsWith("done")) { 	// 完成
			mergeParamsIntoSession();
			
			// 绑定 token 到当前线程
			SurveyToken token = new SurveyToken();
			token.setSurvey(getCurrentSurvey());
			SurveyToken.bindToken(token);	// 绑定令牌

			// 答案入库
			surveyService.saveAnswers(processAnswers());
			
			clearSessionData();
			return "engageSurveyAction";
		}else if(submitName.endsWith("exit")) {		// 退出
			clearSessionData();
			return "engageSurveyAction";
		}
		return null;
	}
	
	/**
	 * 处理答案
	 */
	private List<Answer> processAnswers() {
		String key = null;
		String [] value = null;
		// 所有答案的集合
		List<Answer> answers = new ArrayList<>();
		Answer a = null;
		// 矩阵式单选
		Map<Integer, String> matrixRadioMap = new HashMap<>();
		// Map<页面 id, Map<问题名, 问题答案数组>>
		Map<Integer, Map<String, String []>> allMap = getAllParamsMap();
		for (Map<String, String []> map : allMap.values()) {
			for (Entry<String, String []> m : map.entrySet()) {
				key = m.getKey();
				value = m.getValue();
				// 处理所有以 q 开头的参数
				if(key.startsWith("q")) {
					// 常规参数
					if(!key.contains("other") && !key.contains("_")) {
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));	// answerids
						a.setQuestionId(getQid(key)); 				// qid
						a.setSurveyId(getCurrentSurvey().getId()); 	// sid
						// 其他项
						a.setOtherAnswer(StringUtil.arr2Str(map.get(key + "other")));	// otherAnswer
						answers.add(a);
					} else if(key.contains("_")) {	// 矩阵式单选问题答案的处理
						Integer radioQid = getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue == null) {
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						} else {
							matrixRadioMap.put(radioQid, oldValue + "," +StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		// 单独处理矩阵式单选按钮
		processMatrixRadioMap(matrixRadioMap, answers);
		return answers;
	}
	
	/**
	 * 单独处理矩阵式单选按钮
	 */
	private void processMatrixRadioMap(Map<Integer, String> matrixRadioMap,
			List<Answer> answers) {
		Integer key = null;
		String value = null;
		Answer answer = new Answer();
		for(Entry<Integer, String> entry : matrixRadioMap.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			answer.setAnswerIds(value);
			answer.setQuestionId(key);
			answer.setSurveyId(getCurrentSurvey().getId());
			answers.add(answer);
		}
	}
	/**
	 * 获取矩阵式问题id：q12_0 --> 12
	 */
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}
	/**
	 * 获取当前调查对象
	 */
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
	}
	/**
	 * 获取问题 id : q12 --> 12
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}
	/**
	 * clear session
	 */
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}
	/**
	 * 合并参数到 session 中
	 */
	private void mergeParamsIntoSession() {
		// paramsMap
		Map<Integer, Map<String, String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}
	
	/**
	 * 获取session中存放所有参数的Map
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}
	// 获得提交按钮名称
	private String getSubmitName() {
		for (String key : paramsMap.keySet()) {
			if(key.startsWith("submit_")) {
				return key;
			}
		}
		return null;
	}

	private Map<String, Object> sessionMap ;
	// 接收所有参数的 map
	private Map<String, String[]> paramsMap;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	//注入提交的所有参数的map
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.paramsMap = arg0;
	}
	
	/**
	 * 设置标记，用于答案回显(主要用于 radio|checkbox|select)
	 */
	public String setTag(String name, String value, String selTag) {
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String [] values = map.get(name);
		if(StringUtil.contais(values, value)) {
			return selTag;
		}
		return "";
	}
	/**
	 * 设置标记，用于答案回显(文本框的回显)
	 */
	public String setText(String name) {
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String [] values = map.get(name);
		return "value='" + values[0] + "'";
	}
	
}
 