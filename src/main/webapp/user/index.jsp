<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nill.model.dataModel.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>SSHE</title>
<jsp:include page="incHeadFile.jsp"></jsp:include>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	if (sessionInfo != null) {
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
%>
</head>
<body>
</body>
</html>