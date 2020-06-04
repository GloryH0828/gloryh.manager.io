<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/1
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head>
    <title>家电售后管理</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
        /*function username() {
    var username=document.getElementById("username");
    if (username.value==null ||username.value.trim()==""){
    alert("用户名不能为空！");
    return false;
    }
    return true;
    }
    function password() {
    var password=document.getElementById("password");
    if (password.value==null ||password.value.trim()==""){
    alert("密码不能为空！");
    return false;
    }
    return true;
    }
    function login() {
    if (username()&&password()){
    document.getElementById("form1").submit();
    return true;
    }
    return false;
    }*/
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">
        <form action="/loginServlet" method="post" >
            <input type="hidden" name="action" value="login"/>
            <ul>
                <li><input id="username" name="username" type="text" value="${username}"  class="loginuser" placeholder="请输入工号"
                           onclick="JavaScript:this.value=''"/>${unerror}</li>
                <li><input id="password" name="password" type="password" class="loginpwd" placeholder="请输入密码"
                           onclick="JavaScript:this.value=''"/>${pwderror}</li>
                <li><select id="role" name="role" class="loginrole">
                    <option value="admin">仓库管理员</option>
                    <option value="superadmin">经理</option>
                    <option value="infoadmin">信息管理员</option>
                    <option value="waiter">服务员</option>
                </select></li>
                <li><input name="login" type="submit" class="loginbtn"  value="登录"/></li>
            </ul>
        </form>
    </div>


</div>


<div class="loginbm">238黄光辉</div>
</body>
</html>
