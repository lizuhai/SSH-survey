<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参与调查</title>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp"></s:include>
	<s:form action="EngageSurveyAction_doEngageSurvey" method="post">
		<s:hidden name="currPid" value="%{currPage.id}" />
		<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<!-- Survey Title -->
			<tr>
				<td colspan="2" class="tdPHeaderL"><s:property value="#session.current_survey.title" /></td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<!-- Page Title -->
			<tr>
				<td colspan="2" class="tdPHeaderL"><s:property value="currPage.title" /></td>
			</tr>
			<tr>
				<td width="30px"></td>
				<td>
					<table>
						<!-- 遍历集合 -->
						<s:iterator value="currPage.questions" var="q">
							<s:set value="#q.questionType" var="qt" />
							<s:set value="#q.id" var="qId" />
						<%-- 	<s:property value="#q.questionType"/> --%>
							<tr>
								<td class="tdQHeaderL"><s:property value="#q.title" /></td>
							</tr>
							<tr>
								<td class="tdOptionArea">
									<s:property value="#qt"/>
									
									<s:if test="#qt < 4">
										<s:iterator value="#q.optionArr" status="st">
											<input type='<s:property value="#qt < 2?'radio' : 'checkbox'" />'><s:property />
											<!-- 是否带换行 -->
											<s:if test="#qt == 1 || #qt == 3"><br/></s:if>
										</s:iterator>
										
										<!-- 是否带 other -->
										<s:if test="#q.other">
											<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'>其他
											<!-- 文本框 -->
											<s:if test="#q.otherStyle == 1">
												<input type="text">
											</s:if>
											<!-- 下拉列表 -->
											<s:elseif test="#q.otherStyle == 2">
												<select>
													<s:iterator value="#q.otherSelectionOptionArr">
														<option><s:property /></option>
													</s:iterator>
												</select>
											</s:elseif>
										</s:if>
									</s:if>
									
									<!-- 下拉列表 -->
									<s:if test="#qt == 4">
										<select>
											<s:iterator value="#q.optionArr">
												<option><s:property /></option>
											</s:iterator>
										</select>
									</s:if>
									
									<!-- 文本框 -->
									<s:if test="#qt == 5">
										<input type="text">
									</s:if>
								
									<!-- 题型为6,7，8(矩阵式问题) -->
									<s:if test="#qt > 5">
										<table>
											<!-- 表头 -->
											<tr>
												<td></td>
												<s:iterator value="#q.matrixColTitleArr">
													<td><s:property /></td>
												</s:iterator>
											</tr>
											<!-- 输出 n 多行 -->
											<s:iterator value="#q.matrixRowTitleArr">
												<tr>
													<td><s:property /></td>
													<s:iterator value="#q.matrixColTitleArr">
														<td>
															<s:if test="#qt == 6"><input type="radio"></s:if>
															<s:if test="#qt == 7"><input type="checkbox"></s:if>
															<s:if test="#qt == 8">
																<select>
																	<s:iterator value="#q.matrixSelectionOptionArr">
																		<option><s:property /></option>
																	</s:iterator>
																</select>
															</s:if>
														</td>
													</s:iterator>
												</tr>
											</s:iterator>
										</table>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 上一步按钮 -->
					<s:if test="currPage.orderno != #session.current_survey.minOrderno">
						<input type="submit" name="sumbmit_pre" value='<s:property value="#session.current_survey.preText"/>' />
					</s:if>
					<!-- 下一步按钮 -->
					<s:if test="currPage.orderno != #session.current_survey.maxOrderno">
						<input type="submit" name="sumbmit_next" value='<s:property value="#session.current_survey.nextText"/>' />
					</s:if>
					<!-- 完成按钮 -->
					<s:if test="currPage.orderno == #session.current_survey.maxOrderno">
						<input type="submit" name="sumbmit_done" value='<s:property value="#session.current_survey.doneText"/>' />
					</s:if>
					<!-- 退出按钮 -->
					<input type="submit" name="sumbmit_exit" value='<s:property value="#session.current_survey.exitText"/>' />
				</td>
			</tr>
		</table>
	</s:form>
	
</body>
</html>