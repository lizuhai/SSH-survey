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

	<s:include value="/WEB-INF/common/header.jsp"></s:include>
	<table>
		<tr>
			<td class="tdHeader">添加/编辑页内容:</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<s:form action="PageAction_saveOrUpdatePage" namespace="/" method="post">
								<s:hidden name="id"></s:hidden>
								<s:hidden name="sid"></s:hidden>
								<s:hidden name="orderno"></s:hidden>
								<table>
									<tr>
										<td class="tdFormLabel">页面标题：</td>
										<td class="tdFormControl"><s:textfield name="title" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">页面描述：</td>
										<td class="tdFormControl"><s:textarea name="description" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">页面标题：</td>
										<td class="tdFormControl"><s:submit value="Submit" cssClass="btn" /></td>
									</tr>
								</table>
							</s:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>