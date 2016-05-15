<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<head>
<style>  

#ShowDiv {  
    position: absolute;  
    width:400px;  
    height:200px;  
    left:50%;  
    top:50%;  
    margin-left:-200px;  
    margin-top:-100px;  
    border:1px solid #FFA54F
}  
/*css注释：为了方便截图，对CSS代码进行换行*/  
#showMsg {
	position: absolute;  
    width:400px;  
    height:200px;  
    left:50%;  
    top:50%;  
    margin-left:-200px;  
    margin-top:-50px;  
}
</style>  

<script language="javascript">
var secs = 4; //倒计时
var URL ;
function Load(url){
URL = url;
for(var i=secs;i>=0;i--) 
{ 
   window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000); 
} 
}
function doUpdate(num) 
{ 
document.getElementById('ShowDiv').innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp将在&nbsp;'+num+'&nbsp;秒后自动跳转到主页' ;
if(num == 0) { window.location = URL; }
}
</script>

</head>

<body onload="Load('../securityLogin.jsp')" bgcolor="#FFFAF0">
<div id="ShowDiv"></div>

<div id="showMsg">

&nbsp;${msg}<script type="text/javascript" charset="utf-8">try{parent.$.messager.progress('close');parent.sy.progressBar('close');}catch(e){}</script>

</div>

</body>

