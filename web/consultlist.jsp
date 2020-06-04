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
        <span>咨询记录表</span>

    </div>


    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>问题<i class="sort"><img src="images/px.gif" /></i></th>
            <th>回答</th>
            <th>客户名</th>
            <th>手机号</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="consult">
            <tr>
                <td><input name="${consult.id}" type="checkbox" value="" /></td>
                <td>${consult.question}</td>
                <td>${consult.answer}</td>
                <td>${consult.customername}</td>
                <td>${consult.phone}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${page.totalSize}</i>条记录，当前显示第&nbsp;<i class="blue">${page.currentPage}</i>页</div>
        <ul class="paginList">
            <c:choose>
                <c:when test="${page.currentPage==1}">
                    <li class="paginItem"><a><span class="pagepre"></span></a></li>
                </c:when>
                <c:otherwise>
                    <li class="paginItem"><a href="/ConsultServlet?action=findAll&currentPage=${page.currentPage-1}"><span class="pagepre"></span></a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="${page.start}" end="${page.end}" var="i">
                <li class="paginItem"><a href="/ConsultServlet?action=findAll&currentPage=${i}">${i}</a></li>

            </c:forEach>
            <c:choose>
                <c:when test="${page.currentPage==page.totalPage}">
                    <li class="paginItem"><a><span class="pagenxt"></span></a></li>
                </c:when>
                <c:otherwise>
                    <li class="paginItem"><a href="/ConsultServlet?action=findAll&currentPage=${page.currentPage+1}"><span class="pagenxt"></span></a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>


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

