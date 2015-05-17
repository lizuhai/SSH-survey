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

	<s:include value="/WEB-INF/common/header.jsp"></s:include>
	<s:form action="QuestionAction_toDesignQuestionPage.action" method="post">
		<s:hidden name="pid"></s:hidden>
		<s:hidden name="sid"></s:hidden>
		<table>
			<tr>
				<td colspan="2">
					<select name="questionType" onchange="this.form.submit();">
						<option selected="selected">请选择问题类型</option>
						<option value="0">非矩阵式横向单选按钮</option>
						<option value="1">非矩阵式纵向单选按钮</option>
						<option value="2">非矩阵式横向复选选按钮</option>
						<option value="3">非矩阵式纵向复选按钮</option>
						<option value="4">非矩阵式下拉列表</option>
						<option value="5">非矩阵式文本框</option>
						<option value="6">矩阵式单选按钮</option>
						<option value="7">矩阵式复选按钮</option>
						<option value="8">矩阵式下拉列表</option>
					</select>
				</td>
			</tr>
		</table>
	</s:form>

</body>
</html>