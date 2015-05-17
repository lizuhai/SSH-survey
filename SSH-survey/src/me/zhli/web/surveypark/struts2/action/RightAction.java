package me.zhli.web.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.service.RightService;

/**
 * RightAction
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = 7804676411197907825L;
	
	private List<Right> allRights;
	@Resource
	private RightService rightService;

	private Integer rightId;

	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public List<Right> getAllRights() {
		return allRights;
	}
	
	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}
	
	/**
	 * 查询所有权限
	 */
	public String findAllRights() {
		this.allRights = rightService.findAllEntities();
		return "rightListPage";
	}
	
	/**
	 * 添加权限
	 */
	public String toAddRightPage() {
		return "addRightPage";
	}
	
	/**
	 * 保存或更新权限
	 */
	public String saveOrUpdateRight() {
		// model
		// 插入 -- 处理权限位和权限码
		rightService.sageOrUpdateRight(model);
		return "findAllRightsAction";
	}
	
	/**
	 * 编辑权限
	 */
	public String editRight() {
		// rightId
		this.model = rightService.getEntity(rightId);
		return "editRightPage";
	}
	
	/**
	 * 删除权限
	 */
	public String deleteRight() {
		// rightId
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightsAction";
	}
	
	/**
	 * 批量更新权限
	 */
	public String batchUpdateRights() {
		// alllRights
		rightService.batchUpdateRights(allRights);
		return "findAllRightsAction";
	}
}
