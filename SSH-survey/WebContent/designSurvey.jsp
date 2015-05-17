<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>design survey</title>
</head>
<body>
	
	<s:include value="/WEB-INF/common/header.jsp"></s:include>
	<s:set var="sId" value="id" />
	<table>
		<tr><td colspan="2" class="tdWhiteLine"></td></tr>
		<tr>
			<td colspan="2" class="tdHeaderL">
				<s:if test="logoPhotoExists()">
					<img src="<s:url value="%{logoPhotoPath}" />" width="50px" height="25px">
				</s:if>
				<!-- 调查主题 -->
				<s:property value="title"/>
			</td>
			<td class="tdHeaderR">
				<s:a action="SurveyAction_toAddLogoPage?sid=%{#sId}" namespace="/">增加 Logo</s:a> &nbsp;
				<s:a action="SurveyAction_editSurvey?sid=%{#sId}" namespace="/">编辑调查</s:a> &nbsp;
				<s:a action="PageAction_toAddPage?sid=%{#sId}" namespace="/">增加页</s:a> &nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="2" style="text-align: center;vertical-align: top;">
			<table>
				<tr>
					<td width="30px"></td>
					<td width="">
						<table>
							<!-- 输出页面集合 -->
							<s:iterator value="pages" var="p">
							<s:set var="pId" value="#p.id" />
							<tr>
								<td>
									<table>
										<tr>
											<!-- 输出页面标题 -->
											<td class="tdHeaderL"><s:property value="#p.title" /></td>
											<td class="tdHeaderR">
												<s:a action="PageAction_editPage?sid=%{#sId}&pid=%{#pId}" namespace="/">编辑页面标题</s:a> &nbsp;
												<s:a action="MoveOrCopyPageAction_toSelectTargetPage?srcPid=%{#pId}" namespace="/">移动/复制页</s:a> &nbsp;
												<s:a href="QuestionAction_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}" namespace="/">增加问题</s:a> &nbsp;
												<s:a href="PageAction_deletePage?sid=%{#sId}&pid=%{#pId}" namespace="/">删除页</s:a>&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<td width="30px"></td>
											<td width="">
												<table>
													<tr>
														<td>
															<table>
																<!-- 迭代问题集合 -->
																<s:iterator value="#p.questions" var="q" >
																<s:set value="#q.id" var="qId"></s:set>
																	<tr>
																		<!-- 问题的题干 -->
																		<td class="tdQheaderL"><s:property value="#q.title" /></td>
																		<td class="tdQheaderR">
																			<s:a action="QuestionAction_editQuestion?sid=%{#sId}&qid=%{#qId}&pid=%{#pId}" namespace="/">编辑问题</s:a> &nbsp;
																			<s:a action="QuestionAction_deleteQuestion?sid=%{#sId}&qid=%{#qId}" namespace="/">删除问题</s:a> &nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" style="text-align: left;color: black;background-color: white;">
																			<!-- 处理选项的输出 —— 定义变量，设置第一大类的类型。 -->
																			<s:set var="qt" value="#q.questionType" />
																			<!-- 第一大类，题型为0,1,2,3(常规题) -->
																			<s:if test="#qt < 4">
																				<s:iterator value="#q.optionArr">
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
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							</s:iterator>
						</table>
						
					</td>
				</tr>
			</table>
		</tr>
	
	</table>
</body>
</html>