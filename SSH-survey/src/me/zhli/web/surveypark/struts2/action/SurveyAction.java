package me.zhli.web.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.Survey;
import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.SurveyService;
import me.zhli.web.surveypark.struts2.UserAware;
import me.zhli.web.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware, ServletContextAware {

	private static final long serialVersionUID = -5453092162499375686L;

	// 注入 SurveyService
	@Resource
	private SurveyService surveyService;
	// 调查集合
	private List<Survey> mySurveys;

	// 接收 User 对象
	private User user;
	
	// 指定错误页
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}
	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public String mySurveys() {
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	public String newSurvey() {
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	private Integer sid;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public String designSurvey() {
		// 1. 接收 sid
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	/**
	 * 该方法只在 designSurvey 方法之前调用
	 */
//	public void prepareDesignSurvey() {
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}

	/**
	 * 编辑调查
	 */
	public String editSurvey() {
		// sid
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	public String updateSurvey() {
		// model  
		this.sid = model.getId();
		// 保持关联关系
		model.setUser(user);
		surveyService.updateSurvey(model);
		// redirect to action
		return "designSurveyAction";
	}
	
	/**
	 * 该方法只在之前运行
	 */
	public void prepareUpdateSurvey() {
		inputPage = "/editSurvey.jsp";
	}
	
	public String deleteSurvey() {
		// sid
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 清除答案
	 */
	public String clearAnswers() {
		// sid
		surveyService.clearAnswer(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 打开/关闭调查
	 */
	public String toggleStatus() {
		// sid
		surveyService.toggleStatus(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 到达 logopage页面
	 */
	public String toAddLogoPage() {
		// sid
		return "addLogoPage";
	}
	
	// 上传文件
	private File logoPhoto;
	// 文件名称
	private String logoPhotoFileName;
	// 接收 servletContext 对象
	private ServletContext sc;
	public File getLogoPhoto() {
		return logoPhoto;
	}
	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}
	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}
	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	/**
	 * 实现 logo 上传
	 * @return
	 */
	public String doAddLogo() {
		// sid + 文件
		if(ValidateUtil.isValidate(logoPhotoFileName)) {
			// 1. 实现文件的另存为 /upload/文件夹下
			// upload 文件夹真实路径
			String dir = sc.getRealPath("/upload");
			// 扩展名
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			// 文件名
			long l = System.nanoTime();
			File newFile = new File(dir, l + ext);

			logoPhoto.renameTo(newFile);
			
			// 2. 更新数据库路径信息
			surveyService.updateLogoPhotoPath(sid, "/upload/" + l + ext);
		}
		return "designSurveyAction";
	}
	
	/**
	 * 该方法只在之前运行
	 */
	public void prepareDoAddLogo() {
		inputPage = "/addLogo.jsp";
	}
	
	/**
	 * 图片是否存在
	 */
	public boolean logoPhotoExists() {
		String path = model.getLogoPhotoPath();
		if(ValidateUtil.isValidate(path)) {
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		return false;
	}
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}

	// 注入 servletContext 对象
	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	/**
	 * 分析调查
	 */
	public String analyzeSurvey() {
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage";
	}
}
