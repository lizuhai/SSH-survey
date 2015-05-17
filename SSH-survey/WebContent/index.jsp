<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:include value="WEB-INF/common/header.jsp"></s:include>
	<s:if test="#session['user'] != null">
		<div class="divNavigatorOuterFrame">
			<div class="divNavigatorInnerFrame" style="text-align: right;">
				欢迎 <s:property value="#session['user'].nickName"/>&nbsp;&nbsp;
			</div>
		</div>
	</s:if>
	<s:form action="LoginAction_doLogin" namespace="/" method="post">
		<table>
			<tr><td colspan="2" class="tdWhiteLine"></td></tr>
			<tr><td colspan="2" class="tdHeaderLine">登陆</td></tr>
			<tr><td colspan="2" class="tdWhiteLine"></td></tr>
			<tr>
				<td class="tdFormLabel" width="40%">Email</td>
				<td class="tdFormControl">
					<s:textfield name="email" cssClass="text"  value="zhli@163.com"></s:textfield>
					<font class="fontError"><br /><s:actionerror></s:actionerror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">password</td>
				<td class="tdFormControl">
					<s:password name="password" cssClass="text" value="1"></s:password>
					<font class="fontError"></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><s:submit cssClass="btn" value="Login"></s:submit></td>
			</tr>
		</table>
	</s:form>
</body>
</html>