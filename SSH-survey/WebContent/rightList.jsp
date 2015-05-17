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
			<td colspan="10" class="tdHeaderR"><s:a action="RightAction_toAddRightPage" namespace="/">添加权限</s:a></td>
		</tr>
		<tr><td colspan="10" style="height: 5px"></td></tr>
	</table>
	
	<s:if test="allRights.isEmpty() == true">目前还没有任何权限</s:if>
	<s:else>
		<s:form action="RightAction_batchUpdateRights" namespace="/" method="post">
			<table>
				<thead>
					<tr><td colspan="10" style="height: 5px"></td></tr>
					<tr><td colspan="10" style="height: 5px">权限管理</td></tr>
					<!-- 输出分页条 -->
					<tr><td colspan="10" style="height: 5px;text-align: left;"></td></tr>
					<tr>
						<td class="tdListHeader">ID</td>
						<td class="tdListHeader">权限名称</td>
						<td class="tdListHeader">公共资源<br>
							<input type="checkbox" id="cbSelectAll">全选
							<a id="inverseSelectAll" href="#">反选</a>
						</td>
						<td class="tdListHeader">权限URL</td>
						<td class="tdListHeader">权限位</td>
						<td class="tdListHeader">权限码</td>
						<td class="tdListHeader">修改</td>
						<td class="tdListHeader">删除</td>
					</tr>
				</thead>
				
				<tbody>
					<s:iterator value="allRights" status="st">
						<s:set var="rightId" value="id" />
						<tr>
							<td>
								<%-- <s:property value="id"/> --%>
								<s:textfield name="allRights[%{#st.index}].id" cssClass="text" cssStyle="width:50px" readonly="true" />
							</td>
							<td>
								<%-- <s:property value="rightName"/> --%>
								<s:textfield name="allRights[%{#st.index}].rightName" cssClass="text" cssStyle="width:150px" />
							</td>
							<td style="text-align: left;">
								<s:checkbox name="allRights[%{#st.index}].common"></s:checkbox>
							</td>
							<td style="text-align: left;"><s:property value="rightUrl" /></td>
							<td style="text-align: left;color: gray;"><s:property value="rightPos" /></td>
							<td style="text-align: left;color: gray;"><s:property value="rightCode" /></td>
							<td><s:a action="RightAction_editRight?rightId=%{#rightId} " cssClass="aList">修改</s:a></td>
							<td><s:a action="RightAction_deleteRight?rightId=%{#rightId} " cssClass="aList">删除</s:a></td>
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