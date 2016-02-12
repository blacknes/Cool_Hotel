<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录 -酒店管理后台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/login1.css"">

<script type="text/javascript">
	$(document).ready(function() {
		$("#login").click(function() {

			var action = $("#lg-form").attr('action');
			$.ajax({
				type : "POST",
				url : action,
				data : $('#lg-form').serialize(),
				dataType : 'json',
				success : function(response) {
					if (response.success)
						$("#lg-form").slideUp('slow', function() {
							$("#message").html('<p class="success">提示:' + response.msg + '</p><p>跳转至首页....</p>');
							location.replace("${pageContext.request.contextPath}/admin/adminMain.jsp");
						});
					else
						$("#message").html('<p class="error">提示:' + response.msg + ' </p>');
				}
			});
			return false;

		});
	});
</script>
</head>
<body>
	<div class="lg-container">
		<h1>管理登录</h1>
		<form action="${pageContext.request.contextPath}/base/user!doNotNeedSessionAndSecurity_login.action" id="lg-form" name="lg-form" method="post">

			<div>
				<label for="username">用户名:</label> <input type="text" name="data.loginname" id="username" placeholder="请输入用户名" />
			</div>

			<div>
				<label for="password">密 码:</label> <input type="password" name="data.pwd" id="password" placeholder="请输入密码" />
			</div>

			<div>
				<button type="submit" id="login">登录</button>
			</div>

		</form>
		<div id="message"></div>
	</div>

	<div style="width:200px; height:200px; margin-left:auto; margin-right:auto;">

		<strong>如果登录不了，或者数据错乱、丢失等情况，请点击下面链接(会初始化所有！仅限admin使用)</strong> <br /> 
		<a href="${pageContext.request.contextPath}/initData.jsp">初始化数据库(${pageContext.request.contextPath}/initData.jsp)</a>

	</div>

</body>
</html>