package me.zhli.web.surveypark.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Answer;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.service.SurveyService;

/**
 * 收集调查
 */
@Controller
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey> {

	private static final long serialVersionUID = 868232415041678308L;
	
	private Integer sid;
	@Resource
	private SurveyService surveyService;
	
	public String execute() {
		// sid
		return SUCCESS;
	}
	
	@SuppressWarnings("resource")
	public InputStream getIs() {
		// poi --> inputstream 
		try {
			// 存放 qid --> index	Map<qid, cellIndex>
			Map<Integer, Integer> qidIndexMap = new HashMap<>();
			
			List<Question> list = surveyService.getQuestions(sid);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("收集调查");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			Question q = null;
			// 输出问题的标题
			for (int i = 0; i < list.size(); i++) {
				q = list.get(i);
				cell = row.createCell(i);
				cell.setCellValue(q.getTitle());
				// 设置单元格宽度
				sheet.setColumnWidth(i, 6000);
				
				qidIndexMap.put(q.getId(), i);
			}
			// 设置单元格样式 -- 回绕文本
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			
			// 输出 answers
			List<Answer> answers = surveyService.getAnswers(sid);
			String oldUuid = "";
			String newUuid = "";
			int rowIndx = 0;
			for (Answer a : answers) {
				newUuid = a.getUuid();
				if(!newUuid.equals(oldUuid)) {
					rowIndx ++;
					oldUuid = newUuid;
					row = sheet.createRow(rowIndx);
				}
				cell = row.createCell(qidIndexMap.get(a.getQuestionId()));
				cell.setCellValue(a.getAnswerIds());
				cell.setCellStyle(style);
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
}
