<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/2
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
    </script>


</head>


<body>
<div class="rightinfo">

    <div class="formtitle">
        <span>报修记录表</span>

    </div>


    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>产品名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>维修原因</th>
            <th>客户名</th>
            <th>手机号</th>
            <th>地址</th>
            <th>处理结果</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${fixlog.list}" var="fixlogs">
            <tr>
                <td><input name="${fixlogs.id}" type="checkbox" value="" /></td>
                <td>${fixlogs.productname}</td>
                <td>${fixlogs.reason}</td>
                <td>${fixlogs.customername}</td>
                <td>${fixlogs.customerphone}</td>
                <td>${fixlogs.customeraddress}</td>
                <c:choose>
                    <c:when test="${fixlogs.state == 0}"><td>修理失败</td></c:when>
                    <c:otherwise><td>修理成功</td></c:otherwise>
                </c:choose>
                <td><a href="/FixLogServlet?action=fixLogCheck&id=${fixlogs.id}" class="tablelink">查看</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>

    </div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>

