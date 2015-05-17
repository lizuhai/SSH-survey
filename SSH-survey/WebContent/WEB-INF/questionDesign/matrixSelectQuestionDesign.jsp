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
	<s:form action="QuestionAction_saveOrUpdateQuestion.action" method="post">
		<s:hidden name="id"></s:hidden>
		<s:hidden name="questionType"></s:hidden>
		<s:hidden name="pid"></s:hidden>
		<s:hidden name="sid"></s:hidden>
		<table>
			<tr>
				<td colspan="2" class="tdQHeaderL">矩阵式问题设计：</td>
			</tr>
			<tr>
				<td width="35%" style="text-align: right;">问题标题</td>
				<td width="*" style="text-align: left;"><s:textfield name="title" cssClass="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">行标题标签组</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="matrixRowTitles" /></td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">列标题标签组</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="matrixColTitles" /></td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">下拉列表选项集合</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="matrixSelectionOptions" /></td>
			</tr>
			<tr>
				<td style="text-align: right"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="确定" class="btn"/></td>
			</tr>
		</table>
	</s:form>

</body>
</html>