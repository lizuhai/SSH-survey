package me.zhli.web.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.UserService;
import me.zhli.web.surveypark.util.DataUtil;
import me.zhli.web.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {
	
	private static final long serialVersionUID = 1L;

	private String confirmPassword;
	
	public String getConformPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	// 注入 userService
	@Resource
	private UserService userService;
	
	/**
	 * 到达注册页面
	 */
	// 到这个方法时不校验
	@SkipValidation
	public String toRegPage() {
		return "regPage";
	}
	
	/**
	 * 运行用户注册
	 */
	public String doReg() {
		// 密码加密
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}
	
	/**
	 * 校验
	 * 1. not null
	 * 2. password 一致性
	 * 3. email 是否占用
	 */
	public void validate() {
		// validate not null
		if(!ValidateUtil.isValidate(model.getEmail())) {
			addFieldError("email", "email 是必填项！");
		}
		if(!ValidateUtil.isValidate(model.getPassword())) {
			addFieldError("password", "password 是必填项！");
		}
		if(!ValidateUtil.isValidate(model.getNickName())) {
			addFieldError("nickName", "nickName 是必填项！");
		}
		if(hasErrors()) 
			return ;
		// password 一致性
		if(!model.getPassword().equals(confirmPassword)) {
			addFieldError("password", "password 不一致");
			return ;
		}
		// email 是否占用
		if(userService.isRegisted(model.getEmail())) {
			addFieldError("email", "email 已占用");
		}
	}
}
