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

<title>My JSP 'index.jsp' starting page</title>


<link rel="stylesheet" type="text/css" href="CSS/styles.css">


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>

<body>
	<div class="index-content">
		<div class="index-content-left"style="width:540px;height:780px;">
			<img alt="" src="Image/gs2.jpg" style="width:540px;height:780px;">
		</div>
		<div class="index-content-right" style="width:580px;height:780px;">
			<h3>
				<a href="#" class="title ui-btn ui-icon-video"><strong>公司承办理念</strong></a>
			</h3>
			<ul class="sqicon">
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>品牌灵魂</strong></li>
			</ul>
			<p>&nbsp;作为艾伦品牌文化的精髓,艾伦的品牌灵魂是:工作与旅途中可信任的“家”。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>愿景</strong></li>
			</ul>
			<p>&nbsp;使艾伦酒店集团成为全球酒店行业前五甲的酒店管理企业。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>价值观</strong></li>
			</ul>
			<p>&nbsp;诚信尊重尽责进取合作</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>诚信</strong></li>
			</ul>
			<p>&nbsp;诚实和守信,这是我们做所有事情的前提。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>尊重</strong></li>
			</ul>
			<p>&nbsp;对顾客始终表现出我们在乎。同时始终真实和平等地对待每位员工、合作伙伴以及他们的劳动成果。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>尽责</strong></li>
			</ul>
			<p>&nbsp;承担起对工作,对周围人的责任,做到最好。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>进取</strong></li>
			</ul>
			<p>&nbsp;不好高骛远,但保持持续的创新和进取心,不断超越自我。</p>
			<ul>
				<li class="items ui-btn ui-icon-carat-r ui-btn-icon-left"><strong>品牌承诺</strong></li>
			</ul>
			<p>&nbsp;始终用心了解大众多元的旅行住宿需求和未来趋势,并专心为我们的宾客提供旅行中的“家”。</p>
		</div>
	</div>


</body>
</html>