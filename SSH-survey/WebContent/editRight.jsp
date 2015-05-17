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
	<table>
		<tr>
			<td class="tdHeaderR">保存/更新权限</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<s:form action="RightAction_saveOrUpdateRight" method="post">
								<s:hidden name="id" />
								<table>
									<tr>
										<td class="tdFormLabel">权限名称</td>
										<td class="tdFormControl"><s:textfield name="rightName" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">权限URL</td>
										<td class="tdFormControl"><s:textfield name="rightUrl" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">权限位</td>
										<td class="tdFormControl"><s:textfield name="rightPos" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">权限码</td>
										<td class="tdFormControl"><s:textfield name="rightCode" cssClass="text" /></td>
									</tr>
									<tr>
									<tr>
										<td class="tdFormLabel">公共资源<br>
										<td class="tdFormControl"><s:checkbox name="common" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">权限描述</td>
										<td class="tdFormControl"><s:textarea name="rightDesc" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="确认" /></td>
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