
package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import me.zhli.web.surveypark.util.ValidateUtil;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Submit;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * @see Submit
 */
public class SubmitTag extends AbstractClosingTag {

    private static final long serialVersionUID = 2179281109958301343L;

    protected String action;
    protected String method;
    protected String align;
    protected String type;
    protected String src;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Submit(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();

        Submit submit = ((Submit) component);
        submit.setAction(action);
        submit.setMethod(method);
        submit.setAlign(align);
        submit.setType(type);
        submit.setSrc(src);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
    @Override
    public int doEndTag() throws JspException {
    	return hasRight() ? super.doEndTag() : SKIP_BODY;
    }
    @Override
    public int doStartTag() throws JspException {
    	return hasRight() ? super.doStartTag() : SKIP_BODY;
    }
    /**
     * 有权限没
     */
    private boolean hasRight() {
    	String ns = getFormNameSpace();
    	String actionName = getValidActionName();
    	return ValidateUtil.hasRight(ns, actionName, (HttpServletRequest) pageContext.getRequest(), null);
    }
    
    /**
     * 获得所在表单的名字空间
     */
    private String getFormNameSpace() {
    	// 获得上级标签
    	Tag ptag = this.getParent();
    	while(ptag != null) {
    		if(ptag instanceof FormTag) {
    			return ((FormTag) ptag).namespace;
    		} else {
    			ptag = ptag.getParent();
    		}
    	}
    	return null;
    }
    
    /**
     * 获得所在表单的actionName
     */
    private String getValidActionName() {
    	if(ValidateUtil.isValidate(action)) {
    		return action;
    	}
    	// 获得上级标签
    	Tag ptag = this.getParent();
    	while(ptag != null) {
    		if(ptag instanceof FormTag) {
    			return ((FormTag) ptag).action;
    		} else {
    			ptag = ptag.getParent();
    		}
    	}
    	return null;
    }
}
