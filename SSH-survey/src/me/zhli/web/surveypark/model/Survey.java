package me.zhli.web.surveypark.model;

import java.util.Date;
import java.util.Set;

/**
 * Survey Class
 */
public class Survey extends BaseEntity {
	private static final long serialVersionUID = -2108496285563107913L;
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createDate = new Date();
	
	private boolean closed = false;			// 打开/关闭调查，默认开放
	
	private String logoPhotoPath;
	
	private float minOrderno;
	private float maxOrderno;
	
	public float getMinOrderno() {
		return minOrderno;
	}
	public void setMinOrderno(float minOrderno) {
		this.minOrderno = minOrderno;
	}
	public float getMaxOrderno() {
		return maxOrderno;
	}
	public void setMaxOrderno(float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}
	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}
	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	// 建立从 Survey 到 User 多对一的关联关系
	private User user;
	
	public Set<Page> pages;
	
	public Set<Page> getPages() {
		return pages;
	}
	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPreText() {
		return preText;
	}
	public void setPreText(String preText) {
		this.preText = preText;
	}
	public String getNextText() {
		return nextText;
	}
	public void setNextText(String nextText) {
		this.nextText = nextText;
	}
	public String getExitText() {
		return exitText;
	}
	public void setExitText(String exitText) {
		this.exitText = exitText;
	}
	public String getDoneText() {
		return doneText;
	}
	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
