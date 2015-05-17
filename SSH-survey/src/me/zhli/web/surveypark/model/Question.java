package me.zhli.web.surveypark.model;

import me.zhli.web.surveypark.util.StringUtil;

/**
 * Question Class
 */
public class Question extends BaseEntity {
	
	private static final long serialVersionUID = -2132130076299732389L;

	private static final String RN = "\r\n";

	// 建立从 Question 到 Page 的 n -> 1 关联关系
	private Page page;
	
	private Integer id;
	
	// 题型0-8
	private int questionType;
	
	private String title;
	// 选项
	private String options;
	private String [] optionArr;
	
	// 其他项
	private boolean other;
	
	// 其他项样式：0-无   1-文本框  2-下拉列表
	private int otherStyle;
	
	// 其他下拉选项
	private String otherSelectionOptions;
	private String [] otherSelectionOptionArr;
	
	// 矩阵式行标题集
	private String matrixRowTitles;
	private String [] matrixRowTitleArr;
	
	// 矩阵式列标题集
	private String matrixColTitles;
	private String [] matrixColTitleArr;
	
	// 矩阵式下拉选项集
	private String matrixSelectionOptions;
	private String [] matrixSelectionOptionArr;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOptions() {
		return options;
	}
	
	/**
	 * 重写该方法完成字符串的拆分
	 */
	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtil.str2Arr(options, RN);
	}
	public boolean isOther() {
		return other;
	}
	public void setOther(boolean other) {
		this.other = other;
	}
	public String getOtherSelectionOptions() {
		return otherSelectionOptions;
	}
	public void setOtherSelectionOptions(String otherSelectionOptions) {
		this.otherSelectionOptions = otherSelectionOptions;
		this.otherSelectionOptionArr = StringUtil.str2Arr(otherSelectionOptions, RN);
	}
	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr = StringUtil.str2Arr(matrixRowTitles, RN);
	}
	public String getMatrixColTitles() {
		return matrixColTitles;
	}
	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitleArr = StringUtil.str2Arr(matrixColTitles, RN);
	}
	public String getMatrixSelectionOptions() {
		return matrixSelectionOptions;
	}
	public void setMatrixSelectionOptions(String matrixSelectionOptions) {
		this.matrixSelectionOptions = matrixSelectionOptions;
		this.matrixSelectionOptionArr = StringUtil.str2Arr(matrixSelectionOptions, RN);
	}
	public int getOtherStyle() {
		return otherStyle;
	}
	public void setOtherStyle(int otherStyle) {
		this.otherStyle = otherStyle;
	}
	
	
	public String[] getOtherSelectionOptionArr() {
		return otherSelectionOptionArr;
	}
	public void setOtherSelectionOptionArr(String[] otherSelectionOptionArr) {
		this.otherSelectionOptionArr = otherSelectionOptionArr;
	}
	public String[] getMatrixColTitleArr() {
		return matrixColTitleArr;
	}
	public void setMatrixColTitleArr(String[] matrixColTitleArr) {
		this.matrixColTitleArr = matrixColTitleArr;
	}
	public String[] getMatrixSelectionOptionArr() {
		return matrixSelectionOptionArr;
	}
	public void setMatrixSelectionOptionArr(String[] matrixSelectionOptionArr) {
		this.matrixSelectionOptionArr = matrixSelectionOptionArr;
	}
	public String[] getOptionArr() {
		return optionArr;
	}
	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}
	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}
	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}
} 
