<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分析调查</title>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp"></s:include>
	<s:set value="id" var="sId"></s:set>
	<table>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<tr><td colspan="2" class="tdHeader">分析调查</td></tr>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<tr><td colspan="2" class="tdHeader"><s:property value="title" /></td></tr>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<!-- 遍历页面集合 -->
		<s:iterator value="pages" var="p" status="pst">
			<!-- 设置变量，对当前 Page 的 id 属性进行保持 -->
			<s:set value="#p.id" var="pId" />
			<tr><td colspan="2" class="tdHeader"><s:property value="#p.title" /></td></tr>
			<tr>
				<td width="30px"></td>
				<td>
					<table>
						<!-- 遍历问题集合 -->
						<s:iterator value="#p.questions" var="q" status="qst">
							<s:set value="#q.id" var="qId" />
							<s:set value="#q.questionType" var="qt" />
							<tr>
								<!-- count:从 1 开始；index: 从 0 开始 -->
								<td class="tdQHeaderL" style="border-bottom: 1px solid white"><s:property value="#qId + 1" />.&nbsp;&nbsp;<s:property value="#q.title" /></td>
								<td class="tdQHeaderR" style="border-bottom: 1px solid white">
									<!-- 判断当前题型是否是矩阵题型 -->
									<s:if test="#qt > 5">
										<s:form action="MatrixStaticsAction" namespace="/" method="post">
											<input type="hidden" name="qid" value='<s:property value="#qId" />' >
											<s:submit value="查看矩阵式问题统计" />
										</s:form>
									</s:if>
									<s:elseif test="#qt <= 5">
										<s:form action="ChartOutputAction" namespace="/" method="post" target="_blank">
											<input type="hidden" name="qid" value='<s:property value="#qId" />' >
											<s:set var="chartList" value="#{0:'平面饼图', 
																			1:'立体饼图',
																			2:'横向平面柱状图',
																			3:'纵向平面柱状图',
																			4:'横向立体柱状图',
																			5:'纵向立体柱状图',
																			6:'平面折线图',
																			7:'立体折线图'
																			}" />
											<s:select name="chartType" list="#chartList" listKey="key" listValue="value"></s:select>
											<s:submit value="查看" cssClass="btn"></s:submit>
										</s:form>
									</s:elseif>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>