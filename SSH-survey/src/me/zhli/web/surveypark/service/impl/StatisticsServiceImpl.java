package me.zhli.web.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.Answer;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.statistics.OptionStatisticsModel;
import me.zhli.web.surveypark.model.statistics.QuestionStatisticsModel;
import me.zhli.web.surveypark.service.StatisticsService;

/**
 * 统计服务实现
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	
	@Override
	public QuestionStatisticsModel statistics(Integer qid) {
		Question question = questionDao.getEntity(qid);
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		qsm.setQuestion(question);
		
		// 统计回答问题的总人数
		String hql = "select count(*) from Answer a where a.questionId = ?";
		Long qcount = (Long) answerDao.uniqueResult(hql, qid);
		qsm.setCount(qcount.intValue());
		
		// 统计每个选项的情况
		int qt = question.getQuestionType();
		String ohql = "select count(*) from Answer a where a.questionId = ? and concat(',',a.answerIds,',') like ?";
		Long ocount = null;
		switch(qt) {
			// 非矩阵式
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				String [] arr = question.getOptionArr();
				OptionStatisticsModel osm = null;
				for (int i = 0; i < arr.length; i++) {
					osm = new OptionStatisticsModel();
					osm.setOptionLabel(arr[i]);
					ocount = (Long) answerDao.uniqueResult(ohql, qid, "%," + i + ",%");
					osm.setCount(ocount.intValue());
					qsm.getOsm().add(osm);
				}
				// other
				if(question.isOther()) {
					osm = new OptionStatisticsModel();
					osm.setOptionLabel("其他");
					ocount = (Long) answerDao.uniqueResult(ohql, qid, "%other%");
					osm.setCount(ocount.intValue());
					qsm.getOsm().add(osm);
				}
				break;
			// 矩阵式
			case 6:
			case 7:
			case 8:
				String [] rows = question.getMatrixRowTitleArr();
				String [] cols = question.getMatrixColTitleArr();
				String [] opts = question.getMatrixSelectionOptionArr();
				
				for (int i = 0; i < rows.length; i++) {
					for (int j = 0; j < cols.length; j++) {
						// matrix radio | checkbox
						if(qt != 8) {
							osm = new OptionStatisticsModel();
							osm.setMatrixRowIndx(i);
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixColIndx(j);
							osm.setMatrixColLabel(cols[j]);
							ocount = (Long) answerDao.uniqueResult(ohql, qid, "%," + i + "_" + j + ",%");
							osm.setCount(ocount.intValue());
							qsm.getOsm().add(osm);
						} else { 	// 下拉列表
							for (int k = 0; k < opts.length; k++) {
								osm = new OptionStatisticsModel();
								osm.setMatrixRowIndx(i);
								osm.setMatrixRowLabel(rows[i]);
								osm.setMatrixColIndx(j);
								osm.setMatrixColLabel(cols[j]);
								ocount = (Long) answerDao.uniqueResult(ohql, qid, "%," + i + "_" + j + "_" + k + ",%");
								osm.setCount(ocount.intValue());
								qsm.getOsm().add(osm);
							}
						}
					}
				}
				break;
		}
		return qsm;
	}

}
