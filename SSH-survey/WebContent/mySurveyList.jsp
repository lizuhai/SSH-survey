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
	
	<s:include value="/WEB-INF/common/header.jsp"></s:include><br/>
	<s:if test="mySurveys.isEmpty() == true">目前还没有任何调查</s:if>
	<s:else>
		<table>
			<tr><td colspan="10" style="height: 5px"></td></tr>
			<tr><td colspan="10" class="tdHeader">My Survey</td></tr>
			<tr><td colspan="10" style="height: 5px"></td></tr>
			<tr>
				<td class="tdListHeader">ID</td>
				<td class="tdListHeader">调查标题</td>
				<td class="tdListHeader">创建时间</td>
				<td class="tdListHeader">状态</td>
				<td class="tdListHeader">设计</td>
				<td class="tdListHeader">收集信息</td>
				<td class="tdListHeader">分析</td>
				<td class="tdListHeader">open/close</td>
				<td class="tdListHeader">清除调查</td>
				<td class="tdListHeader">删除</td>
			</tr>
			<s:iterator value="mySurveys">
				<s:set value="id" var="sId" />
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="title" /></td>
					<td><s:date name="createDate" format="MM/dd/YY HH:mm" /></td>
					<td>
						<s:if test="closed">closed</s:if>
						<s:else>opened</s:else>
					</td>
					<td><s:a action="SurveyAction_designSurvey?sid=%{#sId}" namespace="/" cssClass="aList">design</s:a></td>
					<td><s:a action="CollectionSurveyAction?sid=%{#sId}" namespace="/" cssClass="aList">collect information</s:a></td>
					<td><s:a action="SurveyAction_analyzeSurvey?sid=%{#sId}" namespace="/" cssClass="aList">analysis</s:a></td>
					<td><s:a action="SurveyAction_toggleStatus?sid=%{#sId}" namespace="/" cssClass="aList">open/close</s:a></td>
					<td><s:a action="SurveyAction_clearAnswers?sid=%{#sId}" namespace="/" cssClass="aList">clear survey</s:a></td>
					<td><s:a action="SurveyAction_deleteSurvey?sid=%{#sId}" namespace="/" cssClass="aList">delete</s:a></td> 
				</tr>
			</s:iterator>
		</table>
	</s:else>

</body>
</html>