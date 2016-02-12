<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>初始化数据库</title>
<jsp:include page="incHeadFile.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$.post(sy.contextPath + '/init!doNotNeedSessionAndSecurity_initDb.action', function(result) {
			if (result.success) {
				window.location.replace(sy.contextPath + '/securityLogin.jsp');
			}
		}, 'json');
	});
</script>
</head>
<body>
<h>数据库初始化中，请稍候....</h>
</body>
</html>