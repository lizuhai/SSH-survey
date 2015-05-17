<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志列表</title>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp"></s:include><br/>
	<table>
		<tr><td colspan="10" style="height: 5px"></td></tr>
		<tr>
			<td colspan="10" style="tdPHeaderR">
				<s:form action="LogAction_findNearestLogs" namespace="/">
					<s:textfield name="monthNum"></s:textfield>
					<s:submit value="确认"></s:submit>
				</s:form>
			</td>
		</tr>
		<tr><td colspan="10" style="height: 5px"></td></tr>
	</table>
	<s:if test="logs.isEmpty() == true">暂时没有日志</s:if>
	<s:else>
		<table>
			<thead>
				<tr><td colspan="10" style="height: 5px"></td></tr>
				<tr><td colspan="10" style="tdPHeaderR">权限管理</td></tr>
				<tr>
					<td class="tdListHeader">操作人</td>
					<td class="tdListHeader">操作名称</td>
					<td class="tdListHeader">操作参数</td>
					<td class="tdListHeader">操作结果</td>
					<td class="tdListHeader">消息</td>
					<td class="tdListHeader">时间</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="logs" status="st">
					<tr>
						<td><s:property value="operator" /></td>
						<td><s:property value="operName" /></td>
						<td>
							<span title='<s:property value="operParams" />'><s:property value="@me.zhli.web.surveypark.util.StringUtil@getDescString(operParams)" /></span>
						</td>
						<td>
							<span title='<s:property value="operResult" />'><s:property value="@me.zhli.web.surveypark.util.StringUtil@getDescString(operResult)" /></span>
						</td>
						<td>
							<span title='<s:property value="resultMsg" />'><s:property value="@me.zhli.web.surveypark.util.StringUtil@getDescString(resultMsg)" /></span>
						</td>
						<td><s:date name="operTime" format="YY/MM/dd hh:mm:ss" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:else>
</body>
</html>