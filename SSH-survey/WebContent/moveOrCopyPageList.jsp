<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>移动/复制页面</title>
</head>
<body>
	
	<s:include value="/WEB-INF/common/header.jsp" />
	<table>
		<tr>
			<td colspan="3" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td colspan="3" class="tdHeader">移动/赋值页：【同一调查内是移动，不同调查间是复制】</td>
		</tr>
		<tr>
			<td colspan="3" class="tdWhiteLine"></td>
		</tr>
		<s:iterator value="mySurveys" var="s">
			<s:set value="#s.id" var="sId" />
			<tr>
				<td colspan="3" class="tdHeaderL"><s:property value="title" /></td>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			</tr>
			<s:iterator value="#s.pages" status="st" var="p">
				<s:set value="#p.id" var="pId" />
				<!-- 当前页面高亮 -->
				<s:if test="#pId == srcPid">
					<s:set var="bgcolor" value='"rgb(200,125,200)"' />
				</s:if>
				<s:else>
					<s:set var="bgcolor" value='"bgcolor=\"white\""' />
				</s:else>
				<tr bgcolor='<s:property value="#bgcolor" />'>
					<td style="width: 30px;border-width: 0;background-color: white;"></td>
					<td><s:property value="#p.title" /></td>
					<td>
						<s:if test="#pId != srcPid">
							<s:form name="form%{#pId}" action="MoveOrCopyPageAction_doMoveOrCopyPage" method="post">
								<s:hidden name="srcPid"></s:hidden>
								<s:hidden name="targPid" value="%{#pId}"></s:hidden>
								<s:hidden name="sid" value="%{#sId}"></s:hidden>
								<s:radio list="#{0:'之前',1:'之后' }" listKey="key" listValue="value" name="pos"></s:radio>
								<input type="submit" value="确定">
							</s:form>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</s:iterator>
	</table>
</body>
</html>