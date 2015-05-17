<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W10C//DTD HTML 4.01 Transitional//EN" "http://www.w10.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限管理</title>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp" />
	
	<s:form action="RoleAction_saveOrUpdateRole" namespace="/" method="post">
		<s:hidden name="id" />
		<table>
			<thead>
				<tr><td colspan="10" style="height: 5px"></td></tr>
				<tr><td colspan="10" style="height: 5px">授权管理</td></tr>
				<!-- 输出分页条 -->
				<tr><td colspan="10" style="height: 5px;text-align: left;"></td></tr>
			</thead>
			<tbody>
				<tr>
					<th class="thListHeader">count</th>
					<th class="thListHeader">ID</th>
					<th class="thListHeader">email</th>
					<th class="thListHeader">昵称</th>
					<th class="thListHeader">修改授权</th>
					<th class="thListHeader">清除权限</th>
				</tr>
				<s:iterator value="allUsers" status="st">
					<s:set value="id" var="userId" />
					<tr>
						<td><s:property value="#st.count" /></td>
						<td><s:property value="id" /></td>
						<td style="text-align: left;"><s:property value="email" /></td>
						<td style="color: gray; text-align: left;"><s:property value="nickName" /></td>
						<td><s:a action="UserAuthorizeAction_editAuthorize?userId=%{#userId}" cssClass="aList" >修改授权</s:a></td>
						<td><s:a action="UserAuthorizeAction_clearAuthorize?userId=%{#userId}" cssClass="aList" >清除授权</s:a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:form>
</body>
</html>