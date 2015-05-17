package me.zhli.web.surveypark.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.Answer;
import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.SurveyService;
import me.zhli.web.surveypark.util.DataUtil;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;

	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	
	
	/**
	 * 查询调查集合
	 */
	@Override
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}


	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		// 设置关联关系
		s.setUser(user);
		p.setSurvey(s);
//		System.out.println(s.getPages());	// null
//		s.getPages().add(p);
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}


	@Override
	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}


	@Override
	public Survey getSurveyWithChildren(Integer sid) {
//		Survey s = surveyDao.getEntity(sid);
		// 下面的更好，高内聚，低耦合
		Survey s = this.getSurvey(sid);
		// 强行初始化 pages 和 questions 集合
		for(Page p : s.getPages()) {
			p.getQuestions().size();
		}
		return s;
	}

	/**
	 * 更新调查
	 */
	@Override
	public void updateSurvey(Survey s) {
		surveyDao.updateEntity(s);
	}

	/**
	 * 保存或更新页面
	 */
	@Override
	public void saveOrUpdate(Page p) {
		pageDao.saveOrUpdateEntity(p);
	}


	@Override
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}


	@Override
	public void saveOrUpdateQuestion(Question q) {
		questionDao.saveOrUpdateEntity(q);
	}

	/**
	 * 删除 question
	 * 1. 删除答案
	 * 2. 删除问题
	 */
	@Override
	public void deleteQuestion(Integer qid) {
		// 1. 先删除 answer
		String hql = "delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql, qid);
		// 2. 删除 question
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	/**
	 * 删除 pa'ge
	 * 1. 删除答案
	 * 2. 删除问题
	 * 3. 删除页面
	 */
	@Override
	public void deletePage(Integer pid) {
		// 1. delete answer
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		// 2. delete question
		hql = "delete from Question q where q.page.id = ?";
		questionDao.batchEntityByHQL(hql, pid);
		// 3. delete page
		hql = "delete from Page p where p.id = ?";
		pageDao.batchEntityByHQL(hql, pid);
	}

	/**
	 * 删除 survey
	 * 1. 删除答案
	 * 2. 删除问题
	 * 3. 删除页面
	 * 4. 删除调查
	 */
	@Override
	public void deleteSurvey(Integer sid) {
		// 1. delete answer
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
		// 2. delete question
		// hibernate 在写操作中，不允许两级以上以上的链接
		// hql = "delete from Question q where q.page.survey.id = ?"; 	// X
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.batchEntityByHQL(hql, sid);
		// 3. delete page
		hql = "delete from Page p where p.survey.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
		// 4. delete survey
		hql = "delete from Survey s where s.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
	}

	/**
	 * 编辑问题
	 */
	@Override
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}

	/**
	 * 清除调查的答案
	 */
	@Override
	public void clearAnswer(Integer sid) {
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	/**
	 * 打开/关闭
	 */
	@Override
	public void toggleStatus(Integer sid) {
		// hql 不支持取反操作
//		String hql = "update Survey s set s.closed = !s.closed where s.id = ?";		// X
		Survey s = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		answerDao.batchEntityByHQL(hql, !s.isClosed(), sid);
	}

	/**
	 * 更新logo 的路径
	 */
	@Override
	public void updateLogoPhotoPath(Integer sid, String path) {
		String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, path, sid);
	}


	@Override
	public List<Survey> getSurveyWithPages(User user) {
		String hql = "from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		for(Survey s : list) {
			s.getPages().size();
		}
		return list;
	}

	/**
	 * 进行页面移动/复制
	 */
	@Override
	public void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos) {
		// srcPid --> srcPage --> srcSurvey
		// targPid --> targPage --> targSurvey
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targPage = this.getPage(targPid);
		Survey tartSurvey = targPage.getSurvey();
		// 判断移动还是复制
		if(srcSurvey.getId().equals(tartSurvey.getId())) {
			setOrderno(srcPage, targPage, pos);
		} else {
			// 由于懒加载，必须强行初始化问题集合，不然深度复制的页面对象就没有问题
			srcPage.getQuestions().size();
			// copy
			Page copyPage = (Page) DataUtil.deeplyCopy(srcPage); //deeplyCopy(srcPage);
			// 建立关系
			copyPage.setSurvey(tartSurvey);
			pageDao.saveEntity(copyPage);
			for(Question q : copyPage.getQuestions()) {
				questionDao.saveEntity(q);
			}
			// 保存到数据库
			setOrderno(copyPage, targPage, pos);
		}
	}

	/**
	 * 设置页序
	 */
	private void setOrderno(Page srcPage, Page targPage, int pos) {
		// 判断位置 0-之前 1-之后
		if(pos == 0) {
			if(isFirstPage(targPage)) {
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			} else {
				// 取得目标页的前页
				Page prePage = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + prePage.getOrderno()) / 2);
			}
		} else {
			if(isLastPage(targPage)) {
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			} else {
				// 取得目标页的前页
				Page nextPage = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + nextPage.getOrderno()) / 2);
			}
		}
	}


	/**
	 * 判断指定页面的下一页
	 */
	private Page getNextPage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list.get(0);
	}

	/**
	 * 判断指定页面的上一页
	 */
	private Page getPrePage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list.get(0);
	}

	/**
	 * 判断指定页面是否是所在调查尾页
	 */
	private boolean isLastPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ?";
		long count = (long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count == 0;
	}

	/**
	 * 判断指定页面是否是所在调查首页
	 */
	private boolean isFirstPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ?";
		long count = (long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count == 0;
	}


	@Override
	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}


	@Override
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, sid);
		Page p = list.get(0);
		p.getSurvey().getTitle();
		p.getQuestions().size();
		return p;
	}

	/**
	 * 获得下一页
	 */
	@Override
	public Page getNextPage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getNextPage(p);
		// 解决懒加载问题
		p.getQuestions().size();
		return p;
	}

	/**
	 * 获得上一页
	 */
	@Override
	public Page getPrePage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getPrePage(p);
		// 解决懒加载问题
		p.getQuestions().size();
		return p;
	}

	/**
	 * 批量保存答案
	 */
	@Override
	public void saveAnswers(List<Answer> list) {
		Date date = new Date();
		String uuid = UUID.randomUUID().toString();
		for(Answer a : list) {
			a.setUuid(uuid);
			a.setAnswearTime(date);
			answerDao.saveEntity(a);
		}
	}

	/**
	 * 查询指定调查的所有问题
	 */
	@Override
	public List<Question> getQuestions(Integer sid) {
		String hql = "from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		return questionDao.findEntityByHQL(hql, sid);
	}

	/**
	 * 查询指定调查的所有答案
	 */
	@Override
	public List<Answer> getAnswers(Integer sid) {
		String hql = "from Answer a where a.surveyId = ? order by a.uuid asc";
		return answerDao.findEntityByHQL(hql, sid);
	}
	
}
