<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css"/>'>
</head>
<body>
	<s:include value="WEB-INF/common/header.jsp"></s:include>
	<s:form action="RegAction_doReg" method="post" namespace="/">
		<table>
			<tr><td colspan="2" class="tdWhiteLine"></td></tr>
			<tr><td colspan="2" class="tdHeader">注册用户</td></tr>
			<tr><td colspan="2" class="tdWhiteLine"></td></tr>
			<tr>
				<td class="tdFormLabel">Email</td>
				<td class="tdFormControl">
					<s:textfield name="email" cssClass="text"></s:textfield>
					<font class="fontError"><s:fielderror><s:param>email</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">password</td>
				<td class="tdFormControl">
					<s:password name="password" cssClass="text"></s:password>
					<font class="fontError"><s:fielderror><s:param>password</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">confirm password</td>
				<td class="tdFormControl">
					<s:password name="confirmPassword" cssClass="text"></s:password>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">nickName</td>
				<td class="tdFormControl">
					<s:textfield name="nickName" cssClass="text"></s:textfield>
					<font class="fontError"><s:fielderror><s:param>nickName</s:param></s:fielderror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><s:submit cssClass="btn" value="register"></s:submit></td>
			</tr>
		</table>
	</s:form>

</body>
</html>