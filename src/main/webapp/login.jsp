<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>酒店预订系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="jslib/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/login2.css"">

<script>
$(function(){
	
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		
		});
	$('#switch_login').click(function(){
		
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});
if(getParam("a")=='0')
{
	$('#switch_login').trigger('click');
}

	});
	
function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  


var reMethod = "POST",
	pwdmin = 6;

$(document).ready(function() {
	
	$('#loginClick').click(function() {
		if($('#userLogin').val() == ""){
			$('#userLogin').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCueLogin').html("<font color='red'><b>×用户名不能为空</b></font>");
			return false;
		}
		
		if($('#userPasswd').val() == ""){
			$('#userPasswd').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCueLogin').html("<font color='red'><b>×密码不能为空</b></font>");
			return false;
		}

	});


	$('#regClick').click(function() {

		if ($('#user').val() == "") {
			$('#user').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
			return false;
		}

		if ($('#user').val().length < 4 || $('#user').val().length > 16) {

			$('#user').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
			return false;

		}

		if ($('#passwd').val().length < pwdmin) {
			$('#passwd').focus();
			$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
			return false;
		}
		if ($('#passwd2').val() != $('#passwd').val()) {
			$('#passwd2').focus();
			$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
			return false;
		}
		
		$('#regUser').submit();
	});
	
});

</script>
</head>

<body>
	<h1>
		酒店预订系统<sup>nill</sup>
	</h1>

	<div class="login" style="margin-top:50px;">

		<div class="header">
			<div class="switch" id="switch">
				<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a> <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
				<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
			</div>
		</div>


		<div class="web_qr_login" id="web_qr_login" style="display: block; height: 265px;">

			<!--登录-->
			<div class="web_login" id="web_login">

				<div class="login-box">

					<div class="login_form">
						<form action="${pageContext.request.contextPath}/base/user!doNotNeedSessionAndSecurity_login.action" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
						<div id="userCueLogin" class="cueLogin"></div>
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">

									<input type="text" id="userLogin" name="data.loginname" class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">

									<input type="password" id="userPasswd" name="data.pwd" class="inputstyle" />
								</div>
							</div>

							<div style="padding-left:50px;margin-top:20px;">
								<input type="submit" id="loginClick" value="登 录" style="width:150px;" class="button_blue" />
							</div>
						</form>
					</div>

				</div>

			</div>
			<!--登录end-->
		</div>

		<!--注册-->
		<div class="qlogin" id="qlogin" style="display: none; ">

			<div class="web_login">
				<form name="form2" action="${pageContext.request.contextPath}/base/user!doNotNeedSessionAndSecurity_reg.action" id="regUser" accept-charset="utf-8" method="post">
			
					<ul class="reg_form" id="reg-ul">
						<div id="userCue" class="cue">快速注册请注意格式</div>
						<li><label for="user" class="input-tips2">用户名：</label>
							<div class="inputOuter2">
								<input type="text" id="user" name="data.loginname" maxlength="16" class="inputstyle2" />
							</div></li>

						<li><label for="passwd" class="input-tips2">密码：</label>
							<div class="inputOuter2">
								<input type="password" id="passwd" name="data.pwd" maxlength="16" class="inputstyle2" />
							</div></li>
						<li><label for="passwd2" class="input-tips2">确认密码：</label>
							<div class="inputOuter2">
								<input type="password" id="passwd2" name="" maxlength="16" class="inputstyle2" />
							</div></li>

						<li>
							<div class="inputArea">
								<input type="button" id="regClick" style="margin-top:10px;margin-left:85px;" class="button_blue" value="同意协议并注册" /> <a href="#" class="zcxy" target="_blank">注册协议</a>
							</div>

						</li>
						<div class="cl"></div>
					</ul>
				</form>


			</div>


		</div>
		<!--注册end-->
	</div>
	<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body>
</html>
