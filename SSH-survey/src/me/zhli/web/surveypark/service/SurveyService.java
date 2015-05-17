package me.zhli.web.surveypark.service;

import java.util.List;

import me.zhli.web.surveypark.model.Answer;
import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.model.User;

public interface SurveyService {

	/**
	 * 查询调查列表
	 * @param user
	 * @return
	 */
	public List<Survey> findMySurveys(User user);

	/**
	 * 新建调查
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * 安装 id 查询 Survey 对象
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);

	public Survey getSurveyWithChildren(Integer sid);

	public void updateSurvey(Survey model);

	public void saveOrUpdate(Page model);

	public Page getPage(Integer pid);

	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除 question
	 * 1. 删除答案
	 * 2. 删除问题
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 删除pa'ge
	 * 1. 删除答案
	 * 2. 删除问题
	 * 3. 删除页面
	 */
	public void deletePage(Integer pid);
	
	/**
	 * 删除 survey
	 * 1. 删除答案
	 * 2. 删除问题
	 * 3. 删除页面
	 * 4. 删除调查
	 */
	public void deleteSurvey(Integer sid);

	public Question getQuestion(Integer qid);

	public void clearAnswer(Integer sid);

	public void toggleStatus(Integer sid);

	public void updateLogoPhotoPath(Integer sid, String string);

	public List<Survey> getSurveyWithPages(User user);

	public void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos);

	public List<Survey> findAllAvailableSurveys();

	public Page getFirstPage(Integer sid);

	public Page getNextPage(Integer currPid);

	public Page getPrePage(Integer currPid);

	public void saveAnswers(List<Answer> processAnswers);

	public List<Question> getQuestions(Integer sid);

	public List<Answer> getAnswers(Integer sid);
	
}
