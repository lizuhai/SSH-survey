package me.zhli.web.surveypark.model.statistics;

import java.io.Serializable;

public class OptionStatisticsModel implements Serializable {

	private static final long serialVersionUID = 3779863716421461577L;
	
	// 选项索引
	private int optionIndex;
	// 选项标签
	private String optionLabel;
	// 选项回答人数
	private int count;
	
	private int matrixRowIndx;
	private String matrixRowLabel;
	
	private int matrixColIndx;
	private String matrixSelectLabel;
	
	public String getMatrixSelectLabel() {
		return matrixSelectLabel;
	}
	public void setMatrixSelectLabel(String matrixSelectLabel) {
		this.matrixSelectLabel = matrixSelectLabel;
	}
	public int getMatrixSelectIndx() {
		return matrixSelectIndx;
	}
	public void setMatrixSelectIndx(int matrixSelectIndx) {
		this.matrixSelectIndx = matrixSelectIndx;
	}
	private int matrixSelectIndx;
	private String matrixColLabel;
	
	public int getMatrixColIndx() {
		return matrixColIndx;
	}
	public void setMatrixColIndx(int matrixColIndx) {
		this.matrixColIndx = matrixColIndx;
	}
	public String getMatrixColLabel() {
		return matrixColLabel;
	}
	public void setMatrixColLabel(String matrixColLabel) {
		this.matrixColLabel = matrixColLabel;
	}
	public int getOptionIndex() {
		return optionIndex;
	}
	public void setOptionIndex(int optionIndex) {
		this.optionIndex = optionIndex;
	}
	public String getOptionLabel() {
		return optionLabel;
	}
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMatrixRowIndx() {
		return matrixRowIndx;
	}
	public void setMatrixRowIndx(int matrixRowIndx) {
		this.matrixRowIndx = matrixRowIndx;
	}
	public String getMatrixRowLabel() {
		return matrixRowLabel;
	}
	public void setMatrixRowLabel(String matrixRowLabel) {
		this.matrixRowLabel = matrixRowLabel;
	}
	
}
