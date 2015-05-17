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
		<tr><td colspan="2" class="tdHeader">矩阵式问题调查结果分析</td></tr>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<tr><td colspan="2" class="tdHeader"><s:property value="qsm.question.title" /></td></tr>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<tr>
			<td>
				<table border="1px" cellspacing="0">
					<tr>
						<td width="30px"></td>
						<s:iterator value="qsm.question.matrixColTitleArr">
							<td><s:property /></td>
						</s:iterator>
					</tr>
					<s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
						<tr>
							<!-- 行头 -->
							<td><s:property /></td>
							<s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
								<td>
									<s:property value="getScale(#rst.index, #cst.index)"/>
								</td>
							</s:iterator>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: left;">共有&nbsp;<s:property value="qsm.count" />&nbsp;人参与问卷</td>
		</tr>
	</table>
</body>
</html>