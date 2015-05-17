<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加 Logo</title>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp"></s:include><br/>
	<table>
		<tr>
			<td>增加 Logo</td>
		</tr>
		<tr>
			<td>
				<table>
					<s:form action="SurveyAction_doAddLogo.action" method="post" enctype="multipart/form-data">
						<tr>
							<td><input type="hidden" value="${sid }" name="sid"></td>
						</tr>
						<tr>
							<td>请选择 Logo</td>
							<td>
								<input type="file" name="logoPhoto" />
							</td>
							<td>
								<s:fielderror fieldName="logoPhoto"></s:fielderror>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><s:submit value="上传" /></td>
						</tr>
					</s:form>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>