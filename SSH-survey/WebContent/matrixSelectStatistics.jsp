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
			<td colspan="2" style="text-align: left; display: inline;">
				<!-- 通过左上角颜色块，用不同颜色代表下拉选项 -->
				<s:iterator value="qsm.question.matrixSelectionOptionArr" status="optst">
					<!-- 对问本框进行修饰，产生颜色块的特效 -->
					<input type="text" readonly="readonly" style="border: 1px solid blue;background-color: <s:property value='colors[#optst.index]'/>;width: 12px;height: 12px">
				</s:iterator>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1px" cellspacing="0">
					<tr>
						<td width="30px"></td>
						<s:iterator value="qsm.question.matrixColTitleArr">
							<td><s:property /></td>
						</s:iterator>
					</tr>
					<!-- 遍历行标题数组 -->
					<s:iterator var="row" value="qsm.question.matrixRowTitleArr" status="rst">
						<tr>
							<!-- 行头 -->
							<td><s:property /></td>
							<s:iterator var="col" value="qsm.question.matrixColTitleArr" status="cst">
								<td style="text-align: left;">
									<!-- 遍历下拉列表数组 -->
									<s:iterator value="qsm.question.matrixSelectionOptionArr" status="optst">
										<input type="text" style="border: 1px solid <s:property value='colors[#optst.index]'/>;
																  background-color: <s:property value='colors[#optst.index]'/>;
																  width: <s:property value='getPercent(#rst.index, #cst.index, #optst.index)'/>px; 
																  height: 12px" readonly="readonly">
										<s:property value="getScale(#rst.index, #cst.index, #optst.index)"/><br>
									</s:iterator>
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