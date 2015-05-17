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

	<s:include value="/WEB-INF/common/header.jsp" />
	<s:if test="surveys.isEmpty()">目前没有可用的调查</s:if>
	<s:else>
		<s:set value="5" var="cells" />
		<table>
			<tr>
				<td colspan='<s:property value="#cells" />' style="height: 5px;"></td>
			</tr>
			<tr>
				<td colspan='<s:property value="#cells" />' class="tdHeader">参与调查：请选择要参与的调查</td>
			</tr>
			<tr>
				<td colspan='<s:property value="#cells" />' style="height: 5px;">请选择要参与的调查</td>
			</tr>
	 		<s:iterator var="i" begin="0" end="%{surveys.size - 1}" step="#cells">
				<s:set value="id" var="sId" />
				<tr>
					<s:iterator var="j" begin="0" end="%{#cells - 1}" step="1">
						<s:set var="idx" value="#i + #j" />
						<td width='<s:property value="100 / #cells" />%'>
							<s:if test="#idx < surveys.size">
								<s:a action="EngageSurveyAction_entry?sid=%{surveys[#idx].id}" cssClass="aList">
									<img alt='<s:property value="surveys[#idx].title" />' 
										 <%-- src='<s:property value="surveys[#idx].logoPhotoPath" />' --%>
										 src='<s:property value="getImageUrl(surveys[#idx].logoPhotoPath)" />'
										 width="80px" height="80px">
									<br />
									<s:property value="#idx + 1"/>、 <s:property value="surveys[#idx].title"/>
								</s:a>
							</s:if>						 	
						</td>
					</s:iterator>
				</tr>
			</s:iterator>
		</table>
	</s:else>

</body>
</html>