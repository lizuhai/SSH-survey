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
		<tr><td colspan="10" style="height: 5px"></td></tr>
		<tr>
			<td colspan="10" class="tdHeaderR"><s:a action="RoleAction_toAddRolePage" namespace="/">添加角色</s:a></td>
		</tr>
		<tr><td colspan="10" style="height: 5px"></td></tr>
	</table>
	
	<s:if test="allRoles.isEmpty() == true">目前还没有任何角色</s:if>
	<s:else>
		<s:form action="RoleAction_batchUpdateRoles" namespace="/" method="post">
			<table>
				<thead>
					<tr><td colspan="10" style="height: 5px"></td></tr>
					<tr><td colspan="10" style="height: 5px">角色管理</td></tr>
					<!-- 输出分页条 -->
					<tr><td colspan="10" style="height: 5px;text-align: left;"></td></tr>
					<tr>
						<td class="tdListHeader">ID</td>
						<td class="tdListHeader">角色名称</td>
						<td class="tdListHeader">角色值<br>
						<td class="tdListHeader">角色描述<br>
						<td class="tdListHeader">修改</td>
						<td class="tdListHeader">删除</td>
					</tr>
				</thead>
				
				<tbody>
					<s:iterator value="allRoles" status="st">
						<s:set var="roleId" value="id" />
						<tr>
							<td>
								<s:property value="id"/>
								<%-- <s:textfield name="allRoles[%{#st.index}].id" cssClass="text" cssStyle="width:50px" readonly="true" /> --%>
							</td>
							<td>
								<s:property value="roleName"/>
								<%-- <s:textfield name="allRoles[%{#st.index}].roleName" cssClass="text" cssStyle="width:150px" /> --%>
							</td>
							<td>
								<s:property value="roleValue"/>
								<%-- <s:textfield name="allRoles[%{#st.index}].roleValue" cssClass="text" cssStyle="width:150px" /> --%>
							</td>
							<td style="text-align: left;"><s:property value="roleDesc" /></td>
							<td><s:a action="RoleAction_editRole?roleId=%{#roleId} " cssClass="aList">修改</s:a></td>
							<td><s:a action="RoleAction_deleteRole?roleId=%{#roleId} " cssClass="aList">删除</s:a></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="5" align="center"><s:submit value="确认" /></td>
					</tr>
				</tbody>
			</table>
			
		</s:form>
	</s:else>

</body>
</html>