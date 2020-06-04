<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/2
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>功能选项</div>
<%

    String role = (String) session.getAttribute("role");
    String name = (String) session.getAttribute("name");
    if (role != null) {
        if (role.equals("superadmin") || role.equals("waiter")) {

%>
<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="images/leftico01.png"/></span>报修，咨询及投诉
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="repair.jsp" target="rightFrame">客户报修</a><i></i></li>
            <li><cite></cite><a href="consult.jsp" target="rightFrame">客户咨询</a><i></i></li>
            <li><cite></cite><a href="complaint.jsp" target="rightFrame">客户投诉</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/RepairServlet?action=findAll" target="rightFrame">报修记录</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/ConsultServlet?action=findAll" target="rightFrame">咨询记录</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/ComplaintServlet?action=findAll"  target="rightFrame">投诉记录</a><i></i></li>
        </ul>

    </dd>
</dl>
<%


    }
    if (role.equals("superadmin") || role.equals("infoadmin")) {

%>
<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="images/leftico03.png"/></span>维修记录
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="${pageContext.request.contextPath }/FixLogServlet?action=findAll" target="rightFrame">维修完成记录列表</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/FixLogServlet?action=update" target="rightFrame">录入维修记录</a><i></i></li>
        </ul>
    </dd>

</dl>
<%


    }
    if (role.equals("superadmin") || role.equals("admin")) {

%>
<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="images/leftico02.png"/></span>仓库管理
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="${pageContext.request.contextPath }/StorageServlet?action=findAll" target="rightFrame">仓库内物品信息</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/StorageServlet?action=findWorker" target="rightFrame">物品出库</a><i></i></li>
            <li><cite></cite><a href="${pageContext.request.contextPath }/StorageServlet?action=workerReturn" target="rightFrame">物品入库</a><i></i></li>
        </ul>
    </dd>

</dl>
<%
        }
    }
%>
</body>
</html>