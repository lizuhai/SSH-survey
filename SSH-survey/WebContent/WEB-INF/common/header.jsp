<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<h1>Survey Park</h1>
	<s:a action="LoginAction_toLoginPage">首页</s:a>
	<s:a action="SurveyAction_newSurvey">[新建调查]</s:a>
	<s:a action="SurveyAction_mySurveys">[我的调查]</s:a>
	<s:a action="EngageSurveyAction_findAllAvailableSurveys">[参与调查]</s:a>
	<s:a action="RegAction_toRegPage">[注册]</s:a>
	<s:a action="UserAuthorizeAction_findAllUsers">[授权管理]</s:a>
	<s:a action="RoleAction_findAllRoles">[角色管理]</s:a>
	<s:a action="RightAction_findAllRights">[权限管理]</s:a>
	<s:a action="LogAction_findNearestLogs">[日志管理]</s:a>
	<s:a action="">[退出]</s:a>

</body>
</html>