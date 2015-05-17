<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W10C//DTD HTML 4.01 Transitional//EN" "http://www.w10.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//对添加按钮进行事件绑定
			$('#add_em').click(function() {
				if($("#right :selected").length > 0) {
					$("#right option:selected").each(function() {
						$("#left").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
						$(this).remove();  
					})
				}
			
			});
			
			//对移除按钮进行事件绑定
			$('#remove_em').click(function() {
				if($("#left :selected").length>0) {
					$("#left option:selected").each(function() {
						$("#right").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
						$(this).remove();
					})
				}
			});
			
			//对添加所有按钮进行事件绑定
			$('#add_em_all').click(function() {
				if($("#right :selected").length > 0) {
					$("#right option:selected").each(function() {
						$("#left").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
						$(this).remove();
					})
				} 
				
				$("#right option:not(selected)").each(function() {
					$("#left").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
					$(this).remove();
				})
			
			});
			
			//对移除按钮进行事件绑定
			$('#remove_em_all').click(function() {
				if($("#left :selected").length>0) {
					$("#left option:selected").each(function() {
						$("#right").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
						$(this).remove();
					})
				}
				
				$("#left option:not(selected)").each(function() {
					$("#right").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
					$(this).remove();
				})
			});
			
			// struts2 不能自动选择左边的值，需要在提交时手动设置为选中状态
			$('#selectAll').click(function() {
				$("#left option:not(selected)").each(function() {
					// alert("ok")
					this.selected = true;
				})
				return true;
			})
			
		})
	</script>
</head>
<body>

	<s:include value="/WEB-INF/common/header.jsp" />
	
	<s:form action="RoleAction_saveOrUpdateRole" namespace="/" method="post">
		<s:hidden name="id" />
		<table>
			<tr><td colspan="10" style="height: 5px"></td></tr>
			<tr><td colspan="10" style="height: 5px">角色管理</td></tr>
			<!-- 输出分页条 -->
			<tr><td colspan="10" style="height: 5px;text-align: left;"></td></tr>
			<tr>
				<td class="tdListHeader">角色名称</td>
				<td><s:textfield name="roleName"></s:textfield></td>
			</tr>
			<tr>
				<td class="tdListHeader">角色值<br>
				<td><s:textfield name="roleValue"></s:textfield></td>
			</tr>
			<tr>
				<td class="tdListHeader">角色描述<br>
				<td><s:textarea name="roleDesc"></s:textarea></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>添加至我的权限</td>
				<td></td>
				<td>所有权限</td>
			</tr>
			<tr>
				<td align="right"><s:select size="8" id="left" name="ownRightIds" list="rights" listKey="id" listValue="rightName" multiple="true" /></td>
				<td align="center">
					<table>
						<tr><td><input style="width: 80px" type="button" id="add_em" value="<"></td></tr>
						<tr><td><input style="width: 80px" type="button" id="remove_em" value=">"></td></tr>
						<tr><td><input style="width: 80px" type="button" id="add_em_all" value="<<"></td></tr>
						<tr><td><input style="width: 80px" type="button" id="remove_em_all" value=">>"></td></tr>
					</table>
				</td>
				<td align="left"><s:select size="8" id="right" list="noOwnRights" listKey="id" listValue="rightName" multiple="true" /><td>
			</tr>
			<tr><td colspan="5" align="center"><s:submit id="selectAll" value="确认" /></td></tr>
		</table>
	</s:form>
</body>
</html>